package com.example.mp4_tipcal;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.time.MonthDay;
import java.util.logging.Formatter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText tvEnterAmount;
    private TextView tvPercent;
    private SeekBar seekBar;
    private TextView tvTip;
    private TextView tvTotal;
    private TextView perPerson;
    private Spinner spinner;
    private ArrayAdapter<CharSequence>adapter;
    private double roundType;
    private double numOfPerson;
    private double amount;
    private double percent;
    private double tip;
    private double total;
    private double perP;
    private RadioButton button_no_tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("MainActivity","inside onCreate.");
        tvEnterAmount = findViewById(R.id.tv_EnterAmount);
        tvPercent = findViewById(R.id.tv_Percent);
        seekBar = findViewById(R.id.sb_Percent);
        tvTip = findViewById(R.id.tv_Tip);
        tvTotal = findViewById(R.id.tv_Total);
        perPerson = findViewById(R.id.tv_perPerson);
        button_no_tip = findViewById(R.id.no_label);
        button_no_tip.setChecked(true);

        Log.d("MainActivity","inside onCreate spinner");
        spinner =findViewById(R.id.label_spinner);
        adapter = ArrayAdapter.createFromResource
                (this,R.array.labels_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null){
            spinner.setOnItemSelectedListener(MainActivity.this);
            spinner.setAdapter(adapter);
        }
        Log.d("MainActivity","inside onCreate spinner not null");


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // tvPercent.setText(String.valueOf(progress) + "%");
                int percent = progress;
                tvPercent.setText(String.valueOf(percent) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tvEnterAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();

            }
        });




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void calculate() {

        Log.d("MainActivity","inside calculate method.");
        if (tvEnterAmount.length() == 0) {
            tvEnterAmount.requestFocus();
            tvEnterAmount.setError("Please choose round type.");
        } else if ( roundType == 1) {
            double amount = Double.parseDouble(tvEnterAmount.getText().toString());
            int percent = seekBar.getProgress();
            double tip = amount * percent / 100.00;
            double total = amount + tip;
            double perP = total/numOfPerson;
            tvTip.setText("$" + String.valueOf(tip));
            tvTotal.setText("$" + String.valueOf(total));
            perPerson.setText("Per Person: $" + String.valueOf(perP));
        }else if (roundType == 2){
            double amount = Double.parseDouble(tvEnterAmount.getText().toString());
            int percent = seekBar.getProgress();
            double tip = amount * percent / 100.00;
            double roundTip = Math.ceil(tip);
            double total = amount + roundTip;
            double perP = total/numOfPerson;
            tvTip.setText("$" + String.valueOf(roundTip));
            tvTotal.setText("$" + String.valueOf(total));
            perPerson.setText("Per Person: $" + String.valueOf(perP));
        }else if (roundType == 3){
            double amount = Double.parseDouble(tvEnterAmount.getText().toString());
            int percent = seekBar.getProgress();
            double tip = amount * percent / 100.00;
            double total = amount + tip;
            double roundTotal = Math.ceil(total);
            double perP = roundTotal/numOfPerson;
            tvTip.setText("$" + String.valueOf(tip));
            tvTotal.setText("$" + String.valueOf(roundTotal));
            perPerson.setText("Per Person: $" + String.valueOf(perP));
        }
        else{
            double amount = Double.parseDouble(tvEnterAmount.getText().toString());
            int percent = seekBar.getProgress();
            double tip = amount * percent / 100.00;
            double total = amount + tip;
            double perP = total/numOfPerson;
            tvTip.setText("$" + String.valueOf(tip));
            tvTotal.setText("$" + String.valueOf(total));
            perPerson.setText("Per Person: $" + String.valueOf(perP));
        }
        Log.d("MainActivity", "inside calculate method");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.icon_share) {
            Log.d("MainActivity","inside icon share.");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"The Bill Amount $" +
                    tvEnterAmount.getText() + ", Tip " + tvTip.getText() +
                    ", Total Price " + tvTotal.getText() +
                    ", Split by " + (int) numOfPerson + " persons, "+perPerson.getText());
            intent.setType("text/plain");
            Intent chooser = Intent.createChooser(intent,"Share with ");
            if(intent.resolveActivity(getPackageManager())!= null ){
                startActivity(chooser);

            }
            return true;
        }else if (id==R.id.icon_info){
            Log.d("MainActivity","inside icon info");
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setTitle("Message");
            alertBuilder.setMessage("The dropdown is used to split the total among friends" +
                    " and the total amount is what each individual must pay.");
            alertBuilder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicker(View view) {
        Log.d("MainActivity","inside onRadioButtonClicker");

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.no_label:
                if(checked)
                    Log.d("MainActivity","inside onRadioButtonClicker case 1");
                    //tvEnterAmount.clearComposingText();
                    roundType=1;
                    displayToast("No Round");
                break;
            case R.id.tip_label:
                if(checked)
                    Log.d("MainActivity","inside onRadioButtonClicker case 2");
                   // tvEnterAmount.clearComposingText();
                    roundType=2;
                    displayToast("Round Tip");
                break;
            case R.id.total_label:
                if(checked)
                    Log.d("MainActivity","inside onRadioButtonClicker case 3");
                    //
                // tvEnterAmount.clearComposingText();
                    roundType=3;
                    displayToast("Round Total");
                break;
            default:
                break;
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                Log.d("MainActivity","inside onItemSelected case 1");
                numOfPerson = 2;
                calculate();
                break;
            case 2:
                Log.d("MainActivity","inside onItemSelected case 2");
                numOfPerson = 3;
                calculate();
                break;
            case 3:
                Log.d("MainActivity","inside onItemSelected case 3");
                numOfPerson = 4;
                calculate();
                break;
            case 4:
                Log.d("MainActivity","inside onItemSelected case 4");
                numOfPerson = 5;
                calculate();
                break;
            default:
                Log.d("MainActivity","inside onOtemSeected default");
                numOfPerson = 1;
                calculate();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
