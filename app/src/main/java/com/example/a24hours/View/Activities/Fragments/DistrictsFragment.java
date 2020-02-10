package com.example.a24hours.View.Activities.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.Presenter.MyDistrictsListAdapter;
import com.example.a24hours.Presenter.MyKeysGridAdapter;
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
public class DistrictsFragment extends Fragment implements MyDistrictsListAdapter.OnDistrictClickListener {

    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<String> districtList;
    private Typeface myFont;

    public DistrictsFragment() {
        // Required empty public constructor
        districtList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_districts, container, false);

        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BPG Boxo-Boxo.ttf");
        TextView txtView = view.findViewById(R.id.chosen_key_name_id);
        txtView.setText(LocalRepository.getKeysPathName());
        txtView.setTypeface(myFont);

        recyclerView = view.findViewById(R.id.districts_list_id);
        myRef = LocalRepository.getMyDatabaseRef().child(LocalRepository.getKeysPathName());
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
//                        if (!districtList.isEmpty()) districtList.clear();
//                        for (DataSnapshot ds : list){
//                            districtList.add(ds.getKey());
//                        }
                        if(districtList.isEmpty()) {
                            for (DataSnapshot ds : list){
                                districtList.add(ds.getKey());
                            }
                        }
                        initRecycler(districtList);
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
    public void onDistrictClick(String district) {
        LocalRepository.setChosenDistrictName(district);
        Fragment fragment = new InstitutionsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_of_activity_id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initRecycler(ArrayList<String> arrayList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        MyDistrictsListAdapter myDistrictsListAdapter = new MyDistrictsListAdapter();
        myDistrictsListAdapter.setOnDistrictClickListener(this::onDistrictClick);
        myDistrictsListAdapter.addDistricts(arrayList);

        recyclerView.setAdapter(myDistrictsListAdapter);
    }
}
