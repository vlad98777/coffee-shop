package com.coffeeshop.dao;

import com.coffeeshop.models.OrderDrink;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDrinkDAO {
    
    // 1. Добавить напиток в заказ
    public int addOrderDrink(OrderDrink orderDrink) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, " +
                    "quantity, price_per_unit, subtotal) " +
                    "VALUES (seq_order_drinks.NEXTVAL, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"order_drink_id"})) {
            pstmt.setInt(1, orderDrink.getOrderId());
            pstmt.setInt(2, orderDrink.getShopDrinkId());
            pstmt.setInt(3, orderDrink.getQuantity());
            pstmt.setDouble(4, orderDrink.getPricePerUnit());
            pstmt.setDouble(5, orderDrink.getSubtotal());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Получить напитки по ID заказа
    public List<OrderDrink> getOrderDrinksByOrderId(int orderId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<OrderDrink> orderDrinks = new ArrayList<>();
        
        String sql = "SELECT od.*, dc.drink_name, sd.price " +
                    "FROM order_drinks od " +
                    "JOIN shop_drinks sd ON od.shop_drink_id = sd.shop_drink_id " +
                    "JOIN drinks_catalog dc ON sd.drink_id = dc.drink_id " +
                    "WHERE od.order_id = ? " +
                    "ORDER BY od.order_drink_id";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                OrderDrink orderDrink = new OrderDrink();
                orderDrink.setOrderDrinkId(rs.getInt("order_drink_id"));
                orderDrink.setOrderId(rs.getInt("order_id"));
                orderDrink.setShopDrinkId(rs.getInt("shop_drink_id"));
                orderDrink.setQuantity(rs.getInt("quantity"));
                orderDrink.setPricePerUnit(rs.getDouble("price_per_unit"));
                orderDrink.setSubtotal(rs.getDouble("subtotal"));
                
                orderDrinks.add(orderDrink);
            }
        }
        
        return orderDrinks;
    }
    
    // 3. Удалить напиток из заказа
    public boolean deleteOrderDrink(int orderDrinkId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM order_drinks WHERE order_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderDrinkId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 4. Обновить количество напитка в заказе
    public boolean updateOrderDrinkQuantity(int orderDrinkId, int newQuantity, double newPrice) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE order_drinks SET quantity = ?, price_per_unit = ?, subtotal = ? " +
                    "WHERE order_drink_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setDouble(2, newPrice);
            pstmt.setDouble(3, newQuantity * newPrice);
            pstmt.setInt(4, orderDrinkId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 5. Получить статистику по напиткам в заказах
    public List<String> getDrinkStatistics() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<String> statistics = new ArrayList<>();
        
        String sql = "SELECT dc.drink_name, SUM(od.quantity) as total_quantity, " +
                    "SUM(od.subtotal) as total_revenue " +
                    "FROM order_drinks od " +
                    "JOIN shop_drinks sd ON od.shop_drink_id = sd.shop_drink_id " +
                    "JOIN drinks_catalog dc ON sd.drink_id = dc.drink_id " +
                    "GROUP BY dc.drink_name " +
                    "ORDER BY total_quantity DESC";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String stat = String.format("%s: %d порций, %.2f руб.", 
                    rs.getString("drink_name"),
                    rs.getInt("total_quantity"),
                    rs.getDouble("total_revenue"));
                statistics.add(stat);
            }
        }
        
        return statistics;
    }
    
    // 6. Получить общее количество проданных напитков
    public int getTotalDrinksSold() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT SUM(quantity) as total FROM order_drinks";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        
        return 0;
    }
    
    // 7. Получить общую выручку от напитков
    public double getTotalDrinksRevenue() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT SUM(subtotal) as total FROM order_drinks";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        
        return 0.0;
    }
    
    // 8. Удалить все напитки из заказа
    public boolean deleteAllDrinksFromOrder(int orderId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM order_drinks WHERE order_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate() > 0;
        }
    }
}