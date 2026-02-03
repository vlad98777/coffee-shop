package com.coffeeshop.dao;

import com.coffeeshop.models.OrderDessert;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;

public class OrderDessertDAO {
    
    public boolean addOrderDessert(OrderDessert orderDessert) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, " +
                    "quantity, price_per_unit, subtotal) " +
                    "VALUES (seq_order_desserts.NEXTVAL, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderDessert.getOrderId());
            pstmt.setInt(2, orderDessert.getShopDessertId());
            pstmt.setInt(3, orderDessert.getQuantity());
            pstmt.setDouble(4, orderDessert.getPricePerUnit());
            pstmt.setDouble(5, orderDessert.getSubtotal());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean deleteOrderDessert(int orderDessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM order_desserts WHERE order_dessert_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderDessertId);
            return pstmt.executeUpdate() > 0;
        }
    }
}