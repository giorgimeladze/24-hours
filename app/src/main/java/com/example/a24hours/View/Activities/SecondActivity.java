package com.example.a24hours.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a24hours.Model.LocalRepository;
import com.example.a24hours.R;
import com.example.a24hours.View.Activities.Fragments.KeysFragment;

import de.hdodenhof.circleimageview.CircleImageView;


public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private String[] institutions;
    private String[] institutionsOnEnglish = {"Entertainment","Cars","Health", "Others", "Shopping", "Finances", "Vacation", "Food Facility"};
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getClickedInstitutionsName();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new KeysFragment();
        fragmentTransaction.add(R.id.frame_layout_of_activity_id, fragment);
        fragmentTransaction.commit();

        AppCompatButton chvenButton = findViewById(R.id.chven_icon_id);
        chvenButton.setOnClickListener(v -> {
            myDialog = new Dialog(this);
            myDialog.setContentView(R.layout.activity_pop_up_information);
            TextView closeWindow = myDialog.findViewById(R.id.close_pop_up_btn_id);
            TextView informationTxt = myDialog.findViewById(R.id.info_id);
            closeWindow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        });

        CircleImageView logoImageView = findViewById(R.id.navigation_logo_id);
        logoImageView.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void getClickedInstitutionsName() {
        institutions = getApplicationContext().getResources().getStringArray(R.array.institutions);
        Bundle extra = getIntent().getExtras();
        String institution = extra.getString(getApplicationContext().getResources().getString(R.string.institution));
        for (int i = 0; i < institutions.length; i++) {
            if (institutions[i].equals(institution)) {
                LocalRepository.setPathName(institutionsOnEnglish[i]);
                LocalRepository.setInstitutionName(institution);
                return;
            }
        }

        Log.e(TAG, "nothing happened");
    }


}
