package com.example.a24hours.View.Activities.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a24hours.Model.Institution;
import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Institution> arrayList;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.favorite_recycler_list_id);
        arrayList = LocalRepository.getFavorites();

        return view;
    }

}
