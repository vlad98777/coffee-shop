package com.coffeeshop.models;

import java.time.LocalDate;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String address;
    private Double discount;
    
    // Конструкторы
    public Customer() {}
    
    public Customer(int customerId, String firstName, String lastName, 
                   String email, String phone, LocalDate birthDate, 
                   String address, Double discount) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.discount = discount;
    }
    
    // Геттеры и сеттеры
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }
    
    @Override
    public String toString() {
        return String.format("Клиент #%d: %s %s, Email: %s, Телефон: %s, " +
                           "Дата рождения: %s, Адрес: %s, Скидка: %.1f%%",
                           customerId, firstName, lastName, email, phone,
                           birthDate != null ? birthDate.toString() : "не указана",
                           address != null ? address : "не указан",
                           discount != null ? discount : 0.0);
    }
}