package com.example.foodie_mp5;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.ViewHolder> {
    private ArrayList<MealItem> foodsData;
    private Context context;

    public MealItemAdapter(Context context, ArrayList<MealItem>foodsArrayList) {
        this.context = context;
        this.foodsData = foodsArrayList;
    }
    public void addContent(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.activity_dialog,null);
        dialogBuilder.setView(dialogView);

        final EditText editTitle = dialogView.findViewById(R.id.dialog_item_title);
        final EditText description = dialogView.findViewById(R.id.dialog_description);
        final EditText ingredients = dialogView.findViewById(R.id.dialog_ingredients);
        final EditText calories = dialogView.findViewById(R.id.dialog_calories);
        final EditText link = dialogView.findViewById(R.id.dialog_link);

        dialogBuilder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editTitle.getText().toString();
                String des = description.getText().toString();
                String ing = ingredients.getText().toString();
                String cal = calories.getText().toString();
                String lin = link.getText().toString();
                MealItem curMeal = new MealItem(title,des,ing,cal,lin,0);
                if((title.length()+des.length()+ing.length()+cal.length())>0){
                    foodsData.add(foodsData.size(),curMeal);

                    notifyItemInserted(foodsData.size());


                }
            }
        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothingl

            }
        });

        dialogBuilder.create().show();

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealItem currFood = foodsData.get(position);
        holder.bindItem(currFood);
    }

    @Override
    public int getItemCount() {
        return foodsData.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView textTitle, textDescription;
        private ImageView imageViewFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            textDescription = itemView.findViewById(R.id.description);
            imageViewFood = itemView.findViewById(R.id.imageViewFood);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bindItem(MealItem currentFood){
            textTitle.setText(currentFood.getTitle());
            textDescription.setText(currentFood.getDescription());
            Glide.with(context).load(currentFood.getImageID()).into(imageViewFood);
        }

        @Override
        public void onClick(View v) {

            MealItem currMeal = foodsData.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailsActivity.class);

//            intent.putExtra("Image_ID", currMeal.getImageID());
            intent.putExtra("Title", currMeal.getTitle());
            intent.putExtra("Description", currMeal.getDescription());
            intent.putExtra("Ingredients", currMeal.getIngredient());
            intent.putExtra("Calories", currMeal.getCalories());
            intent.putExtra("Link", currMeal.getLink());

            Bundle bundle=new Bundle();
            bundle.putInt("image",currMeal.getImageID());
            intent.putExtras(bundle);

            context.startActivity(intent);

        }

        @Override
        public boolean onLongClick(View v) {
            MealItem currFood = foodsData.get(getAdapterPosition());
            foodsData.remove(currFood);
            notifyDataSetChanged();
            return true;
        }
    }

}