package com.coffeeshop.dao;

import com.coffeeshop.models.Dessert;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DessertDAO {
    
    // 1. Изменить название уже существующего десерта
    public boolean updateDessertName(int dessertId, String newName) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE desserts_catalog SET dessert_name = ? WHERE dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, dessertId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 2. Добавление нового десерта
    public int addNewDessert(Dessert dessert) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) " +
                    "VALUES (seq_desserts.NEXTVAL, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"dessert_id"})) {
            pstmt.setString(1, dessert.getDessertName());
            pstmt.setString(2, dessert.getDescription());
            pstmt.setString(3, dessert.getCategory());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 3. Получить все десерты
    public List<Dessert> getAllDesserts() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Dessert> desserts = new ArrayList<>();
        
        String sql = "SELECT * FROM desserts_catalog ORDER BY dessert_name";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Dessert dessert = new Dessert();
                dessert.setDessertId(rs.getInt("dessert_id"));
                dessert.setDessertName(rs.getString("dessert_name"));
                dessert.setDescription(rs.getString("description"));
                dessert.setCategory(rs.getString("category"));
                desserts.add(dessert);
            }
        }
        
        return desserts;
    }
    
    // 4. Удалить десерт
    public boolean deleteDessert(int dessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM desserts_catalog WHERE dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dessertId);
            return pstmt.executeUpdate() > 0;
        }
    }
}