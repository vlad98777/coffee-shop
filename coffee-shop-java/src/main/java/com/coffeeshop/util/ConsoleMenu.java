package com.coffeeshop.util;

import com.coffeeshop.service.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleMenu {
    private Scanner scanner;
    private ReportService reportService;
    private CoffeeShopService coffeeShopService;
    
    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.reportService = new ReportService();
        this.coffeeShopService = new CoffeeShopService();
    }
    
    // Главное меню для интеграции с Main.java
    public void showMainMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🏪 СИСТЕМА УПРАВЛЕНИЯ КОФЕЙНЕЙ - ГЛАВНОЕ МЕНЮ");
            System.out.println("=".repeat(60));
            System.out.println("1. 📊 Отчеты и аналитика");
            System.out.println("2. 🏪 Управление кофейнями");
            System.out.println("3. 🛒 Управление заказами");
            System.out.println("4. 👤 Управление клиентами");
            System.out.println("5. ⚙️  Быстрые отчеты");
            System.out.println("0. 🚪 Выход");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите опцию: ");
            
            switch (choice) {
                case 1:
                    showReportsMenu();
                    break;
                case 2:
                    showCoffeeShopMenu();
                    break;
                case 3:
                    showOrdersMenu();
                    break;
                case 4:
                    showCustomersMenu();
                    break;
                case 5:
                    showQuickReportsMenu();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Выход из системы...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
    
    // Меню отчетов и аналитики
    public void showReportsMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("📊 ОТЧЕТЫ И АНАЛИТИКА");
            System.out.println("=".repeat(60));
            System.out.println("1.  Отчет по скидкам клиентов");
            System.out.println("2.  Аналитика клиентов (возраст, дни рождения)");
            System.out.println("3.  Заказы за период");
            System.out.println("4.  Статистика заказов по дате");
            System.out.println("5.  Клиенты и бариста");
            System.out.println("6.  Расписание баристов");
            System.out.println("7.  Быстрые отчеты");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите отчет: ");
            
            switch (choice) {
                case 1:
                    showDiscountReport();
                    break;
                case 2:
                    showCustomerAnalytics();
                    break;
                case 3:
                    showOrdersInDateRange();
                    break;
                case 4:
                    showOrdersStatsByDate();
                    break;
                case 5:
                    showCustomersAndBaristas();
                    break;
                case 6:
                    showBaristaSchedule();
                    break;
                case 7:
                    showQuickReportsMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                waitForEnter();
            }
        }
    }
    
    // Меню управления кофейнями
    public void showCoffeeShopMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🏪 УПРАВЛЕНИЕ КОФЕЙНЯМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Показать все кофейни");
            System.out.println("2.  Найти кофейню по названию");
            System.out.println("3.  Показать детали кофейни");
            System.out.println("4.  Статистика кофейни");
            System.out.println("0.  Назад");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите действие: ");
            
            switch (choice) {
                case 1:
                    showAllCoffeeShops();
                    break;
                case 2:
                    findCoffeeShopByName();
                    break;
                case 3:
                    showCoffeeShopDetails();
                    break;
                case 4:
                    showCoffeeShopStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                waitForEnter();
            }
        }
    }
    
    // Меню управления заказами
    public void showOrdersMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🛒 УПРАВЛЕНИЕ ЗАКАЗАМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Заказы за период");
            System.out.println("2.  Статистика заказов по дате");
            System.out.println("3.  Количество заказов десертов/напитков");
            System.out.println("4.  Клиент с максимальным заказом");
            System.out.println("0.  Назад");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите действие: ");
            
            switch (choice) {
                case 1:
                    showOrdersInDateRange();
                    break;
                case 2:
                    showOrdersStatsByDate();
                    break;
                case 3:
                    showOrdersCountByDate();
                    break;
                case 4:
                    showCustomerWithMaxOrder();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                waitForEnter();
            }
        }
    }
    
    // Меню управления клиентами
    public void showCustomersMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("👤 УПРАВЛЕНИЕ КЛИЕНТАМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Отчет по скидкам");
            System.out.println("2.  Аналитика клиентов");
            System.out.println("3.  Клиенты и бариста");
            System.out.println("4.  Клиенты без адреса");
            System.out.println("5.  Дни рождения сегодня");
            System.out.println("0.  Назад");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите действие: ");
            
            switch (choice) {
                case 1:
                    showDiscountReport();
                    break;
                case 2:
                    showCustomerAnalytics();
                    break;
                case 3:
                    showCustomersAndBaristas();
                    break;
                case 4:
                    showCustomersWithoutAddress();
                    break;
                case 5:
                    showBirthdayCustomers();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                waitForEnter();
            }
        }
    }
    
    // Меню быстрых отчетов
    public void showQuickReportsMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("⚡ БЫСТРЫЕ ОТЧЕТЫ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Отчет по скидкам");
            System.out.println("2.  Клиенты без адреса");
            System.out.println("3.  Дни рождения сегодня");
            System.out.println("4.  Расписание всех баристов");
            System.out.println("5.  Сегодняшняя статистика");
            System.out.println("6.  Недельная статистика");
            System.out.println("0.  Назад");
            System.out.println("=".repeat(60));
            
            int choice = readInt("Выберите отчет: ");
            
            switch (choice) {
                case 1:
                    showDiscountReport();
                    break;
                case 2:
                    showCustomersWithoutAddress();
                    break;
                case 3:
                    showBirthdayCustomers();
                    break;
                case 4:
                    showAllBaristasSchedule();
                    break;
                case 5:
                    showTodaysStats();
                    break;
                case 6:
                    showWeeklyStats();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                waitForEnter();
            }
        }
    }
    
    // ==================== РЕАЛИЗАЦИЯ ОТЧЕТОВ ====================
    
    // 1. Отчет по скидкам
    private void showDiscountReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📈 ОТЧЕТ ПО СКИДКАМ КЛИЕНТОВ");
        System.out.println("=".repeat(60));
        reportService.showDiscountReport();
    }
    
    // 2. Аналитика клиентов
    private void showCustomerAnalytics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👤 АНАЛИТИКА КЛИЕНТОВ");
        System.out.println("=".repeat(60));
        reportService.showCustomerReport();
    }
    
    // 3. Заказы за период
    private void showOrdersInDateRange() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 ЗАКАЗЫ ЗА ПЕРИОД");
        System.out.println("=".repeat(60));
        
        try {
            System.out.print("Введите начальную дату (ГГГГ-ММ-ДД): ");
            LocalDate startDate = readDate();
            
            System.out.print("Введите конечную дату (ГГГГ-ММ-ДД): ");
            LocalDate endDate = readDate();
            
            System.out.print("Введите дату для статистики (ГГГГ-ММ-ДД): ");
            LocalDate specificDate = readDate();
            
            if (startDate.isAfter(endDate)) {
                System.out.println("Ошибка: начальная дата позже конечной");
                return;
            }
            
            reportService.showOrderReport(startDate, endDate, specificDate);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка формата даты. Используйте формат ГГГГ-ММ-ДД");
        }
    }
    
    // 4. Статистика заказов по дате
    private void showOrdersStatsByDate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💰 СТАТИСТИКА ЗАКАЗОВ ПО ДАТЕ");
        System.out.println("=".repeat(60));
        
        try {
            System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
            LocalDate date = readDate();
            
            System.out.println("\n--- Статистика за " + date.format(DateTimeFormatter.ISO_DATE) + " ---");
            
            // Используем ReportService для получения данных
            // В реальной реализации здесь будет вызов соответствующих методов
            
            System.out.println("Средняя сумма заказа: [данные из ReportService]");
            System.out.println("Максимальная сумма заказа: [данные из ReportService]");
            System.out.println("Общая выручка: [данные из ReportService]");
            System.out.println("Количество заказов: [данные из ReportService]");
            
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка формата даты. Используйте формат ГГГГ-ММ-ДД");
        }
    }
    
    // 5. Количество заказов десертов/напитков
    private void showOrdersCountByDate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🍰☕ КОЛИЧЕСТВО ЗАКАЗОВ ПО ДАТЕ");
        System.out.println("=".repeat(60));
        
        try {
            System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
            LocalDate date = readDate();
            
            System.out.println("\n--- Статистика за " + date.format(DateTimeFormatter.ISO_DATE) + " ---");
            System.out.println("Заказов десертов: [данные из ReportService]");
            System.out.println("Заказов напитков: [данные из ReportService]");
            System.out.println("Всего заказов: [данные из ReportService]");
            
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка формата даты. Используйте формат ГГГГ-ММ-ДД");
        }
    }
    
    // 6. Клиенты и бариста
    private void showCustomersAndBaristas() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👤☕👨‍🍳 КЛИЕНТЫ И БАРИСТА");
        System.out.println("=".repeat(60));
        reportService.showCustomersAndBaristasReport();
    }
    
    // 7. Клиент с максимальным заказом
    private void showCustomerWithMaxOrder() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👑 КЛИЕНТ С МАКСИМАЛЬНЫМ ЗАКАЗОМ");
        System.out.println("=".repeat(60));
        
        try {
            System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
            LocalDate date = readDate();
            
            System.out.println("\n--- Клиент с максимальным заказом за " + 
                date.format(DateTimeFormatter.ISO_DATE) + " ---");
            System.out.println("Клиент: [данные из ReportService]");
            System.out.println("Сумма заказа: [данные из ReportService] руб.");
            
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка формата даты. Используйте формат ГГГГ-ММ-ДД");
        }
    }
    
    // 8. Расписание конкретного бариста
    private void showBaristaSchedule() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🕐 РАСПИСАНИЕ БАРИСТА");
        System.out.println("=".repeat(60));
        
        System.out.print("Введите ID бариста: ");
        int employeeId = readInt("");
        
        reportService.showScheduleReport(employeeId);
    }
    
    // 9. Показать всех баристов
    private void showAllBaristasSchedule() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👨‍🍳 РАСПИСАНИЕ ВСЕХ БАРИСТОВ");
        System.out.println("=".repeat(60));
        reportService.showScheduleReport(0);
    }
    
    // 10. Клиенты без адреса
    private void showCustomersWithoutAddress() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📭 КЛИЕНТЫ БЕЗ АДРЕСА");
        System.out.println("=".repeat(60));
        
        System.out.println("Клиенты без указанного адреса:");
        System.out.println("[Данные из ReportService]");
        
        // В реальной реализации:
        // List<Map<String, Object>> noAddress = reportDAO.getCustomersWithoutAddress();
        // Вывод списка клиентов
    }
    
    // 11. Дни рождения сегодня
    private void showBirthdayCustomers() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎂 ДНИ РОЖДЕНИЯ СЕГОДНЯ");
        System.out.println("=".repeat(60));
        
        LocalDate today = LocalDate.now();
        System.out.println("Клиенты с днем рождения " + today.format(DateTimeFormatter.ISO_DATE) + ":");
        System.out.println("[Данные из ReportService]");
        
        // В реальной реализации:
        // List<Map<String, Object>> birthdays = reportDAO.getCustomersWithBirthdayToday();
        // Вывод списка клиентов
    }
    
    // 12. Сегодняшняя статистика
    private void showTodaysStats() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 СЕГОДНЯШНЯЯ СТАТИСТИКА");
        System.out.println("=".repeat(60));
        
        LocalDate today = LocalDate.now();
        System.out.println("Статистика за " + today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ":");
        System.out.println("\n--- Общая статистика ---");
        System.out.println("Количество заказов: [данные]");
        System.out.println("Общая выручка: [данные] руб.");
        System.out.println("Средний чек: [данные] руб.");
        
        System.out.println("\n--- По категориям ---");
        System.out.println("Заказов кофе: [данные]");
        System.out.println("Заказов десертов: [данные]");
        System.out.println("Клиентов: [данные]");
    }
    
    // 13. Недельная статистика
    private void showWeeklyStats() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📈 НЕДЕЛЬНАЯ СТАТИСТИКА");
        System.out.println("=".repeat(60));
        
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(7);
        
        System.out.println("Статистика за период " + 
            weekAgo.format(DateTimeFormatter.ISO_DATE) + " - " + 
            today.format(DateTimeFormatter.ISO_DATE) + ":");
        
        System.out.println("\n--- Общая статистика ---");
        System.out.println("Всего заказов: [данные]");
        System.out.println("Общая выручка: [данные] руб.");
        System.out.println("Средний дневной доход: [данные] руб.");
        
        System.out.println("\n--- Динамика ---");
        System.out.println("Лучший день: [данные]");
        System.out.println("Худший день: [данные]");
        System.out.println("Рост/падение: [данные]%");
    }
    
    // ==================== ФУНКЦИИ УПРАВЛЕНИЯ КОФЕЙНЯМИ ====================
    
    // Показать все кофейни
    private void showAllCoffeeShops() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏪 ВСЕ КОФЕЙНИ");
        System.out.println("=".repeat(60));
        
        // В реальной реализации:
        // List<CoffeeShop> shops = coffeeShopService.getAllCoffeeShops();
        // Вывод списка кофеен
        
        System.out.println("Список всех кофеен:");
        System.out.println("1. Central Coffee (ID: 1)");
        System.out.println("2. Urban Brew (ID: 2)");
        System.out.println("3. Morning Cup (ID: 3)");
        System.out.println("...");
    }
    
    // Найти кофейню по названию
    private void findCoffeeShopByName() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔍 ПОИСК КОФЕЙНИ ПО НАЗВАНИЮ");
        System.out.println("=".repeat(60));
        
        System.out.print("Введите название кофейни: ");
        String name = scanner.nextLine();
        
        System.out.println("\nРезультаты поиска \"" + name + "\":");
        System.out.println("[Данные из CoffeeShopService]");
    }
    
    // Показать детали кофейни
    private void showCoffeeShopDetails() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 ДЕТАЛИ КОФЕЙНИ");
        System.out.println("=".repeat(60));
        
        System.out.print("Введите ID кофейни: ");
        int shopId = readInt("");
        
        System.out.println("\n--- Информация о кофейне ID: " + shopId + " ---");
        System.out.println("Название: [данные]");
        System.out.println("Адрес: [данные]");
        System.out.println("Телефон: [данные]");
        System.out.println("Дата открытия: [данные]");
        System.out.println("Менеджер: [данные]");
        System.out.println("Количество сотрудников: [данные]");
    }
    
    // Статистика кофейни
    private void showCoffeeShopStatistics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 СТАТИСТИКА КОФЕЙНИ");
        System.out.println("=".repeat(60));
        
        System.out.print("Введите ID кофейни: ");
        int shopId = readInt("");
        
        System.out.println("\n--- Статистика кофейни ID: " + shopId + " ---");
        System.out.println("Заказов за месяц: [данные]");
        System.out.println("Выручка за месяц: [данные] руб.");
        System.out.println("Средний чек: [данные] руб.");
        System.out.println("Популярные напитки: [данные]");
        System.out.println("Лучший сотрудник: [данные]");
    }
    
    // ==================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ====================
    
    private int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Введите число: ");
            }
        }
    }
    
    private LocalDate readDate() {
        while (true) {
            try {
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException e) {
                System.out.print("Неверный формат даты. Введите дату (ГГГГ-ММ-ДД): ");
            }
        }
    }
    
    private void waitForEnter() {
        System.out.print("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }
    
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
