package com.coffeeshop.models;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private Integer customerId;
    private int employeeId;
    private int shopId;        // Добавить
    private String status;     // Добавить
    private LocalDateTime orderDate;
    private Double totalAmount;
    
    // Конструкторы
    public Order() {}
    
    public Order(int orderId, Integer customerId, int employeeId, int shopId, 
                String status, LocalDateTime orderDate, Double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.shopId = shopId;      // Добавить
        this.status = status;      // Добавить
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    
    // Геттеры и сеттеры
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    
    public int getShopId() { return shopId; }               // Добавить
    public void setShopId(int shopId) { this.shopId = shopId; }  // Добавить
    
    public String getStatus() { return status; }            // Добавить
    public void setStatus(String status) { this.status = status; }  // Добавить
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    
    @Override
    public String toString() {
        return String.format("Заказ #%d: Клиент ID=%s, Бариста ID=%d, Кофейня ID=%d, " +
                           "Статус: %s, Дата: %s, Сумма: %.2f руб.",
                           orderId, 
                           customerId != null ? customerId.toString() : "не указан",
                           employeeId,
                           shopId,
                           status != null ? status : "не указан",
                           orderDate != null ? orderDate.toString() : "не указана",
                           totalAmount != null ? totalAmount : 0.0);
    }
}