package com.coffeeshop.ui;

import com.coffeeshop.service.OrderService;
import com.coffeeshop.ui.utils.InputHelper;
import com.coffeeshop.models.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final OrderService orderService;
    
    public OrderMenu(Scanner scanner, OrderService orderService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.orderService = orderService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🛒 УПРАВЛЕНИЕ ЗАКАЗАМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить заказ кофе");
            System.out.println("2.  Добавить заказ десерта");
            System.out.println("3.  Показать все заказы по десерту");
            System.out.println("4.  Показать заказы официанта");
            System.out.println("5.  Показать заказы клиента");
            System.out.println("6.  Изменить информацию о заказе");
            System.out.println("7.  Удалить конкретный заказ");
            System.out.println("8.  Удалить заказы конкретного десерта");
            System.out.println("9.  Показать заказы по кофейне");
            System.out.println("10. Показать сегодняшние заказы");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addNewCoffeeOrder();
                case 2 -> addNewDessertOrder();
                case 3 -> showOrdersByDessert();
                case 4 -> showOrdersByWaiter();
                case 5 -> showOrdersByCustomer();
                case 6 -> updateOrderInfo();
                case 7 -> deleteSpecificOrder();
                case 8 -> deleteOrdersByDessert();
                case 9 -> showOrdersByShop();
                case 10 -> showTodaysOrders();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addNewCoffeeOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО ЗАКАЗА КОФЕ");
        System.out.println("=".repeat(50));
        
        try {
            int shopId = inputHelper.getIntInput("ID кофейни: ");
            int employeeId = inputHelper.getIntInput("ID сотрудника: ");
            System.out.print("ID клиента (0 если нет): ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            
            double totalAmount = inputHelper.getDoubleInput("Сумма заказа: ");
            
            Order order = new Order();
            order.setShopId(shopId);
            order.setEmployeeId(employeeId);
            order.setCustomerId(customerId > 0 ? customerId : null);
            order.setTotalAmount(totalAmount);
            
            System.out.println("Заказ кофе добавлен (функция требует реализации в OrderService)");
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
    
    private void addNewDessertOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО ЗАКАЗА ДЕСЕРТА");
        System.out.println("=".repeat(50));
        
        try {
            int shopId = inputHelper.getIntInput("ID кофейни: ");
            int employeeId = inputHelper.getIntInput("ID сотрудника: ");
            System.out.print("ID клиента (0 если нет): ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            
            double totalAmount = inputHelper.getDoubleInput("Сумма заказа: ");
            
            System.out.println("Заказ десерта добавлен (функция требует реализации в OrderService)");
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
    
    private void showOrdersByDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ ПО ДЕСЕРТУ");
        System.out.println("=".repeat(50));
        
        int dessertId = inputHelper.getIntInput("Введите ID десерта: ");
        System.out.println("Показаны заказы по десерту ID: " + dessertId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void showOrdersByWaiter() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ ОФИЦИАНТА");
        System.out.println("=".repeat(50));
        
        int waiterId = inputHelper.getIntInput("Введите ID официанта: ");
        System.out.println("Показаны заказы официанта ID: " + waiterId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void showOrdersByCustomer() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ КЛИЕНТА");
        System.out.println("=".repeat(50));
        
        System.out.println("1. По ID клиента");
        System.out.println("2. По телефону");
        int choice = inputHelper.getIntInput("Выберите способ поиска: ");
        
        if (choice == 1) {
            int customerId = inputHelper.getIntInput("Введите ID клиента: ");
            System.out.println("Заказы клиента ID: " + customerId);
        } else if (choice == 2) {
            String phone = inputHelper.readString("Введите телефон клиента: ");
            System.out.println("Заказы клиента с телефоном: " + phone);
        }
        
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void updateOrderInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ ИНФОРМАЦИИ О ЗАКАЗЕ");
        System.out.println("=".repeat(50));
        
        int orderId = inputHelper.getIntInput("Введите ID заказа: ");
        System.out.println("Изменена информация о заказе ID: " + orderId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void deleteSpecificOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ КОНКРЕТНОГО ЗАКАЗА");
        System.out.println("=".repeat(50));
        
        int orderId = inputHelper.getIntInput("Введите ID заказа: ");
        System.out.println("Удален заказ ID: " + orderId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void deleteOrdersByDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ ЗАКАЗОВ ПО ДЕСЕРТУ");
        System.out.println("=".repeat(50));
        
        int dessertId = inputHelper.getIntInput("Введите ID десерта: ");
        System.out.println("Удалены заказы с десертом ID: " + dessertId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void showOrdersByShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ ПО КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("Введите ID кофейни: ");
        System.out.println("Заказы кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в OrderService)");
    }
    
    private void showTodaysOrders() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СЕГОДНЯШНИЕ ЗАКАЗЫ");
        System.out.println("=".repeat(50));
        
        LocalDate today = LocalDate.now();
        System.out.println("Заказы за " + today.format(DateTimeFormatter.ISO_DATE));
        System.out.println("(Функция требует реализации в OrderService)");
    }
}