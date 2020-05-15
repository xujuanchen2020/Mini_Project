package com.example.foodie_mp5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    private ArrayList<MealItem> foodsData;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


    }


//    public void addContent(){
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        final View dialogView = inflater.inflate(R.layout.activity_dialog,null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText editTitle = dialogView.findViewById(R.id.dialog_item_title);
//        final EditText description = dialogView.findViewById(R.id.dialog_description);
//        final EditText ingredients = dialogView.findViewById(R.id.dialog_ingredients);
//        final EditText calories = dialogView.findViewById(R.id.dialog_calories);
//        final EditText link = dialogView.findViewById(R.id.dialog_link);
//
//        dialogBuilder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
//
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String title = editTitle.getText().toString();
//                String des = description.getText().toString();
//                String ing = ingredients.getText().toString();
//                String cal = calories.getText().toString();
//                String lin = link.getText().toString();
//                MealItem curMeal = new MealItem(title,des,ing,cal,lin,0);
//                if((title.length()+des.length()+ing.length()+cal.length())>0){
//                    foodsData.add(foodsData.size(),curMeal);
//
//
//                }
//            }
//        });
//        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // do nothingl
//
//            }
//        });
//
//        dialogBuilder.create().show();
//
//    }
}
