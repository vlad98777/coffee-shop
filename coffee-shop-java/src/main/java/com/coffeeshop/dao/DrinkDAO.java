package com.coffeeshop.dao;

import com.coffeeshop.models.Drink;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {
    
    // 1. Добавление информации о новом кофе
    public int addNewDrink(Drink drink) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO drinks_catalog (drink_id, drink_name, description, category) " +
                    "VALUES (seq_drinks.NEXTVAL, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"drink_id"})) {
            pstmt.setString(1, drink.getDrinkName());
            pstmt.setString(2, drink.getDescription());
            pstmt.setString(3, drink.getCategory());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Изменить название уже существующего вида кофе
    public boolean updateDrinkName(int drinkId, String newName) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE drinks_catalog SET drink_name = ? WHERE drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, drinkId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 3. Получить все напитки
    public List<Drink> getAllDrinks() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Drink> drinks = new ArrayList<>();
        
        String sql = "SELECT * FROM drinks_catalog ORDER BY drink_name";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Drink drink = new Drink();
                drink.setDrinkId(rs.getInt("drink_id"));
                drink.setDrinkName(rs.getString("drink_name"));
                drink.setDescription(rs.getString("description"));
                drink.setCategory(rs.getString("category"));
                drinks.add(drink);
            }
        }
        
        return drinks;
    }
    
    // 4. Поиск напитка по ID
    public Drink getDrinkById(int drinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM drinks_catalog WHERE drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drinkId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Drink drink = new Drink();
                drink.setDrinkId(rs.getInt("drink_id"));
                drink.setDrinkName(rs.getString("drink_name"));
                drink.setDescription(rs.getString("description"));
                drink.setCategory(rs.getString("category"));
                return drink;
            }
        }
        
        return null;
    }
    
    // 5. Удалить напиток
    public boolean deleteDrink(int drinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM drinks_catalog WHERE drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drinkId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 6. Добавить напиток в кофейню
    public boolean addDrinkToShop(int shopId, int drinkId, double price) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) " +
                    "VALUES (seq_shop_drinks.NEXTVAL, ?, ?, ?, 'Y')";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setInt(2, drinkId);
            pstmt.setDouble(3, price);
            return pstmt.executeUpdate() > 0;
        }
    }
}