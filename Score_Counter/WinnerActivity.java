package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {
    private static final String TAG = WinnerActivity.class.getSimpleName();
    private TextView tv_result;
    private String winningTeam;
    private String strScoreCount;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent();
        tv_result = findViewById(R.id.textView_Result);

        strScoreCount = intent.getExtras().get(MainActivity.END_RESULT).toString();
        winningTeam = intent.getExtras().get(MainActivity.WINNING_TEAM).toString();

        tv_result.append(" " + strScoreCount);
        result = winningTeam+" "+strScoreCount;
        tv_result.setText(result);
        Log.d(TAG,"WinnerActivity");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "inside onSaveInstanceState");
        outState.putString("result",winningTeam);
        outState.putString("wonBy",strScoreCount);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"inside onRestoreInstanceState");
        winningTeam=savedInstanceState.getString("reslut",winningTeam);
        strScoreCount=savedInstanceState.getString("wonBy",strScoreCount);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "inside onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"inside onResume");
        tv_result.setText(result);

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "inside onRestart" );
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"inside onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"inside onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"inside onDestroy");
    }


    public void makeCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Intent chooser = Intent.createChooser(intent,"Call with");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,result);
        intent.setType("text/plain");
        Intent chooser = Intent.createChooser(intent,"Share with");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }

    }

    public void findLocation(View view) {
        Uri uri = Uri.parse("geo:0,0?q=GameStop");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
        Intent chooser = mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager())!=null){
            startActivity(chooser);
        }

    }
}

