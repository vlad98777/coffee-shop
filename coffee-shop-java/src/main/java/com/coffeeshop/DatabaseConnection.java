package com.coffeeshop;

import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    // Файловая база данных для сохранения между запусками
    private static final String H2_URL = "jdbc:h2:file:./data/coffeeshop;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(H2_URL, USER, PASSWORD);
            System.out.println("✓ H2 база данных подключена");
        } catch (Exception e) {
            System.err.println("✗ Ошибка подключения к H2: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public static void initializeH2Database() {
        try (Statement stmt = connection.createStatement()) {
            System.out.println("\n📁 Инициализация базы данных...");
            
            // Создаем таблицы если не существуют
            createTables(stmt);
            
            // Проверяем и добавляем тестовые данные
            checkAndAddTestData(stmt);
            
            System.out.println("✅ База данных готова к работе!\n");
            
        } catch (Exception e) {
            System.err.println("Ошибка при инициализации БД: " + e.getMessage());
        }
    }
    
    private static void createTables(Statement stmt) throws SQLException {
        System.out.println("Создание таблиц...");
        
        // Кофейни
        stmt.execute("CREATE TABLE IF NOT EXISTS coffee_shops (" +
                    "shop_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "shop_name VARCHAR(100) NOT NULL, " +
                    "address VARCHAR(200), " +
                    "phone VARCHAR(20), " +
                    "opening_date DATE, " +
                    "manager_id INT)");
        
        // Клиенты
        stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "customer_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(100), " +
                    "phone VARCHAR(20), " +
                    "birth_date DATE, " +
                    "address VARCHAR(200), " +
                    "discount DECIMAL(5,2) DEFAULT 0)");
        
        // Сотрудники
        stmt.execute("CREATE TABLE IF NOT EXISTS employees (" +
                    "employee_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(50) NOT NULL, " +
                    "position VARCHAR(50), " +
                    "hire_date DATE, " +
                    "salary DECIMAL(10,2), " +
                    "shop_id INT, " +
                    "email VARCHAR(100))");
        
        // Напитки (каталог)
        stmt.execute("CREATE TABLE IF NOT EXISTS drinks_catalog (" +
                    "drink_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "drink_name VARCHAR(100) NOT NULL, " +
                    "description VARCHAR(200), " +
                    "category VARCHAR(50))");
        
        // Десерты (каталог)
        stmt.execute("CREATE TABLE IF NOT EXISTS desserts_catalog (" +
                    "dessert_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "dessert_name VARCHAR(100) NOT NULL, " +
                    "description VARCHAR(200), " +
                    "category VARCHAR(50))");
        
        // Заказы
        stmt.execute("CREATE TABLE IF NOT EXISTS orders (" +
                    "order_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "shop_id INT NOT NULL, " +
                    "employee_id INT NOT NULL, " +
                    "customer_id INT, " +
                    "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "total_amount DECIMAL(10,2), " +
                    "status VARCHAR(20) DEFAULT 'NEW')");
        
        // Напитки в заказах
        stmt.execute("CREATE TABLE IF NOT EXISTS order_drinks (" +
                    "order_drink_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "order_id INT NOT NULL, " +
                    "drink_id INT NOT NULL, " +
                    "quantity INT, " +
                    "price_per_unit DECIMAL(10,2), " +
                    "subtotal DECIMAL(10,2))");
        
        // Десерты в заказах
        stmt.execute("CREATE TABLE IF NOT EXISTS order_desserts (" +
                    "order_dessert_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "order_id INT NOT NULL, " +
                    "dessert_id INT NOT NULL, " +
                    "quantity INT, " +
                    "price_per_unit DECIMAL(10,2), " +
                    "subtotal DECIMAL(10,2))");
        
        // Расписание
        stmt.execute("CREATE TABLE IF NOT EXISTS schedules (" +
                    "schedule_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "shop_id INT NOT NULL, " +
                    "employee_id INT NOT NULL, " +
                    "work_date DATE, " +
                    "day_of_week VARCHAR(20), " +
                    "opening_time TIME, " +
                    "closing_time TIME, " +
                    "notes VARCHAR(200))");
        
        System.out.println("✅ Таблицы созданы/проверены");
    }
    
    private static void checkAndAddTestData(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM coffee_shops");
        rs.next();
        int count = rs.getInt(1);
        
        if (count == 0) {
            System.out.println("Добавление тестовых данных...");
            
            // Тестовые кофейни
            stmt.execute("INSERT INTO coffee_shops (shop_name, address, phone, opening_date) VALUES " +
                        "('Central Coffee', 'ул. Центральная, 1', '+7-123-456-7890', '2020-01-15'), " +
                        "('Urban Brew', 'пр. Городской, 45', '+7-987-654-3210', '2021-03-20'), " +
                        "('Morning Cup', 'ул. Утренняя, 12', '+7-555-123-4567', '2019-11-10')");
            
            // Тестовые клиенты
            stmt.execute("INSERT INTO customers (first_name, last_name, email, phone, birth_date, address, discount) VALUES " +
                        "('Иван', 'Петров', 'ivan@example.com', '+7-111-222-3333', '1990-05-15', 'ул. Примерная, 10', 5.0), " +
                        "('Мария', 'Сидорова', 'maria@example.com', '+7-444-555-6666', '1985-08-22', 'ул. Тестовая, 20', 10.0), " +
                        "('Алексей', 'Иванов', 'alex@example.com', '+7-777-888-9999', '1995-12-10', NULL, 0.0)");
            
            // Тестовые сотрудники
            stmt.execute("INSERT INTO employees (first_name, last_name, position, hire_date, salary, shop_id, email) VALUES " +
                        "('Анна', 'Кузнецова', 'Бариста', '2022-01-10', 40000, 1, 'anna@coffee.com'), " +
                        "('Дмитрий', 'Смирнов', 'Официант', '2021-06-15', 35000, 1, 'dmitry@coffee.com'), " +
                        "('Ольга', 'Васильева', 'Менеджер', '2020-03-01', 60000, 2, 'olga@coffee.com')");
            
            // Тестовые напитки
            stmt.execute("INSERT INTO drinks_catalog (drink_name, description, category) VALUES " +
                        "('Эспрессо', 'Классический крепкий кофе', 'Кофе'), " +
                        "('Капучино', 'Кофе с молочной пенкой', 'Кофе'), " +
                        "('Латте', 'Кофе с большим количеством молока', 'Кофе'), " +
                        "('Чай черный', 'Классический черный чай', 'Чай')");
            
            // Тестовые десерты
            stmt.execute("INSERT INTO desserts_catalog (dessert_name, description, category) VALUES " +
                        "('Тирамису', 'Итальянский десерт', 'Пирожное'), " +
                        "('Чизкейк', 'Сырный торт', 'Торт'), " +
                        "('Маффин', 'Шоколадный кекс', 'Выпечка')");
            
            // Назначаем менеджера кофейне
            stmt.execute("UPDATE coffee_shops SET manager_id = 3 WHERE shop_id = 2");
            
            System.out.println("✅ Тестовые данные добавлены");
        } else {
            System.out.println("✅ В базе уже есть " + count + " кофеен");
        }
    }
    
    public static void testConnection() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 'База данных готова' as status");
            
            if (rs.next()) {
                System.out.println("✓ " + rs.getString("status"));
            }
            
            // Показываем статистику
            System.out.println("\n📊 Статистика базы данных:");
            String[] tables = {"coffee_shops", "customers", "employees", "drinks_catalog", "desserts_catalog", "orders"};
            for (String table : tables) {
                try {
                    rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM " + table);
                    if (rs.next()) {
                        System.out.println("  " + table + ": " + rs.getInt("cnt") + " записей");
                    }
                } catch (Exception e) {
                    System.out.println("  " + table + ": таблица не существует");
                }
            }
            
            stmt.close();
            
        } catch (Exception e) {
            System.err.println("✗ Ошибка тестирования: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при закрытии: " + e.getMessage());
        }
    }
    
    // Метод для быстрого теста
    public static void main(String[] args) {
        initializeH2Database();
        testConnection();
        closeConnection();
    }
}