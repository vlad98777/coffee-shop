package com.coffeeshop.models;

public class OrderDessert {
    private int orderDessertId;
    private int orderId;
    private int shopDessertId;
    private int quantity;
    private double pricePerUnit;
    private double subtotal;
    
    public OrderDessert() {}
    
    public int getOrderDessertId() { return orderDessertId; }
    public void setOrderDessertId(int orderDessertId) { this.orderDessertId = orderDessertId; }
    
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getShopDessertId() { return shopDessertId; }
    public void setShopDessertId(int shopDessertId) { this.shopDessertId = shopDessertId; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}