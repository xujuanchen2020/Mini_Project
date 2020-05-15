package com.example.foodie_mp5;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MealItem> foodData;
    private MealItemAdapter foodAdapter;
    private int gridColumnCount;
    private AddItemActivity addItemActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        foodData = new ArrayList<>();
        foodAdapter = new MealItemAdapter(this, foodData);
        recyclerView.setAdapter(foodAdapter);

        loadFoodsData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                foodAdapter.addContent();
                foodAdapter.addContent();



            }
        });
    }

    private void loadFoodsData() {
        //load arrays from strings
        foodData.clear();
        TypedArray foodImage = getResources().obtainTypedArray(R.array.food_images);
        String[] foodTitle = getResources().getStringArray(R.array.food_titles);
        String[] foodDescription = getResources().getStringArray(R.array.food_descriptions);
        String[] foodIngredient = getResources().getStringArray(R.array.food_ingredients);
        String[] foodCalories = getResources().getStringArray(R.array.food_calories);
        String[] foodLink = getResources().getStringArray(R.array.food_links);


        List<String> titles = Arrays.asList(foodTitle);


        for(int i=0; i<foodTitle.length; i++){

            MealItem currFood = new MealItem(foodTitle[i],foodDescription[i],
                      foodIngredient[i],foodCalories[i],foodLink[i],
                      foodImage.getResourceId(i,0));
            foodData.add(currFood);
        }

        foodAdapter.notifyDataSetChanged();
        foodImage.recycle();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
