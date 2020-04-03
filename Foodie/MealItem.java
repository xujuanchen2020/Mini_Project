package com.example.foodie_mp5;

public class MealItem {
    private String title;
    private String description;
    private String ingredient;
    private String calories;
    private String link;
    private int imagesource;

    public MealItem (String title, String description,
                     String ingredient, String calories,
                     String link, int imagesource){
        this.title = title;
        this.description = description;
        this.ingredient = ingredient;
        this.calories = calories;
        this.link = link;
        this.imagesource = imagesource;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getIngredient(){ return ingredient;}
    public String getCalories(){ return calories;}
    public String getLink(){ return link;}
    public int getImagesource(){
        return imagesource;
    }
}
