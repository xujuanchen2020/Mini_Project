package com.example.foodie_mp5;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mealTitle = findViewById(R.id.mealTitle);
        TextView mealDescription = findViewById(R.id.mealInfo);
        ImageView imageView = findViewById(R.id.mealImage);
        TextView mealIngredient = findViewById(R.id.mealIngredients);
        TextView mealCalories = findViewById(R.id.mealCalories);
        TextView mealLink = findViewById(R.id.mealLink);

        mealTitle.setText(getIntent().getStringExtra("Title"));
        mealDescription.setText(getIntent().getStringExtra("Description"));
        mealIngredient.setText(getIntent().getStringExtra("Ingredient"));
        mealCalories.setText(getIntent().getStringExtra("Calories"));
        mealLink.setText(getIntent().getStringExtra("Link"));
        int img_ID = getIntent().getIntExtra("Image_ID",0);
        imageView.setImageResource(getIntent().getIntExtra("Image_ID ",0));


    }
}
