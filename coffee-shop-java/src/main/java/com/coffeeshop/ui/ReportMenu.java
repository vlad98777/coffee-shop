package com.coffeeshop.ui;

import com.coffeeshop.dao.ReportDAO;
import com.coffeeshop.dao.DiscountDAO;
import com.coffeeshop.ui.utils.InputHelper;
import com.coffeeshop.models.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class ReportMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final ReportDAO reportDAO;
    private final DiscountDAO discountDAO;
    
    public ReportMenu(Scanner scanner, ReportDAO reportDAO, DiscountDAO discountDAO) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.reportDAO = reportDAO;
        this.discountDAO = discountDAO;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("📊 ОТЧЕТЫ И АНАЛИТИКА");
            System.out.println("=".repeat(60));
            System.out.println("1.  Отчет по скидкам клиентов");
            System.out.println("2.  Отчет по клиентам (возраст, дни рождения)");
            System.out.println("3.  Информация о заказах за период");
            System.out.println("4.  Количество заказов десертов/напитков за дату");
            System.out.println("5.  Клиенты, заказы и бариста");
            System.out.println("6.  Средняя и максимальная сумма заказа");
            System.out.println("7.  Клиент с максимальной суммой заказа");
            System.out.println("8.  Расписание работы баристов");
            System.out.println("9.  Все отчеты сразу");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите отчет: ");
            
            switch (choice) {
                case 1 -> showDiscountReport();
                case 2 -> showCustomerAnalyticsReport();
                case 3 -> showOrdersInDateRangeReport();
                case 4 -> showOrdersCountByDateReport();
                case 5 -> showCustomersAndBaristasReport();
                case 6 -> showOrderStatsReport();
                case 7 -> showCustomerWithMaxOrderReport();
                case 8 -> showBaristaScheduleReport();
                case 9 -> showAllReports();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void showDiscountReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📈 ОТЧЕТ ПО СКИДКАМ КЛИЕНТОВ");
        System.out.println("=".repeat(60));
        
        Double minDiscount = discountDAO.getMinDiscount();
        Double maxDiscount = discountDAO.getMaxDiscount();
        Double avgDiscount = discountDAO.getAverageDiscount();
        
        System.out.printf("Минимальная скидка: %.1f%%\n", minDiscount);
        System.out.printf("Максимальная скидка: %.1f%%\n", maxDiscount);
        System.out.printf("Средняя скидка: %.1f%%\n", avgDiscount);
        
        System.out.println("\n--- Клиенты с минимальной скидкой ---");
        List<Customer> minCustomers = discountDAO.getCustomersWithMinDiscount();
        printCustomers(minCustomers);
        
        System.out.println("\n--- Клиенты с максимальной скидкой ---");
        List<Customer> maxCustomers = discountDAO.getCustomersWithMaxDiscount();
        printCustomers(maxCustomers);
    }
    
    private void printCustomers(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Нет клиентов");
        } else {
            customers.forEach(customer -> 
                System.out.printf("- %s %s: %.1f%%\n", 
                    customer.getFirstName(), 
                    customer.getLastName(), 
                    customer.getDiscount()));
        }
    }
    
    private void showCustomerAnalyticsReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👤 АНАЛИТИКА КЛИЕНТОВ");
        System.out.println("=".repeat(60));
        
        // Самый молодой клиент
        System.out.println("\n--- Самый молодой клиент ---");
        Map<String, Object> youngest = reportDAO.getYoungestCustomer();
        printCustomerMap(youngest);
        
        // Самый возрастной клиент
        System.out.println("\n--- Самый возрастной клиент ---");
        Map<String, Object> oldest = reportDAO.getOldestCustomer();
        printCustomerMap(oldest);
        
        // Клиенты с днем рождения сегодня
        System.out.println("\n--- Дни рождения сегодня ---");
        List<Map<String, Object>> birthdays = reportDAO.getCustomersWithBirthdayToday();
        if (birthdays.isEmpty()) {
            System.out.println("Сегодня дней рождения нет");
        } else {
            birthdays.forEach(this::printCustomerMap);
        }
        
        // Клиенты без адреса
        System.out.println("\n--- Клиенты без адреса ---");
        List<Map<String, Object>> noAddress = reportDAO.getCustomersWithoutAddress();
        if (noAddress.isEmpty()) {
            System.out.println("Все клиенты имеют адрес");
        } else {
            noAddress.forEach(customer -> 
                System.out.printf("- %s %s (Телефон: %s, Email: %s)\n",
                    customer.get("first_name"),
                    customer.get("last_name"),
                    customer.get("phone"),
                    customer.get("email")));
        }
    }
    
    private void printCustomerMap(Map<String, Object> customer) {
        if (customer.containsKey("first_name")) {
            System.out.printf("- %s %s", customer.get("first_name"), customer.get("last_name"));
            if (customer.containsKey("birth_date")) {
                System.out.printf(" (Дата рождения: %s)", customer.get("birth_date"));
            }
            System.out.println();
        } else {
            System.out.println("Данные не найдены");
        }
    }
    
    private void showOrdersInDateRangeReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 ИНФОРМАЦИЯ О ЗАКАЗАХ ЗА ПЕРИОД");
        System.out.println("=".repeat(60));
        
        try {
            LocalDate startDate = inputHelper.readDate("Введите начальную дату");
            LocalDate endDate = inputHelper.readDate("Введите конечную дату");
            
            if (startDate.isAfter(endDate)) {
                System.out.println("Ошибка: начальная дата позже конечной");
                return;
            }
            
            List<Map<String, Object>> orders = reportDAO.getOrdersInDateRange(startDate, endDate);
            
            System.out.printf("\n--- Заказы за период: %s - %s ---\n", 
                startDate.format(DateTimeFormatter.ISO_DATE),
                endDate.format(DateTimeFormatter.ISO_DATE));
            
            if (orders.isEmpty()) {
                System.out.println("Заказов за указанный период нет");
            } else {
                System.out.println("Найдено заказов: " + orders.size());
                System.out.println("-".repeat(60));
                
                double totalAmount = 0;
                for (Map<String, Object> order : orders) {
                    System.out.printf("Заказ #%d от %s\n",
                        order.get("order_id"),
                        order.get("order_date"));
                    System.out.printf("Сумма: %.2f руб.\n", order.get("total_amount"));
                    System.out.printf("Клиент: %s, Бариста: %s\n",
                        order.get("customer_name"), order.get("employee_name"));
                    System.out.println("-".repeat(30));
                    
                    totalAmount += (Double) order.get("total_amount");
                }
                
                System.out.printf("\nОбщая выручка за период: %.2f руб.\n", totalAmount);
                System.out.printf("Средний чек: %.2f руб.\n", totalAmount / orders.size());
            }
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showOrdersCountByDateReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🍰☕ КОЛИЧЕСТВО ЗАКАЗОВ ПО ДАТЕ");
        System.out.println("=".repeat(60));
        
        try {
            LocalDate date = inputHelper.readDate("Введите дату");
            
            int dessertOrders = reportDAO.getDessertOrdersCount(date);
            int drinkOrders = reportDAO.getDrinkOrdersCount(date);
            
            System.out.printf("\n--- Статистика за %s ---\n", 
                date.format(DateTimeFormatter.ISO_DATE));
            System.out.printf("Заказов десертов: %d\n", dessertOrders);
            System.out.printf("Заказов напитков: %d\n", drinkOrders);
            System.out.printf("Всего заказов: %d\n", dessertOrders + drinkOrders);
            
            if (dessertOrders + drinkOrders > 0) {
                double dessertPercent = (dessertOrders * 100.0) / (dessertOrders + drinkOrders);
                double drinkPercent = (drinkOrders * 100.0) / (dessertOrders + drinkOrders);
                System.out.printf("Процент десертов: %.1f%%\n", dessertPercent);
                System.out.printf("Процент напитков: %.1f%%\n", drinkPercent);
            }
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showCustomersAndBaristasReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👤☕👨‍🍳 КЛИЕНТЫ, ЗАКАЗЫ И БАРИСТЫ");
        System.out.println("=".repeat(60));
        
        List<Map<String, Object>> records = reportDAO.getCustomersWithDrinksAndBaristas();
        if (records.isEmpty()) {
            System.out.println("Данные не найдены");
        } else {
            System.out.println("Найдено записей: " + records.size());
            System.out.println("-".repeat(60));
            
            records.forEach(record -> 
                System.out.printf("Клиент: %s (ID: %d) → Напиток: '%s' → Бариста: %s (ID: %d) [%s]\n",
                    record.get("customer_name"),
                    record.get("customer_id"),
                    record.get("drink_name"),
                    record.get("barista_name"),
                    record.get("barista_id"),
                    record.get("order_date")));
        }
    }
    
    private void showOrderStatsReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💰 СТАТИСТИКА СУММ ЗАКАЗОВ ПО ДАТЕ");
        System.out.println("=".repeat(60));
        
        try {
            LocalDate date = inputHelper.readDate("Введите дату");
            
            Double avgAmount = reportDAO.getAverageOrderAmount(date);
            Double maxAmount = reportDAO.getMaxOrderAmount(date);
            
            System.out.printf("\n--- Статистика за %s ---\n", 
                date.format(DateTimeFormatter.ISO_DATE));
            System.out.printf("Средняя сумма заказа: %.2f руб.\n", avgAmount);
            System.out.printf("Максимальная сумма заказа: %.2f руб.\n", maxAmount);
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showCustomerWithMaxOrderReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👑 КЛИЕНТ С МАКСИМАЛЬНЫМ ЗАКАЗОМ");
        System.out.println("=".repeat(60));
        
        try {
            LocalDate date = inputHelper.readDate("Введите дату");
            
            Map<String, Object> maxCustomer = reportDAO.getCustomerWithMaxOrderAmount(date);
            
            System.out.printf("\n--- Клиент с максимальным заказом за %s ---\n", 
                date.format(DateTimeFormatter.ISO_DATE));
            
            if (maxCustomer.containsKey("first_name")) {
                System.out.printf("Клиент: %s %s (ID: %d)\n",
                    maxCustomer.get("first_name"),
                    maxCustomer.get("last_name"),
                    maxCustomer.get("customer_id"));
                System.out.printf("Сумма заказа: %.2f руб.\n", maxCustomer.get("total_amount"));
            } else {
                System.out.println("Данные не найдены");
            }
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showBaristaScheduleReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🕐 РАСПИСАНИЕ РАБОТЫ БАРИСТОВ");
        System.out.println("=".repeat(60));
        
        System.out.println("1. Расписание конкретного бариста");
        System.out.println("2. Расписание всех баристов");
        int choice = inputHelper.getIntInput("Выберите вариант: ");
        
        if (choice == 1) {
            int employeeId = inputHelper.getIntInput("Введите ID бариста: ");
            showSingleBaristaSchedule(employeeId);
        } else if (choice == 2) {
            showAllBaristasSchedule();
        } else {
            System.out.println("Неверный выбор!");
        }
    }
    
    private void showSingleBaristaSchedule(int employeeId) {
        List<Map<String, Object>> schedule = reportDAO.getBaristaSchedule(employeeId);
        
        System.out.printf("\n--- Расписание бариста (ID: %d) ---\n", employeeId);
        
        if (schedule.isEmpty()) {
            System.out.println("Расписание не найдено");
        } else {
            schedule.forEach(day -> 
                System.out.printf("%s: %s - %s (%s, %s)\n",
                    day.get("day_of_week"),
                    day.get("opening_time"),
                    day.get("closing_time"),
                    day.get("shop_name"),
                    day.get("address")));
        }
    }
    
    private void showAllBaristasSchedule() {
        List<Map<String, Object>> allSchedules = reportDAO.getAllBaristasSchedule();
        
        System.out.println("\n--- Расписание всех баристов ---");
        
        if (allSchedules.isEmpty()) {
            System.out.println("Данные не найдены");
        } else {
            String currentEmployee = "";
            for (Map<String, Object> schedule : allSchedules) {
                String employeeName = (String) schedule.get("employee_name");
                if (!employeeName.equals(currentEmployee)) {
                    System.out.println("\n" + employeeName + " (ID: " + schedule.get("employee_id") + "):");
                    currentEmployee = employeeName;
                }
                System.out.printf("  %s: %s - %s (%s)\n",
                    schedule.get("day_of_week"),
                    schedule.get("opening_time"),
                    schedule.get("closing_time"),
                    schedule.get("shop_name"));
            }
        }
    }
    
    private void showAllReports() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 ВСЕ ОТЧЕТЫ");
        System.out.println("=".repeat(60));
        
        System.out.println("Отчеты будут выводиться последовательно...");
        
        showDiscountReport();
        inputHelper.waitForEnter();
        
        showCustomerAnalyticsReport();
        inputHelper.waitForEnter();
        
        showOrdersInDateRangeReport();
        inputHelper.waitForEnter();
        
        showCustomersAndBaristasReport();
        inputHelper.waitForEnter();
        
        showBaristaScheduleReport();
    }
}