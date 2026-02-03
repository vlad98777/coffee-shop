package com.coffeeshop.dao;

import com.coffeeshop.models.ShopDrink;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDrinkDAO {
    
    // 1. Добавить напиток в кофейню
    public int addDrinkToShop(ShopDrink shopDrink) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        // Проверяем, есть ли уже такой напиток в этой кофейне
        String checkSql = "SELECT shop_drink_id FROM shop_drinks WHERE shop_id = ? AND drink_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, shopDrink.getShopId());
            checkStmt.setInt(2, shopDrink.getDrinkId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("shop_drink_id"); // Уже существует
            }
        }
        
        String sql = "INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) " +
                    "VALUES (seq_shop_drinks.NEXTVAL, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"shop_drink_id"})) {
            pstmt.setInt(1, shopDrink.getShopId());
            pstmt.setInt(2, shopDrink.getDrinkId());
            pstmt.setDouble(3, shopDrink.getPrice());
            pstmt.setString(4, String.valueOf(shopDrink.getIsAvailable()));
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Получить напитки конкретной кофейни
    public List<ShopDrink> getDrinksByShop(int shopId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<ShopDrink> shopDrinks = new ArrayList<>();
        
        String sql = "SELECT sd.*, dc.drink_name, dc.category " +
                    "FROM shop_drinks sd " +
                    "JOIN drinks_catalog dc ON sd.drink_id = dc.drink_id " +
                    "WHERE sd.shop_id = ? AND sd.is_available = 'Y' " +
                    "ORDER BY dc.drink_name";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ShopDrink shopDrink = new ShopDrink();
                shopDrink.setShopDrinkId(rs.getInt("shop_drink_id"));
                shopDrink.setShopId(rs.getInt("shop_id"));
                shopDrink.setDrinkId(rs.getInt("drink_id"));
                shopDrink.setPrice(rs.getDouble("price"));
                shopDrink.setIsAvailable(rs.getString("is_available").charAt(0));
                
                shopDrinks.add(shopDrink);
            }
        }
        
        return shopDrinks;
    }
    
    // 3. Получить конкретный напиток в кофейне
    public ShopDrink getShopDrinkById(int shopDrinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM shop_drinks WHERE shop_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopDrinkId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                ShopDrink shopDrink = new ShopDrink();
                shopDrink.setShopDrinkId(rs.getInt("shop_drink_id"));
                shopDrink.setShopId(rs.getInt("shop_id"));
                shopDrink.setDrinkId(rs.getInt("drink_id"));
                shopDrink.setPrice(rs.getDouble("price"));
                shopDrink.setIsAvailable(rs.getString("is_available").charAt(0));
                return shopDrink;
            }
        }
        
        return null;
    }
    
    // 4. Обновить цену напитка в кофейне
    public boolean updateDrinkPrice(int shopDrinkId, double newPrice) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE shop_drinks SET price = ? WHERE shop_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, shopDrinkId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 5. Изменить доступность напитка
    public boolean setDrinkAvailability(int shopDrinkId, boolean isAvailable) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE shop_drinks SET is_available = ? WHERE shop_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isAvailable ? "Y" : "N");
            pstmt.setInt(2, shopDrinkId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 6. Удалить напиток из кофейни
    public boolean deleteShopDrink(int shopDrinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM shop_drinks WHERE shop_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopDrinkId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 7. Получить цену напитка в кофейне
    public double getDrinkPrice(int shopId, int drinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT price FROM shop_drinks WHERE shop_id = ? AND drink_id = ? AND is_available = 'Y'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setInt(2, drinkId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("price");
            }
        }
        
        return 0.0;
    }
    
    // 8. Получить все кофейни, где есть определенный напиток
    public List<Integer> getShopsWithDrink(int drinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Integer> shopIds = new ArrayList<>();
        
        String sql = "SELECT DISTINCT shop_id FROM shop_drinks WHERE drink_id = ? AND is_available = 'Y'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drinkId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                shopIds.add(rs.getInt("shop_id"));
            }
        }
        
        return shopIds;
    }
}