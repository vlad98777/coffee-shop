package com.coffeeshop.dao;

import com.coffeeshop.models.CoffeeShop;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopDAO {
    
    // 1. Добавление новой кофейни
    public int addCoffeeShop(CoffeeShop shop) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) " +
                    "VALUES (seq_shops.NEXTVAL, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"shop_id"})) {
            pstmt.setString(1, shop.getShopName());
            pstmt.setString(2, shop.getAddress());
            pstmt.setString(3, shop.getPhone());
            pstmt.setDate(4, new Date(shop.getOpeningDate().getTime()));
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Удаление кофейни
    public boolean deleteCoffeeShop(int shopId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM coffee_shops WHERE shop_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 3. Получение всех кофеен
    public List<CoffeeShop> getAllCoffeeShops() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<CoffeeShop> shops = new ArrayList<>();
        
        String sql = "SELECT * FROM coffee_shops ORDER BY shop_name";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                CoffeeShop shop = new CoffeeShop();
                shop.setShopId(rs.getInt("shop_id"));
                shop.setShopName(rs.getString("shop_name"));
                shop.setAddress(rs.getString("address"));
                shop.setPhone(rs.getString("phone"));
                shop.setOpeningDate(rs.getDate("opening_date"));
                shop.setManagerId(rs.getInt("manager_id"));
                shops.add(shop);
            }
        }
        
        return shops;
    }
    
    // 4. Обновление кофейни
    public boolean updateCoffeeShop(CoffeeShop shop) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE coffee_shops SET shop_name = ?, address = ?, phone = ?, " +
                    "opening_date = ?, manager_id = ? WHERE shop_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, shop.getShopName());
            pstmt.setString(2, shop.getAddress());
            pstmt.setString(3, shop.getPhone());
            pstmt.setDate(4, new Date(shop.getOpeningDate().getTime()));
            if (shop.getManagerId() != null) {
                pstmt.setInt(5, shop.getManagerId());
            } else {
                pstmt.setNull(5, Types.NUMERIC);
            }
            pstmt.setInt(6, shop.getShopId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
}
