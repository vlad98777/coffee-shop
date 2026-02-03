package com.coffeeshop.models;

public class ShopDessert {
    private int shopDessertId;
    private int shopId;
    private int dessertId;
    private double price;
    private char isAvailable;
    
    public ShopDessert() {}
    
    public ShopDessert(int shopId, int dessertId, double price) {
        this.shopId = shopId;
        this.dessertId = dessertId;
        this.price = price;
        this.isAvailable = 'Y';
    }
    
    // Геттеры и сеттеры
    public int getShopDessertId() { return shopDessertId; }
    public void setShopDessertId(int shopDessertId) { this.shopDessertId = shopDessertId; }
    
    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }
    
    public int getDessertId() { return dessertId; }
    public void setDessertId(int dessertId) { this.dessertId = dessertId; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public char getIsAvailable() { return isAvailable; }
    public void setIsAvailable(char isAvailable) { this.isAvailable = isAvailable; }
    
    @Override
    public String toString() {
        return String.format("Десерт в кофейне [ID: %d, Цена: %.2f руб., Доступен: %s]", 
                shopDessertId, price, isAvailable == 'Y' ? "Да" : "Нет");
    }
}