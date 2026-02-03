package com.coffeeshop.dao;

import com.coffeeshop.models.*;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    
    // 1. Добавление информации о новом заказе кофе
    public int addCoffeeOrder(Order order, List<OrderDrink> coffeeItems) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        int orderId = -1;
        
        try {
            conn.setAutoCommit(false);
            
            String sql = "INSERT INTO orders (order_id, shop_id, employee_id, " +
                        "order_date, total_amount, status, customer_id) " +
                        "VALUES (seq_orders.NEXTVAL, ?, ?, SYSTIMESTAMP, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                    new String[]{"order_id"})) {
                pstmt.setInt(1, order.getShopId());
                pstmt.setInt(2, order.getEmployeeId());
                pstmt.setDouble(3, order.getTotalAmount());
                pstmt.setString(4, order.getStatus());
                if (order.getCustomerId() != null) {
                    pstmt.setInt(5, order.getCustomerId());
                } else {
                    pstmt.setNull(5, Types.NUMERIC);
                }
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    
                    // Добавляем позиции напитков
                    OrderDrinkDAO orderDrinkDAO = new OrderDrinkDAO();
                    for (OrderDrink item : coffeeItems) {
                        item.setOrderId(orderId);
                        orderDrinkDAO.addOrderDrink(item);
                    }
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        
        return orderId;
    }
    
    // 2. Добавление информации о новом заказе десерта
    public int addDessertOrder(Order order, List<OrderDessert> dessertItems) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        int orderId = -1;
        
        try {
            conn.setAutoCommit(false);
            
            String sql = "INSERT INTO orders (order_id, shop_id, employee_id, " +
                        "order_date, total_amount, status, customer_id) " +
                        "VALUES (seq_orders.NEXTVAL, ?, ?, SYSTIMESTAMP, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                    new String[]{"order_id"})) {
                pstmt.setInt(1, order.getShopId());
                pstmt.setInt(2, order.getEmployeeId());
                pstmt.setDouble(3, order.getTotalAmount());
                pstmt.setString(4, order.getStatus());
                if (order.getCustomerId() != null) {
                    pstmt.setInt(5, order.getCustomerId());
                } else {
                    pstmt.setNull(5, Types.NUMERIC);
                }
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    
                    // Добавляем позиции десертов
                    OrderDessertDAO orderDessertDAO = new OrderDessertDAO();
                    for (OrderDessert item : dessertItems) {
                        item.setOrderId(orderId);
                        orderDessertDAO.addOrderDessert(item);
                    }
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        
        return orderId;
    }
    
    // 3. Изменить информацию о заказе
    public boolean updateOrder(Order order) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE orders SET shop_id = ?, employee_id = ?, total_amount = ?, " +
                    "status = ?, customer_id = ? WHERE order_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getShopId());
            pstmt.setInt(2, order.getEmployeeId());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            if (order.getCustomerId() != null) {
                pstmt.setInt(5, order.getCustomerId());
            } else {
                pstmt.setNull(5, Types.NUMERIC);
            }
            pstmt.setInt(6, order.getOrderId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 4. Удалить конкретный заказ
    public boolean deleteOrder(int orderId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        try {
            conn.setAutoCommit(false);
            
            // Удаляем детали заказа (напитки)
            String deleteDrinks = "DELETE FROM order_drinks WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteDrinks)) {
                pstmt.setInt(1, orderId);
                pstmt.executeUpdate();
            }
            
            // Удаляем детали заказа (десерты)
            String deleteDesserts = "DELETE FROM order_desserts WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteDesserts)) {
                pstmt.setInt(1, orderId);
                pstmt.executeUpdate();
            }
            
            // Удаляем сам заказ
            String deleteOrder = "DELETE FROM orders WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteOrder)) {
                pstmt.setInt(1, orderId);
                int affectedRows = pstmt.executeUpdate();
                
                conn.commit();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
    
    // 5. Удалить заказы конкретного десерта
    public int deleteOrdersByDessert(int dessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        // Находим все заказы, содержащие этот десерт
        String findOrdersSql = "SELECT DISTINCT o.order_id " +
                              "FROM orders o " +
                              "JOIN order_desserts od ON o.order_id = od.order_id " +
                              "JOIN shop_desserts sd ON od.shop_dessert_id = sd.shop_dessert_id " +
                              "WHERE sd.dessert_id = ?";
        
        List<Integer> orderIds = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(findOrdersSql)) {
            pstmt.setInt(1, dessertId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        }
        
        // Удаляем найденные заказы
        int deletedCount = 0;
        for (int orderId : orderIds) {
            if (deleteOrder(orderId)) {
                deletedCount++;
            }
        }
        
        return deletedCount;
    }
    
    // 6. Показать все заказы конкретного десерта
    public List<Order> getOrdersByDessert(int dessertId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Order> orders = new ArrayList<>();
        
        String sql = "SELECT DISTINCT o.* " +
                    "FROM orders o " +
                    "JOIN order_desserts od ON o.order_id = od.order_id " +
                    "JOIN shop_desserts sd ON od.shop_dessert_id = sd.shop_dessert_id " +
                    "WHERE sd.dessert_id = ? " +
                    "ORDER BY o.order_date DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dessertId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = mapOrderFromResultSet(rs);
                orders.add(order);
            }
        }
        
        return orders;
    }
    
    // 7. Показать все заказы конкретного официанта
    public List<Order> getOrdersByWaiter(int employeeId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Order> orders = new ArrayList<>();
        
        String sql = "SELECT o.* FROM orders o " +
                    "WHERE o.employee_id = ? " +
                    "ORDER BY o.order_date DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = mapOrderFromResultSet(rs);
                orders.add(order);
            }
        }
        
        return orders;
    }
    
    // 8. Показать все заказы конкретного клиента (по ID)
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Order> orders = new ArrayList<>();
        
        String sql = "SELECT o.* FROM orders o " +
                    "WHERE o.customer_id = ? " +
                    "ORDER BY o.order_date DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = mapOrderFromResultSet(rs);
                orders.add(order);
            }
        }
        
        return orders;
    }
    
    // 9. Показать все заказы конкретного клиента (по телефону)
    public List<Order> getOrdersByCustomerPhone(String phone) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Order> orders = new ArrayList<>();
        
        String sql = "SELECT o.* FROM orders o " +
                    "JOIN customers c ON o.customer_id = c.customer_id " +
                    "WHERE c.phone = ? " +
                    "ORDER BY o.order_date DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = mapOrderFromResultSet(rs);
                orders.add(order);
            }
        }
        
        return orders;
    }
    
    // 10. Получить заказ по ID
    public Order getOrderById(int orderId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapOrderFromResultSet(rs);
            }
        }
        
        return null;
    }
    
    // Вспомогательный метод для маппинга Order из ResultSet
private Order mapOrderFromResultSet(ResultSet rs) throws SQLException {
    Order order = new Order();
    order.setOrderId(rs.getInt("order_id"));
    order.setShopId(rs.getInt("shop_id"));
    order.setEmployeeId(rs.getInt("employee_id"));
    
    // Исправляем конвертацию Timestamp в LocalDateTime
    Timestamp timestamp = rs.getTimestamp("order_date");
    if (timestamp != null) {
        order.setOrderDate(timestamp.toLocalDateTime());
    }
    
    order.setTotalAmount(rs.getDouble("total_amount"));
    order.setStatus(rs.getString("status"));
    
    int customerId = rs.getInt("customer_id");
    if (!rs.wasNull()) {
        order.setCustomerId(customerId);
    }
    
    return order;
}
}