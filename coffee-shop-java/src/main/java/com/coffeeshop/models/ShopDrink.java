package com.coffeeshop.models;

public class ShopDrink {
    private int shopDrinkId;
    private int shopId;
    private int drinkId;
    private double price;
    private char isAvailable;
    
    public ShopDrink() {}
    
    public ShopDrink(int shopId, int drinkId, double price) {
        this.shopId = shopId;
        this.drinkId = drinkId;
        this.price = price;
        this.isAvailable = 'Y';
    }
    
    // Геттеры и сеттеры
    public int getShopDrinkId() { return shopDrinkId; }
    public void setShopDrinkId(int shopDrinkId) { this.shopDrinkId = shopDrinkId; }
    
    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }
    
    public int getDrinkId() { return drinkId; }
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public char getIsAvailable() { return isAvailable; }
    public void setIsAvailable(char isAvailable) { this.isAvailable = isAvailable; }
    
    @Override
    public String toString() {
        return String.format("Напиток в кофейне [ID: %d, Цена: %.2f руб., Доступен: %s]", 
                shopDrinkId, price, isAvailable == 'Y' ? "Да" : "Нет");
    }
}