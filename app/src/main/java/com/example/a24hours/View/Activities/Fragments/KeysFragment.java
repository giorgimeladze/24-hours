package com.example.a24hours.View.Activities.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.a24hours.Model.Key;
import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.Presenter.MyKeysGridAdapter;
import com.example.a24hours.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeysFragment extends Fragment implements MyKeysGridAdapter.OnKeyClickListener{

    private RecyclerView recyclerView;
    private ArrayList<Key> arrayList;
    private DatabaseReference myRef;
    private ArrayList<String> stringList = new ArrayList<>();
    private Typeface myFont;

    public KeysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keys, container, false);

        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BPG Boxo-Boxo.ttf");
        TextView textView = view.findViewById(R.id.title_text_id);
        textView.setText(LocalRepository.getInstitutionName());
        textView.setTypeface(myFont);

        recyclerView = view.findViewById(R.id.keys_recycler_view_id);

        myRef = LocalRepository.getDatabaseReference(LocalRepository.getPathName());
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            public void run() {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
                        arrayList = new ArrayList<>();
                        if (stringList.size() != 0) stringList.clear();
                        for (DataSnapshot ds : list){
                            stringList.add(ds.getKey());
                        }
                        for (String i : stringList)
                            arrayList.add(new Key(i,R.drawable.common_google_signin_btn_icon_dark));
                        initRecycler(arrayList);
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
    public void onKeyClick(Key key) {
        LocalRepository.setKeysPathName(key.getTextTitle());
        Fragment fragment = new DistrictsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_of_activity_id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initRecycler(ArrayList<Key> list){
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(),2));

        MyKeysGridAdapter myKeysGridAdapter = new MyKeysGridAdapter();
        myKeysGridAdapter.setOnKeyClickListener(this::onKeyClick);
        myKeysGridAdapter.addKeyItems(list);

        recyclerView.setAdapter(myKeysGridAdapter);
    }
}
