package com.coffeeshop.dao;

import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDAO {
    
    // 1. Самый молодой клиент
    public Map<String, Object> getYoungestCustomer() {
        String sql = "SELECT * FROM customers WHERE birth_date = (SELECT MAX(birth_date) FROM customers WHERE birth_date IS NOT NULL)";
        return getCustomerWithExtremeAge(sql, "Самый молодой клиент");
    }
    
    // 2. Самый возрастной клиент
    public Map<String, Object> getOldestCustomer() {
        String sql = "SELECT * FROM customers WHERE birth_date = (SELECT MIN(birth_date) FROM customers WHERE birth_date IS NOT NULL)";
        return getCustomerWithExtremeAge(sql, "Самый возрастной клиент");
    }
    
    // 3. Клиенты с днем рождения сегодня
    public List<Map<String, Object>> getCustomersWithBirthdayToday() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, birth_date FROM customers " +
                    "WHERE EXTRACT(MONTH FROM birth_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                    "AND EXTRACT(DAY FROM birth_date) = EXTRACT(DAY FROM CURRENT_DATE)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> customer = new HashMap<>();
                customer.put("customer_id", rs.getInt("customer_id"));
                customer.put("first_name", rs.getString("first_name"));
                customer.put("last_name", rs.getString("last_name"));
                customer.put("birth_date", rs.getDate("birth_date"));
                results.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиентов с днем рождения: " + e.getMessage());
        }
        return results;
    }
    
    // 4. Клиенты без адреса
    public List<Map<String, Object>> getCustomersWithoutAddress() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, email, phone FROM customers " +
                    "WHERE address IS NULL OR TRIM(address) = ''";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> customer = new HashMap<>();
                customer.put("customer_id", rs.getInt("customer_id"));
                customer.put("first_name", rs.getString("first_name"));
                customer.put("last_name", rs.getString("last_name"));
                customer.put("email", rs.getString("email"));
                customer.put("phone", rs.getString("phone"));
                results.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиентов без адреса: " + e.getMessage());
        }
        return results;
    }
    
    // 5. Информация о заказах в указанном промежутке дат
    public List<Map<String, Object>> getOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT o.order_id, o.order_date, o.total_amount, " +
                    "c.first_name || ' ' || c.last_name as customer_name, " +
                    "e.first_name || ' ' || e.last_name as employee_name " +
                    "FROM orders o " +
                    "JOIN customers c ON o.customer_id = c.customer_id " +
                    "JOIN employees e ON o.employee_id = e.employee_id " +
                    "WHERE DATE(o.order_date) BETWEEN ? AND ? " +
                    "ORDER BY o.order_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> order = new HashMap<>();
                    order.put("order_id", rs.getInt("order_id"));
                    order.put("order_date", rs.getTimestamp("order_date"));
                    order.put("total_amount", rs.getDouble("total_amount"));
                    order.put("customer_name", rs.getString("customer_name"));
                    order.put("employee_name", rs.getString("employee_name"));
                    results.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении заказов за период: " + e.getMessage());
        }
        return results;
    }
    
    // 6. Количество заказов десертов в конкретную дату
    public int getDessertOrdersCount(LocalDate date) {
        String sql = "SELECT COUNT(*) FROM order_desserts od " +
                    "JOIN orders o ON od.order_id = o.order_id " +
                    "WHERE DATE(o.order_date) = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении количества заказов десертов: " + e.getMessage());
        }
        return 0;
    }
    
    // 7. Количество заказов напитков в конкретную дату
    public int getDrinkOrdersCount(LocalDate date) {
        String sql = "SELECT COUNT(*) FROM order_drinks od " +
                    "JOIN orders o ON od.order_id = o.order_id " +
                    "WHERE DATE(o.order_date) = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении количества заказов напитков: " + e.getMessage());
        }
        return 0;
    }
    
    // 8. Клиенты, заказывавшие напитки и информация о баристах
    public List<Map<String, Object>> getCustomersWithDrinksAndBaristas() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT DISTINCT c.customer_id, c.first_name as customer_first, c.last_name as customer_last, " +
                    "e.employee_id, e.first_name as barista_first, e.last_name as barista_last, " +
                    "d.drink_name, o.order_date " +
                    "FROM customers c " +
                    "JOIN orders o ON c.customer_id = o.customer_id " +
                    "JOIN order_drinks od ON o.order_id = od.order_id " +
                    "JOIN drinks d ON od.drink_id = d.drink_id " +
                    "JOIN employees e ON o.employee_id = e.employee_id " +
                    "ORDER BY o.order_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("customer_id", rs.getInt("customer_id"));
                record.put("customer_name", rs.getString("customer_first") + " " + rs.getString("customer_last"));
                record.put("barista_id", rs.getInt("employee_id"));
                record.put("barista_name", rs.getString("barista_first") + " " + rs.getString("barista_last"));
                record.put("drink_name", rs.getString("drink_name"));
                record.put("order_date", rs.getTimestamp("order_date"));
                results.add(record);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении информации о клиентах и баристах: " + e.getMessage());
        }
        return results;
    }
    
    // 9. Средняя сумма заказа в конкретную дату
    public Double getAverageOrderAmount(LocalDate date) {
        String sql = "SELECT AVG(total_amount) FROM orders WHERE DATE(order_date) = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении средней суммы заказа: " + e.getMessage());
        }
        return 0.0;
    }
    
    // 10. Максимальная сумма заказа в конкретную дату
    public Double getMaxOrderAmount(LocalDate date) {
        String sql = "SELECT MAX(total_amount) FROM orders WHERE DATE(order_date) = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении максимальной суммы заказа: " + e.getMessage());
        }
        return 0.0;
    }
    
    // 11. Клиент с максимальной суммой заказа в конкретную дату
    public Map<String, Object> getCustomerWithMaxOrderAmount(LocalDate date) {
        Map<String, Object> result = new HashMap<>();
        String sql = "SELECT c.customer_id, c.first_name, c.last_name, o.total_amount " +
                    "FROM customers c " +
                    "JOIN orders o ON c.customer_id = o.customer_id " +
                    "WHERE DATE(o.order_date) = ? " +
                    "AND o.total_amount = (SELECT MAX(total_amount) FROM orders WHERE DATE(order_date) = ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            pstmt.setDate(2, Date.valueOf(date));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result.put("customer_id", rs.getInt("customer_id"));
                    result.put("first_name", rs.getString("first_name"));
                    result.put("last_name", rs.getString("last_name"));
                    result.put("total_amount", rs.getDouble("total_amount"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиента с максимальной суммой заказа: " + e.getMessage());
        }
        return result;
    }
    
    // 12. Расписание работы конкретного бариста на неделю
    public List<Map<String, Object>> getBaristaSchedule(int employeeId) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT s.day_of_week, s.opening_time, s.closing_time, " +
                    "cs.shop_name, cs.address " +
                    "FROM schedules s " +
                    "JOIN coffee_shops cs ON s.shop_id = cs.shop_id " +
                    "WHERE s.employee_id = ? " +
                    "ORDER BY s.day_of_week";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> schedule = new HashMap<>();
                    schedule.put("day_of_week", rs.getString("day_of_week"));
                    schedule.put("opening_time", rs.getTime("opening_time"));
                    schedule.put("closing_time", rs.getTime("closing_time"));
                    schedule.put("shop_name", rs.getString("shop_name"));
                    schedule.put("address", rs.getString("address"));
                    results.add(schedule);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении расписания бариста: " + e.getMessage());
        }
        return results;
    }
    
    // 13. Расписание работы всех баристов на неделю
    public List<Map<String, Object>> getAllBaristasSchedule() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, " +
                    "s.day_of_week, s.opening_time, s.closing_time, " +
                    "cs.shop_name " +
                    "FROM employees e " +
                    "JOIN schedules s ON e.employee_id = s.employee_id " +
                    "JOIN coffee_shops cs ON s.shop_id = cs.shop_id " +
                    "WHERE e.position = 'Бариста' " +
                    "ORDER BY e.last_name, e.first_name, s.day_of_week";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> schedule = new HashMap<>();
                schedule.put("employee_id", rs.getInt("employee_id"));
                schedule.put("employee_name", rs.getString("first_name") + " " + rs.getString("last_name"));
                schedule.put("day_of_week", rs.getString("day_of_week"));
                schedule.put("opening_time", rs.getTime("opening_time"));
                schedule.put("closing_time", rs.getTime("closing_time"));
                schedule.put("shop_name", rs.getString("shop_name"));
                results.add(schedule);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении расписания всех баристов: " + e.getMessage());
        }
        return results;
    }
    
    // Вспомогательный метод для получения клиента с крайним возрастом
    private Map<String, Object> getCustomerWithExtremeAge(String sql, String title) {
        Map<String, Object> result = new HashMap<>();
        result.put("title", title);
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                result.put("customer_id", rs.getInt("customer_id"));
                result.put("first_name", rs.getString("first_name"));
                result.put("last_name", rs.getString("last_name"));
                result.put("birth_date", rs.getDate("birth_date"));
                result.put("email", rs.getString("email"));
                result.put("phone", rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении клиента: " + e.getMessage());
        }
        return result;
    }
}