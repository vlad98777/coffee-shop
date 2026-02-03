package com.coffeeshop.models;

public class OrderDrink {
    private int orderDrinkId;
    private int orderId;
    private int shopDrinkId;
    private int quantity;
    private double pricePerUnit;
    private double subtotal;
    
    public OrderDrink() {}
    
    public int getOrderDrinkId() { return orderDrinkId; }
    public void setOrderDrinkId(int orderDrinkId) { this.orderDrinkId = orderDrinkId; }
    
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getShopDrinkId() { return shopDrinkId; }
    public void setShopDrinkId(int shopDrinkId) { this.shopDrinkId = shopDrinkId; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}