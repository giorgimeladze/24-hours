package com.example.a24hours.View.Activities.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a24hours.Model.Institution;
import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.Presenter.MyInstitutionsListAdapter;
import com.example.a24hours.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstitutionsFragment extends Fragment implements MyInstitutionsListAdapter.OnInstitutionClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Institution> arrayList;
    private DatabaseReference myRef;
    private Typeface myFont;

    public InstitutionsFragment() {
        // Required empty public constructor
        arrayList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_institutions, container, false);

        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BPG Boxo-Boxo.ttf");
        TextView txtView = view.findViewById(R.id.chosen_district_name_id);
        txtView.setText(LocalRepository.getChosenDistrictName());
        txtView.setTypeface(myFont);

        recyclerView = view.findViewById(R.id.institution_list_id);
        myRef = LocalRepository.getMyDatabaseRef().child(LocalRepository.getKeysPathName()).child(LocalRepository.getChosenDistrictName());
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                        Iterable<DataSnapshot> iterable2;
                        String address,description,number,url;
                        if (!arrayList.isEmpty()) arrayList.clear();
                        for (DataSnapshot ds : iterable){
                            iterable2 = ds.getChildren();
                            description = (String) iterable2.iterator().next().getValue();
                            address = (String) iterable2.iterator().next().getValue();
                            number = (String) iterable2.iterator().next().getValue();
                            url = (String) iterable2.iterator().next().getValue();
                            arrayList.add(new Institution(ds.getKey(),description,address,number,url));
                        }

                        initRecycler();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        databaseError.toException().printStackTrace();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onInstitutionClicked(Institution institution) {
        LocalRepository.setInstitutionObject(institution);
        Fragment fragment = new InstitutionInformationFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_of_activity_id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        MyInstitutionsListAdapter myInstitutionsListAdapter = new MyInstitutionsListAdapter();
        myInstitutionsListAdapter.setOnDistrictClickListener(this::onInstitutionClicked);
        myInstitutionsListAdapter.addInstitutions(arrayList);

        recyclerView.setAdapter(myInstitutionsListAdapter);
    }

}
