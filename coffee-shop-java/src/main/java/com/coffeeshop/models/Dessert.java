package com.coffeeshop.models;

public class Dessert {
    private int dessertId;
    private String dessertName;
    private String description;
    private String category;
    
    public Dessert() {}
    
    public Dessert(String dessertName, String description, String category) {
        this.dessertName = dessertName;
        this.description = description;
        this.category = category;
    }
    
    public int getDessertId() { return dessertId; }
    public void setDessertId(int dessertId) { this.dessertId = dessertId; }
    
    public String getDessertName() { return dessertName; }
    public void setDessertName(String dessertName) { this.dessertName = dessertName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", dessertName, category);
    }
}