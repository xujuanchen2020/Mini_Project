package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String END_RESULT = "End Result";
    public static final String WINNING_TEAM = "Who Wins";

    public static final int TEXT_REQUEST = 1;

    private Button button_A;
    private Button button_B;
    private TextView tv_A;
    private TextView tv_B;
    private int aCount = 0;
    private int bCount = 0;

    private String message_A;
    private String message_B;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_A = findViewById(R.id.button_teamA);
        button_B = findViewById(R.id.button_teamB);
        tv_A = findViewById(R.id.text_teamA);
        tv_B = findViewById(R.id.text_teamB);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        Log.d(TAG,"inside onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
       super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "inside of onStart");


        Log.d(TAG, "end of onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "inside of onResume");

        Log.d(TAG, "end of onResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "inside of onRestart");


        Log.d(TAG, "end of onRestart");
    }


    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "inside of onPause");


        Log.d(TAG, "end of onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "inside of onStop");


        Log.d(TAG, "end of onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "inside of onDestroy");


        Log.d(TAG, "end of onDestroy");
    }


    public void aCountUp(android.view.View view) {
        aCount++;
        tv_A.setText(""+aCount);
        nextActivity();
    }


    public void bCountUp(android.view.View view) {
        bCount++;
        tv_B.setText(""+bCount);
        nextActivity();
    }


    public void nextActivity() {
        Intent intent = new Intent(this, WinnerActivity.class);
        int result;
        if(aCount == 5){
            result = aCount - bCount;
            intent.putExtra(END_RESULT, result);
            intent.putExtra(WINNING_TEAM, "TEAM A:");
            startActivity(intent);
        }
        else if(bCount == 5) {
            result = bCount - aCount;

            intent.putExtra(END_RESULT, result);
            intent.putExtra(WINNING_TEAM, "TEAM B:");
            startActivity(intent);
        }
    }

}
