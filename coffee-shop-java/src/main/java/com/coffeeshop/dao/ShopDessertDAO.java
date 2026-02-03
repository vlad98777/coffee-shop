package com.coffeeshop.dao;

import com.coffeeshop.models.ShopDessert;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDessertDAO {
    
    // 1. Добавить десерт в кофейню
    public int addDessertToShop(ShopDessert shopDessert) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        // Проверяем, есть ли уже такой десерт в этой кофейне
        String checkSql = "SELECT shop_dessert_id FROM shop_desserts WHERE shop_id = ? AND dessert_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, shopDessert.getShopId());
            checkStmt.setInt(2, shopDessert.getDessertId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("shop_dessert_id"); // Уже существует
            }
        }
        
        String sql = "INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) " +
                    "VALUES (seq_shop_desserts.NEXTVAL, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"shop_dessert_id"})) {
            pstmt.setInt(1, shopDessert.getShopId());
            pstmt.setInt(2, shopDessert.getDessertId());
            pstmt.setDouble(3, shopDessert.getPrice());
            pstmt.setString(4, String.valueOf(shopDessert.getIsAvailable()));
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Получить десерты конкретной кофейни
    public List<ShopDessert> getDessertsByShop(int shopId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<ShopDessert> shopDesserts = new ArrayList<>();
        
        String sql = "SELECT sd.*, dc.dessert_name, dc.category " +
                    "FROM shop_desserts sd " +
                    "JOIN desserts_catalog dc ON sd.dessert_id = dc.dessert_id " +
                    "WHERE sd.shop_id = ? AND sd.is_available = 'Y' " +
                    "ORDER BY dc.dessert_name";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ShopDessert shopDessert = new ShopDessert();
                shopDessert.setShopDessertId(rs.getInt("shop_dessert_id"));
                shopDessert.setShopId(rs.getInt("shop_id"));
                shopDessert.setDessertId(rs.getInt("dessert_id"));
                shopDessert.setPrice(rs.getDouble("price"));
                shopDessert.setIsAvailable(rs.getString("is_available").charAt(0));
                
                shopDesserts.add(shopDessert);
            }
        }
        
        return shopDesserts;
    }
    
    // 3. Получить конкретный десерт в кофейне
    public ShopDessert getShopDessertById(int shopDessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM shop_desserts WHERE shop_dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopDessertId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                ShopDessert shopDessert = new ShopDessert();
                shopDessert.setShopDessertId(rs.getInt("shop_dessert_id"));
                shopDessert.setShopId(rs.getInt("shop_id"));
                shopDessert.setDessertId(rs.getInt("dessert_id"));
                shopDessert.setPrice(rs.getDouble("price"));
                shopDessert.setIsAvailable(rs.getString("is_available").charAt(0));
                return shopDessert;
            }
        }
        
        return null;
    }
    
    // 4. Обновить цену десерта в кофейне
    public boolean updateDessertPrice(int shopDessertId, double newPrice) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE shop_desserts SET price = ? WHERE shop_dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, shopDessertId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 5. Изменить доступность десерта
    public boolean setDessertAvailability(int shopDessertId, boolean isAvailable) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE shop_desserts SET is_available = ? WHERE shop_dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isAvailable ? "Y" : "N");
            pstmt.setInt(2, shopDessertId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 6. Удалить десерт из кофейни
    public boolean deleteShopDessert(int shopDessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM shop_desserts WHERE shop_dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopDessertId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 7. Получить цену десерта в кофейне
    public double getDessertPrice(int shopId, int dessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT price FROM shop_desserts WHERE shop_id = ? AND dessert_id = ? AND is_available = 'Y'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setInt(2, dessertId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("price");
            }
        }
        
        return 0.0;
    }
    
    // 8. Получить все кофейни, где есть определенный десерт
    public List<Integer> getShopsWithDessert(int dessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Integer> shopIds = new ArrayList<>();
        
        String sql = "SELECT DISTINCT shop_id FROM shop_desserts WHERE dessert_id = ? AND is_available = 'Y'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dessertId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                shopIds.add(rs.getInt("shop_id"));
            }
        }
        
        return shopIds;
    }
}