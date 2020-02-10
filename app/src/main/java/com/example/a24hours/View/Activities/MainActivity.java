package com.example.a24hours.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.a24hours.R;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String[] institutions;
    private CircleImageView imageView;
    private androidx.appcompat.widget.SearchView searchView;
    private Dialog myDialog;
    private Typeface myFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/BPG Boxo-Boxo.ttf");

        searchView.setSubmitButtonEnabled(true);
        imageView.setOnClickListener(v -> initDialog());

        toolbar.setOnClickListener(v -> searchView.setIconified(false));

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Intent intent = new Intent(Intent.ACTION_SEARCH);
                Intent intent = new Intent(MainActivity.this,SearchResultActivity.class);
                intent.putExtra(SearchManager.QUERY, searchView.getQuery());
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem item = menu.findItem(R.id.menu_search_id);
        CharSequence cs = getString(R.string.search_hint);
        searchView.setQueryHint(cs);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        return true;
    }

    private void initUI() {
        Context context = getApplicationContext();
        institutions = context.getResources().getStringArray(R.array.institutions);
        searchView = findViewById(R.id.search_id);
        imageView = findViewById(R.id.additional_info_btn_id);
        toolbar = findViewById(R.id.tool_bar_id);
    }

    private void initDialog(){
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.activity_pop_up_information);
        TextView closeWindow = myDialog.findViewById(R.id.close_pop_up_btn_id);
        closeWindow.setTypeface(myFont);
        TextView informationTxt = myDialog.findViewById(R.id.info_id);
        informationTxt.setTypeface(myFont);
        closeWindow.setOnClickListener(v -> myDialog.dismiss());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void initListeners() {
        Picasso.get().load(R.mipmap.information_us_icon).resize(20,33).into(imageView);

        AppCompatButton entertainmentBtn = findViewById(R.id.entertainment_btn_id);
        entertainmentBtn.setText(institutions[0]);
        entertainmentBtn.setTypeface(myFont);
        entertainmentBtn.setTextColor(Color.BLACK);

        AppCompatButton carsBtn = findViewById(R.id.automobile_btn_id);
        carsBtn.setText(institutions[1]);
        carsBtn.setTypeface(myFont);
        carsBtn.setTextColor(Color.BLACK);

        AppCompatButton healthBtn = findViewById(R.id.healthy_department_btn_id);
        healthBtn.setText(institutions[2]);
        healthBtn.setTypeface(myFont);
        healthBtn.setTextColor(Color.BLACK);

        AppCompatButton otherCatBtn = findViewById(R.id.others_btn_id);
        otherCatBtn.setText(institutions[3]);
        otherCatBtn.setTypeface(myFont);
        otherCatBtn.setTextColor(Color.BLACK);

        AppCompatButton shoppingBtn = findViewById(R.id.shopping_btn_id);
        shoppingBtn.setText(institutions[4]);
        shoppingBtn.setTypeface(myFont);
        shoppingBtn.setTextColor(Color.BLACK);

        AppCompatButton financesBtn = findViewById(R.id.finances_btn_id);
        financesBtn.setText(institutions[5]);
        financesBtn.setTypeface(myFont);
        financesBtn.setTextColor(Color.BLACK);

        AppCompatButton vacationBtn = findViewById(R.id.vacation_btn_id);
        vacationBtn.setText(institutions[6]);
        vacationBtn.setTypeface(myFont);
        vacationBtn.setTextColor(Color.BLACK);

        AppCompatButton foodFacilityBtn = findViewById(R.id.food_facility_btn_id);
        foodFacilityBtn.setText(institutions[7]);
        foodFacilityBtn.setTypeface(myFont);
        foodFacilityBtn.setTextColor(Color.BLACK);

        entertainmentBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[0]);
            startActivity(intent);
        });

        carsBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[1]);
            startActivity(intent);
        });

        healthBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[2]);
            startActivity(intent);
        });

        otherCatBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[3]);
            startActivity(intent);
        });

        shoppingBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[4]);
            startActivity(intent);
        });

        financesBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[5]);
            startActivity(intent);
        });

        vacationBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[6]);
            startActivity(intent);
        });

        foodFacilityBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(getResources().getString(R.string.institution),institutions[7]);
            startActivity(intent);
        });
    }

}
