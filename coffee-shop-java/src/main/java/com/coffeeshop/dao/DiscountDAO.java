package com.coffeeshop.dao;

import com.coffeeshop.DatabaseConnection;
import com.coffeeshop.models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {
    
    // 1. Минимальная скидка
    public Double getMinDiscount() {
        String sql = "SELECT MIN(discount) FROM customers WHERE discount IS NOT NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении минимальной скидки: " + e.getMessage());
        }
        return 0.0;
    }
    
    // 2. Максимальная скидка
    public Double getMaxDiscount() {
        String sql = "SELECT MAX(discount) FROM customers WHERE discount IS NOT NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении максимальной скидки: " + e.getMessage());
        }
        return 0.0;
    }
    
    // 3. Клиенты с минимальной скидкой
    public List<Customer> getCustomersWithMinDiscount() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE discount = (SELECT MIN(discount) FROM customers WHERE discount IS NOT NULL)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиентов с минимальной скидкой: " + e.getMessage());
        }
        return customers;
    }
    
    // 4. Клиенты с максимальной скидкой
    public List<Customer> getCustomersWithMaxDiscount() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE discount = (SELECT MAX(discount) FROM customers WHERE discount IS NOT NULL)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиентов с максимальной скидкой: " + e.getMessage());
        }
        return customers;
    }
    
    // 5. Средняя величина скидки
    public Double getAverageDiscount() {
        String sql = "SELECT AVG(discount) FROM customers WHERE discount IS NOT NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении средней скидки: " + e.getMessage());
        }
        return 0.0;
    }
    
    // Вспомогательный метод для извлечения Customer из ResultSet
    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        
        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            customer.setBirthDate(birthDate.toLocalDate());
        }
        
        customer.setAddress(rs.getString("address"));
        customer.setDiscount(rs.getDouble("discount"));
        
        return customer;
    }
}