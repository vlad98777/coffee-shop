package com.coffeeshop.dao;

import com.coffeeshop.models.Customer;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    // 1. Добавление нового клиента
    public int addCustomer(Customer customer) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO customers (customer_id, first_name, last_name, email, phone, address) " +
                    "VALUES (seq_customers.NEXTVAL, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"customer_id"})) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getAddress());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Удаление клиента
    public boolean deleteCustomer(int customerId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 3. Получение клиента по ID
    public Customer getCustomerById(int customerId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
        }
        
        return null;
    }
    
    // 4. Получение клиента по телефону
    public Customer getCustomerByPhone(String phone) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM customers WHERE phone = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
        }
        
        return null;
    }
    
    // 5. Получение всех клиентов
    public List<Customer> getAllCustomers() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Customer> customers = new ArrayList<>();
        
        String sql = "SELECT * FROM customers ORDER BY last_name, first_name";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        }
        
        return customers;
    }
}