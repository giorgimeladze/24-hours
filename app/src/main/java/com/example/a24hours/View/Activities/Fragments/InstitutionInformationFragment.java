package com.example.a24hours.View.Activities.Fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24hours.Model.Institution;
import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstitutionInformationFragment extends Fragment {

    private Institution institution;
    private Typeface myFont;

    public InstitutionInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_institution_information, container, false);

        institution = LocalRepository.getInstitutionObject();
        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BPG Boxo-Boxo.ttf");

        TextView nameTxtView = view.findViewById(R.id.institution_name_id);
        nameTxtView.setText(institution.getName());
        nameTxtView.setTypeface(myFont);
        TextView descriptionTxtView = view.findViewById(R.id.institution_description_id);
        descriptionTxtView.setText(institution.getDescription());
        descriptionTxtView.setTypeface(myFont);
        TextView numberTxtView = view.findViewById(R.id.institution_number_id);
        numberTxtView.setText(institution.getPhoneNumber());
        numberTxtView.setTypeface(myFont);
        TextView addressTxtView = view.findViewById(R.id.institution_address_id);
        addressTxtView.setText(institution.getAddress());
        addressTxtView.setTypeface(myFont);
        TextView googleTxt = view.findViewById(R.id.google_url_open_id);
        googleTxt.setTypeface(myFont);
        googleTxt.setOnClickListener(v -> {
            // gadavides google mapis applicationshi
            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse(institution.getGoogleURL());

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        });
//        RatingBar ratingBar = view.findViewById(R.id.rating_bar_icon_id);
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                if (ratingBar.getRating() == 0) {
//                    institution.setFavorite(false);
//                } else {
//                    institution.setFavorite(true);
//                }
//
//                institutionArrayList();
//            }
//        });

        numberTxtView.setOnLongClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("phone number", numberTxtView.getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "ნომერი დაკოპირებულია",
                    Toast.LENGTH_LONG).show();
            return false;
        });
        return view;
    }

//    private void institutionArrayList() {
//        ArrayList<Institution> list = LocalRepository.getFavorites();
//        if (institution.isFavorite()){
//            list.add(institution);
//            Toast.makeText(getActivity().getBaseContext(), "ინსტიტუტი დამატებულია ფავორიტებში", Toast.LENGTH_LONG).show();
//        } else {
//            list.remove(institution);
//            Toast.makeText(getActivity().getBaseContext(), "ინსტიტუტი ამოღებულია ფავორიტებიდან", Toast.LENGTH_LONG).show();
//        }
//
//    }

}
