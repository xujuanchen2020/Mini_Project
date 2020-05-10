package com.example.androidfinalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private Button logoutBtn, addBtn, deleteBtn;
    private TextView textConsumedCals;
    private String stringConsumedCals;

    // Calender by Rene
    private TextView tt;
    public static final int TEXT_REQUEST = 1;

    // Shared preference
    private Float mCount = 0f;
    private int mColor;
    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";
    // Shared preferences object
    private SharedPreferences mPreferences;
    // Name of shared preferences file
    private String sharedPrefFile = "sharedpreference";
    private CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        setTitle("Go Back");

        logout();
        add();
        delete();
        setCircularProgressBar();
        pickCalender();
    }

    private void logout() {
        // click button exit
        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void add() {
        // click button add
        addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(HomeActivity.this, MainRecyclerActivity.class);
                startActivity(intent_add);

            }
        });
    }

    private void delete() {
        // click button add
        deleteBtn = (Button)findViewById(R.id.delete_button);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount = mCount - Float.valueOf(stringConsumedCals);
                if (mCount >0f){
                    textConsumedCals.setText(String.valueOf(mCount));
                    circularProgressBar.setProgress(mCount);
                }
                else {
                    mCount = 0f;
                    textConsumedCals.setText(String.valueOf(mCount));
                    circularProgressBar.setProgress(mCount);
                }
            }
        });
    }

    private void setCircularProgressBar() {
        // set CircularProgressBar
        circularProgressBar = findViewById(R.id.circularProgressBar);
        // Set Width, RoundBorder, Max
        circularProgressBar.setProgressBarWidth(30); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(30); // in DP
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setProgressMax(3000f);

        // Initialize views, color, preferences
        textConsumedCals = (TextView) findViewById(R.id.text_consumed_calories);
        mColor = ContextCompat.getColor(HomeActivity.this, R.color.colorAccent);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // Restore preferences
        mCount = mPreferences.getFloat(COUNT_KEY, 0);
        textConsumedCals.setText(String.format("%s", mCount));
        circularProgressBar.setProgress(mCount);

        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        circularProgressBar.setProgressBarColor(mColor);

        // add up calories from intent
        getIntentCalories();

    }

    private void getIntentCalories() {

        stringConsumedCals = getIntent().getStringExtra(MainRecyclerActivity.CALORIES_RESULT);
        if (stringConsumedCals != null) {
            mCount = mCount + Float.valueOf(stringConsumedCals);
            textConsumedCals.setText(String.valueOf(mCount));
            circularProgressBar.setProgress(mCount);
            if (mCount >= 3000f) {
                Toast.makeText(HomeActivity.this,
                               "Too many calories today.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void changeBackground (View view){
        int color = ((ColorDrawable) view.getBackground()).getColor();
        circularProgressBar.setProgressBarColor(color);
        mColor = color;

    }

    public void reset (View view){
        // Reset count
        mCount = 0f;
        textConsumedCals.setText(String.format("%s", mCount));
        circularProgressBar.setProgress(mCount);

        // Reset color
        mColor = ContextCompat.getColor(this, R.color.colorAccent);
        circularProgressBar.setProgressBarColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    public void clear (View view){
        // Reset count
        mCount = 0f;
        textConsumedCals.setText(String.format("%s", mCount));
        circularProgressBar.setProgress(mCount);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putFloat(COUNT_KEY, mCount);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restores the scores if there is savedInstanceState
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getFloat(COUNT_KEY);
            //Set the score text views
            textConsumedCals.setText(String.valueOf(mCount));
            circularProgressBar.setProgress(mCount);

        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause () {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putFloat(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
    }

    @Override
    protected void onStart(){
        super.onStart();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }




    // Calender by Rene
    private void pickCalender() {

        tt = (TextView) findViewById(R.id.text_date_label);

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String date = simpleDateFormat.format(c.getTime());

        tt.setText("Date: "+date);

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalenderActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String dd = data.getStringExtra(CalenderActivity.EXTRA_DATE);
                tt.setText("Date: " + dd);
                tt.setVisibility(View.VISIBLE);
            }
        }
    }
}



