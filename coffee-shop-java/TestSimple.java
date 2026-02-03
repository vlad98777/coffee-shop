import java.sql.*;

public class TestSimple {
    public static void main(String[] args) {
        System.out.println("=== ПРОСТОЙ ТЕСТ КОФЕЙНИ ===");
        
        try {
            // 1. Подключаемся к H2
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:coffeeshop;DB_CLOSE_DELAY=-1", "sa", "");
            
            // 2. Создаем таблицу и тестовые данные
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS coffee_shops (" +
                        "shop_id INT PRIMARY KEY, " +
                        "shop_name VARCHAR(100), " +
                        "address VARCHAR(200))");
            
            stmt.execute("DELETE FROM coffee_shops");
            stmt.execute("INSERT INTO coffee_shops VALUES (1, 'Кофейня №1', 'ул. Центральная, 1')");
            stmt.execute("INSERT INTO coffee_shops VALUES (2, 'Coffee Time', 'пр. Мира, 25')");
            stmt.execute("INSERT INTO coffee_shops VALUES (3, 'Кофе & Книги', 'ул. Гагарина, 7')");
            
            // 3. Показываем все кофейни
            ResultSet rs = stmt.executeQuery("SELECT * FROM coffee_shops ORDER BY shop_name");
            
            System.out.println("\n=== СПИСОК КОФЕЕН ===");
            while (rs.next()) {
                System.out.println(rs.getInt("shop_id") + ". " + 
                                 rs.getString("shop_name") + " - " +
                                 rs.getString("address"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            System.out.println("\n✅ Тест завершен успешно!");
            
        } catch (Exception e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
}