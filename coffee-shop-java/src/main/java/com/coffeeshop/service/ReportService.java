package com.coffeeshop.service;

import com.coffeeshop.dao.DiscountDAO;
import com.coffeeshop.dao.ReportDAO;
import com.coffeeshop.models.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ReportService {
    private DiscountDAO discountDAO;
    private ReportDAO reportDAO;
    
    public ReportService() {
        this.discountDAO = new DiscountDAO();
        this.reportDAO = new ReportDAO();
    }
    
    // 1. Отчет по скидкам
    public void showDiscountReport() {
        System.out.println("\n=== ОТЧЕТ ПО СКИДКАМ КЛИЕНТОВ ===");
        
        Double minDiscount = discountDAO.getMinDiscount();
        Double maxDiscount = discountDAO.getMaxDiscount();
        Double avgDiscount = discountDAO.getAverageDiscount();
        
        System.out.printf("Минимальная скидка: %.1f%%\n", minDiscount);
        System.out.printf("Максимальная скидка: %.1f%%\n", maxDiscount);
        System.out.printf("Средняя скидка: %.1f%%\n", avgDiscount);
        
        System.out.println("\n--- Клиенты с минимальной скидкой ---");
        List<Customer> minCustomers = discountDAO.getCustomersWithMinDiscount();
        if (minCustomers.isEmpty()) {
            System.out.println("Нет клиентов с минимальной скидкой");
        } else {
            minCustomers.forEach(customer -> 
                System.out.printf("- %s %s: %.1f%%\n", 
                    customer.getFirstName(), 
                    customer.getLastName(), 
                    customer.getDiscount()));
        }
        
        System.out.println("\n--- Клиенты с максимальной скидкой ---");
        List<Customer> maxCustomers = discountDAO.getCustomersWithMaxDiscount();
        if (maxCustomers.isEmpty()) {
            System.out.println("Нет клиентов с максимальной скидкой");
        } else {
            maxCustomers.forEach(customer -> 
                System.out.printf("- %s %s: %.1f%%\n", 
                    customer.getFirstName(), 
                    customer.getLastName(), 
                    customer.getDiscount()));
        }
    }
    
    // 2. Отчет по клиентам
    public void showCustomerReport() {
        System.out.println("\n=== ОТЧЕТ ПО КЛИЕНТАМ ===");
        
        // Самый молодой клиент
        Map<String, Object> youngest = reportDAO.getYoungestCustomer();
        System.out.println("\n" + youngest.get("title") + ":");
        if (youngest.containsKey("first_name")) {
            System.out.printf("- %s %s (Дата рождения: %s)\n",
                youngest.get("first_name"),
                youngest.get("last_name"),
                youngest.get("birth_date"));
        } else {
            System.out.println("Данные не найдены");
        }
        
        // Самый возрастной клиент
        Map<String, Object> oldest = reportDAO.getOldestCustomer();
        System.out.println("\n" + oldest.get("title") + ":");
        if (oldest.containsKey("first_name")) {
            System.out.printf("- %s %s (Дата рождения: %s)\n",
                oldest.get("first_name"),
                oldest.get("last_name"),
                oldest.get("birth_date"));
        } else {
            System.out.println("Данные не найдены");
        }
        
        // Клиенты с днем рождения сегодня
        System.out.println("\n--- Дни рождения сегодня ---");
        List<Map<String, Object>> birthdays = reportDAO.getCustomersWithBirthdayToday();
        if (birthdays.isEmpty()) {
            System.out.println("Сегодня дней рождения нет");
        } else {
            birthdays.forEach(customer ->
                System.out.printf("- %s %s (ID: %d)\n",
                    customer.get("first_name"),
                    customer.get("last_name"),
                    customer.get("customer_id")));
        }
        
        // Клиенты без адреса
        System.out.println("\n--- Клиенты без адреса ---");
        List<Map<String, Object>> noAddress = reportDAO.getCustomersWithoutAddress();
        if (noAddress.isEmpty()) {
            System.out.println("Все клиенты имеют адрес");
        } else {
            noAddress.forEach(customer ->
                System.out.printf("- %s %s (Телефон: %s)\n",
                    customer.get("first_name"),
                    customer.get("last_name"),
                    customer.get("phone")));
        }
    }
    
    // 3. Отчет по заказам
    public void showOrderReport(LocalDate startDate, LocalDate endDate, LocalDate specificDate) {
        System.out.println("\n=== ОТЧЕТ ПО ЗАКАЗАМ ===");
        
        // Заказы в указанном периоде
        System.out.printf("\n--- Заказы за период: %s - %s ---\n", 
            startDate.format(DateTimeFormatter.ISO_DATE),
            endDate.format(DateTimeFormatter.ISO_DATE));
        
        List<Map<String, Object>> orders = reportDAO.getOrdersInDateRange(startDate, endDate);
        if (orders.isEmpty()) {
            System.out.println("Заказов за указанный период нет");
        } else {
            orders.forEach(order -> 
                System.out.printf("- Заказ #%d от %s: %.2f руб. (Клиент: %s, Бариста: %s)\n",
                    order.get("order_id"),
                    ((java.sql.Timestamp)order.get("order_date")).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                    order.get("total_amount"),
                    order.get("customer_name"),
                    order.get("employee_name")));
        }
        
        // Статистика по конкретной дате
        System.out.printf("\n--- Статистика за %s ---\n", 
            specificDate.format(DateTimeFormatter.ISO_DATE));
        
        int dessertOrders = reportDAO.getDessertOrdersCount(specificDate);
        int drinkOrders = reportDAO.getDrinkOrdersCount(specificDate);
        Double avgAmount = reportDAO.getAverageOrderAmount(specificDate);
        Double maxAmount = reportDAO.getMaxOrderAmount(specificDate);
        Map<String, Object> maxCustomer = reportDAO.getCustomerWithMaxOrderAmount(specificDate);
        
        System.out.printf("Заказов десертов: %d\n", dessertOrders);
        System.out.printf("Заказов напитков: %d\n", drinkOrders);
        System.out.printf("Средняя сумма заказа: %.2f руб.\n", avgAmount);
        System.out.printf("Максимальная сумма заказа: %.2f руб.\n", maxAmount);
        
        if (maxCustomer.containsKey("first_name")) {
            System.out.printf("Клиент с максимальным заказом: %s %s (%.2f руб.)\n",
                maxCustomer.get("first_name"),
                maxCustomer.get("last_name"),
                maxCustomer.get("total_amount"));
        }
    }
    
    // 4. Отчет по клиентам и баристам
    public void showCustomersAndBaristasReport() {
        System.out.println("\n=== КЛИЕНТЫ, ЗАКАЗЫ И БАРИСТЫ ===");
        
        List<Map<String, Object>> records = reportDAO.getCustomersWithDrinksAndBaristas();
        if (records.isEmpty()) {
            System.out.println("Данные не найдены");
        } else {
            records.forEach(record -> 
                System.out.printf("- Клиент: %s (ID: %d) -> Напиток: '%s' -> Бариста: %s (ID: %d) [%s]\n",
                    record.get("customer_name"),
                    record.get("customer_id"),
                    record.get("drink_name"),
                    record.get("barista_name"),
                    record.get("barista_id"),
                    ((java.sql.Timestamp)record.get("order_date")).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))));
        }
    }
    
    // 5. Отчет по расписанию
    public void showScheduleReport(int employeeId) {
        System.out.println("\n=== РАСПИСАНИЕ РАБОТЫ ===");
        
        // Расписание конкретного бариста
        System.out.printf("\n--- Расписание бариста (ID: %d) ---\n", employeeId);
        List<Map<String, Object>> baristaSchedule = reportDAO.getBaristaSchedule(employeeId);
        if (baristaSchedule.isEmpty()) {
            System.out.println("Расписание не найдено");
        } else {
            baristaSchedule.forEach(schedule -> 
                System.out.printf("- %s: %s - %s (%s, %s)\n",
                    schedule.get("day_of_week"),
                    schedule.get("opening_time"),
                    schedule.get("closing_time"),
                    schedule.get("shop_name"),
                    schedule.get("address")));
        }
        
        // Расписание всех баристов
        System.out.println("\n--- Расписание всех баристов ---");
        List<Map<String, Object>> allSchedules = reportDAO.getAllBaristasSchedule();
        if (allSchedules.isEmpty()) {
            System.out.println("Данные не найдены");
        } else {
            String currentEmployee = "";
            for (Map<String, Object> schedule : allSchedules) {
                String employeeName = (String) schedule.get("employee_name");
                if (!employeeName.equals(currentEmployee)) {
                    System.out.println("\n" + employeeName + ":");
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
}