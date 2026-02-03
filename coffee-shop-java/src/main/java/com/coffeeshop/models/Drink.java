package com.coffeeshop.models;

public class Drink {
    private int drinkId;
    private String drinkName;
    private String description;
    private String category;
    
    public Drink() {}
    
    public Drink(String drinkName, String description, String category) {
        this.drinkName = drinkName;
        this.description = description;
        this.category = category;
    }
    
    public int getDrinkId() { return drinkId; }
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    
    public String getDrinkName() { return drinkName; }
    public void setDrinkName(String drinkName) { this.drinkName = drinkName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - %s", drinkName, category, description);
    }
}