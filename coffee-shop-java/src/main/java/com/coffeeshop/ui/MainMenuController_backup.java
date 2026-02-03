package com.coffeeshop.ui;

import com.coffeeshop.service.*;
import java.util.Scanner;

public class MainMenuController {
    private final Scanner scanner;
    private final CoffeeShopService coffeeShopService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final MenuService menuService;
    private final ScheduleService scheduleService;
    
    public MainMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.coffeeShopService = new CoffeeShopService();
        this.customerService = new CustomerService();
        this.orderService = new OrderService();
        this.menuService = new MenuService();
        this.scheduleService = new ScheduleService();
    }
    
    public void run() {
        boolean exit = false;
        
        while (!exit) {
            printMainMenu();
            System.out.print("Выберите действие: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    // Управление заказами
                    case 1 -> addCoffeeOrder();
                    case 2 -> addDessertOrder();
                    case 3 -> updateOrderInfo();
                    case 4 -> deleteSpecificOrder();
                    case 5 -> deleteOrdersByDessert();
                    case 6 -> showOrdersByDessert();
                    case 7 -> showOrdersByWaiter();
                    case 8 -> showOrdersByCustomer();
                    
                    // Управление меню
                    case 9 -> addNewCoffee();
                    case 10 -> updateCoffeeName();
                    case 11 -> addNewDessert();
                    case 12 -> updateDessertName();
                    
                    // Управление расписанием
                    case 13 -> addScheduleForNextMonday();
                    case 14 -> updateTuesdaySchedule();
                    case 15 -> deleteScheduleForDay();
                    case 16 -> deleteScheduleBetweenDates();
                    case 17 -> showScheduleForDay();
                    
                    // Управление кофейнями
                    case 18 -> showAllCoffeeShops();
                    case 19 -> showCoffeeShopDetails();
                    case 20 -> showAllManagers();
                    case 21 -> assignManagerToCoffeeShop();
                    case 22 -> showCoffeeShopStatistics();
                    case 23 -> showEmployeesByShop();
                    case 24 -> findCoffeeShopByName();
                    case 25 -> getOldestNewestCoffeeShop();
                    case 26 -> getShopsByYear();
                    case 27 -> updateCoffeeShopContactInfo();
                    case 28 -> checkManagerVacancy();
                    
                    // Общие операции
                    case 29 -> addRow();
                    case 30 -> deleteRow();
                    case 31 -> updateRow();
                    
                    case 0 -> exit = true;
                    default -> System.out.println("Неверный выбор!");
                }
                
                if (choice != 0) {
                    System.out.print("\nНажмите Enter для продолжения...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число.");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ГЛАВНОЕ МЕНЮ - СИСТЕМА УПРАВЛЕНИЯ КОФЕЙНЕЙ");
        System.out.println("=".repeat(50));
        
        System.out.println("--- УПРАВЛЕНИЕ ЗАКАЗАМИ ---");
        System.out.println(" 1. Добавить заказ кофе");
        System.out.println(" 2. Добавить заказ десерта");
        System.out.println(" 3. Изменить информацию о заказе");
        System.out.println(" 4. Удалить конкретный заказ");
        System.out.println(" 5. Удалить заказы конкретного десерта");
        System.out.println(" 6. Показать заказы по десерту");
        System.out.println(" 7. Показать заказы официанта");
        System.out.println(" 8. Показать заказы клиента");
        
        System.out.println("\n--- УПРАВЛЕНИЕ МЕНЮ ---");
        System.out.println(" 9. Добавить новый кофе в каталог");
        System.out.println("10. Изменить название кофе");
        System.out.println("11. Добавить новый десерт в каталог");
        System.out.println("12. Изменить название десерта");
        
        System.out.println("\n--- УПРАВЛЕНИЕ РАСПИСАНИЕМ ---");
        System.out.println("13. Добавить расписание на ближайший понедельник");
        System.out.println("14. Изменить расписание на вторник");
        System.out.println("15. Удалить расписание на день");
        System.out.println("16. Удалить расписание между датами");
        System.out.println("17. Показать расписание на день");
        
        System.out.println("\n--- УПРАВЛЕНИЕ КОФЕЙНЯМИ ---");
        System.out.println("18. Показать все кофейни");
        System.out.println("19. Показать детальную информацию о кофейне");
        System.out.println("20. Показать всех менеджеров");
        System.out.println("21. Назначить менеджера кофейне");
        System.out.println("22. Показать статистику кофейни");
        System.out.println("23. Показать сотрудников кофейни");
        System.out.println("24. Найти кофейню по названию");
        System.out.println("25. Показать самую старую/новую кофейню");
        System.out.println("26. Показать кофейни по году открытия");
        System.out.println("27. Обновить контактную информацию кофейни");
        System.out.println("28. Проверить вакансию менеджера");
        
        System.out.println("\n--- ОБЩИЕ ОПЕРАЦИИ ---");
        System.out.println("29. Добавить строку (общая функция)");
        System.out.println("30. Удалить строку (общая функция)");
        System.out.println("31. Обновить строку (общая функция)");
        
        System.out.println("\n--- ВЫХОД ---");
        System.out.println(" 0. Выход из системы");
        System.out.println("=".repeat(50));
    }
    
    // ==================== РЕАЛИЗАЦИЯ МЕТОДОВ ====================
    
    // Управление заказами
    private void addCoffeeOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ ЗАКАЗА КОФЕ");
        System.out.println("=".repeat(50));
        
        try {
            System.out.print("ID кофейни: ");
            int shopId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ID сотрудника: ");
            int employeeId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ID клиента (0 если нет): ");
            int customerId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Сумма заказа: ");
            double totalAmount = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Заказ добавлен (нужна реализация)");
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
    
    private void addDessertOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ ЗАКАЗА ДЕСЕРТА");
        System.out.println("=".repeat(50));
        
        try {
            System.out.print("ID кофейни: ");
            int shopId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ID сотрудника: ");
            int employeeId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ID клиента (0 если нет): ");
            int customerId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Сумма заказа: ");
            double totalAmount = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Заказ десерта добавлен (нужна реализация)");
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
    
    private void updateOrderInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ ИНФОРМАЦИИ О ЗАКАЗЕ");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID заказа: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.println("Изменена информация о заказе ID: " + orderId);
    }
    
    private void deleteSpecificOrder() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ КОНКРЕТНОГО ЗАКАЗА");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID заказа: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.println("Удален заказ ID: " + orderId);
    }
    
    private void deleteOrdersByDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ ЗАКАЗОВ ПО ДЕСЕРТУ");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID десерта: ");
        int dessertId = Integer.parseInt(scanner.nextLine());
        System.out.println("Удалены заказы с десертом ID: " + dessertId);
    }
    
    private void showOrdersByDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ ПО ДЕСЕРТУ");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID десерта: ");
        int dessertId = Integer.parseInt(scanner.nextLine());
        System.out.println("Заказы с десертом ID: " + dessertId);
    }
    
    private void showOrdersByWaiter() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ ОФИЦИАНТА");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID официанта: ");
        int waiterId = Integer.parseInt(scanner.nextLine());
        System.out.println("Заказы официанта ID: " + waiterId);
    }
    
    private void showOrdersByCustomer() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАКАЗЫ КЛИЕНТА");
        System.out.println("=".repeat(50));
        
        System.out.println("1. По ID клиента");
        System.out.println("2. По телефону");
        System.out.print("Выберите способ поиска: ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice == 1) {
            System.out.print("Введите ID клиента: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            System.out.println("Заказы клиента ID: " + customerId);
        } else if (choice == 2) {
            System.out.print("Введите телефон клиента: ");
            String phone = scanner.nextLine();
            System.out.println("Заказы клиента с телефоном: " + phone);
        }
    }
    
    // Управление меню
    private void addNewCoffee() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО КОФЕ В КАТАЛОГ");
        System.out.println("=".repeat(50));
        
        System.out.print("Название кофе: ");
        String name = scanner.nextLine();
        
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        
        System.out.print("Категория: ");
        String category = scanner.nextLine();
        
        System.out.println("Добавлен кофе: " + name);
    }
    
    private void updateCoffeeName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ НАЗВАНИЯ КОФЕ");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID кофе: ");
        int drinkId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Новое название: ");
        String newName = scanner.nextLine();
        
        System.out.println("Изменено название кофе ID " + drinkId + " на: " + newName);
    }
    
    private void addNewDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО ДЕСЕРТА В КАТАЛОГ");
        System.out.println("=".repeat(50));
        
        System.out.print("Название десерт: ");
        String name = scanner.nextLine();
        
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        
        System.out.print("Категория: ");
        String category = scanner.nextLine();
        
        System.out.println("Добавлен десерт: " + name);
    }
    
    private void updateDessertName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ НАЗВАНИЯ ДЕСЕРТА");
        System.out.println("=".repeat(50));
        
        System.out.print("Введите ID десерта: ");
        int dessertId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Новое название: ");
        String newName = scanner.nextLine();
        
        System.out.println("Изменено название десерта ID " + dessertId + " на: " + newName);
    }
    
    // Управление расписанием
    private void addScheduleForNextMonday() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ РАСПИСАНИЯ НА ПОНЕДЕЛЬНИК");
        System.out.println("=".repeat(50));
        
        System.out.println("Расписание добавлено (нужна реализация)");
    }
    
    private void updateTuesdaySchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ РАСПИСАНИЯ НА ВТОРНИК");
        System.out.println("=".repeat(50));
        
        System.out.println("Расписание изменено (нужна реализация)");
    }
    
    private void deleteScheduleForDay() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ РАСПИСАНИЯ НА ДЕНЬ");
        System.out.println("=".repeat(50));
        
        System.out.print("День недели: ");
        String day = scanner.nextLine();
        System.out.println("Удалено расписание на " + day);
    }
    
    private void deleteScheduleBetweenDates() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ РАСПИСАНИЯ МЕЖДУ ДАТАМИ");
        System.out.println("=".repeat(50));
        
        System.out.print("Начальная дата (ГГГГ-ММ-ДД): ");
        String startDate = scanner.nextLine();
        
        System.out.print("Конечная дата (ГГГГ-ММ-ДД): ");
        String endDate = scanner.nextLine();
        
        System.out.println("Удалено расписание между " + startDate + " и " + endDate);
    }
    
    private void showScheduleForDay() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАСПИСАНИЕ НА ДЕНЬ");
        System.out.println("=".repeat(50));
        
        System.out.print("Дата (ГГГГ-ММ-ДД): ");
        String date = scanner.nextLine();
        System.out.println("Расписание на " + date);
    }
    
    // Управление кофейнями
    private void showAllCoffeeShops() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        try {
            coffeeShopService.displayAllCoffeeShopsBrief();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showCoffeeShopDetails() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДЕТАЛЬНАЯ ИНФОРМАЦИЯ О КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        try {
            coffeeShopService.displayCoffeeShopDetails(shopId);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showAllManagers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ МЕНЕДЖЕРЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список менеджеров (нужна реализация)");
    }
    
    private void assignManagerToCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("НАЗНАЧЕНИЕ МЕНЕДЖЕРА КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("ID сотрудника: ");
        int managerId = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Менеджер назначен (нужна реализация)");
    }
    
    private void showCoffeeShopStatistics() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СТАТИСТИКА КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        try {
            System.out.println(coffeeShopService.getCoffeeShopStatistics(shopId));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void showEmployeesByShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СОТРУДНИКИ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Сотрудники кофейни ID: " + shopId + " (нужна реализация)");
    }
    
    private void findCoffeeShopByName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОИСК КОФЕЙНИ ПО НАЗВАНИЮ");
        System.out.println("=".repeat(50));
        
        System.out.print("Название кофейни: ");
        String name = scanner.nextLine();
        
        try {
            var shop = coffeeShopService.findCoffeeShopByName(name);
            if (shop != null) {
                System.out.println("Найдена: " + shop.getShopName() + " (ID: " + shop.getShopId() + ")");
            } else {
                System.out.println("Кофейня не найдена");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void getOldestNewestCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("САМАЯ СТАРАЯ И НОВАЯ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        try {
            var oldest = coffeeShopService.getOldestCoffeeShop();
            var newest = coffeeShopService.getNewestCoffeeShop();
            
            if (oldest != null) {
                System.out.println("Самая старая: " + oldest.getShopName() + 
                                 " (открыта: " + oldest.getOpeningDate() + ")");
            }
            if (newest != null) {
                System.out.println("Самая новая: " + newest.getShopName() + 
                                 " (открыта: " + newest.getOpeningDate() + ")");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void getShopsByYear() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("КОФЕЙНИ ПО ГОДУ ОТКРЫТИЯ");
        System.out.println("=".repeat(50));
        
        System.out.print("Год открытия: ");
        int year = Integer.parseInt(scanner.nextLine());
        
        try {
            var shops = coffeeShopService.getCoffeeShopsOpenedInYear(year);
            if (shops != null && !shops.isEmpty()) {
                System.out.println("Кофейни, открытые в " + year + " году:");
                for (var shop : shops) {
                    System.out.println("- " + shop.getShopName() + " (ID: " + shop.getShopId() + ")");
                }
            } else {
                System.out.println("Кофейни не найдены");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void updateCoffeeShopContactInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ КОНТАКТНОЙ ИНФОРМАЦИИ");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Новый телефон: ");
        String phone = scanner.nextLine();
        
        System.out.print("Новый адрес: ");
        String address = scanner.nextLine();
        
        try {
            boolean success = coffeeShopService.updateContactInfo(shopId, phone, address);
            if (success) {
                System.out.println("Контактная информация обновлена");
            } else {
                System.out.println("Ошибка обновления");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void checkManagerVacancy() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПРОВЕРКА ВАКАНСИИ МЕНЕДЖЕРА");
        System.out.println("=".repeat(50));
        
        System.out.print("ID кофейни: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        
        try {
            boolean hasVacancy = coffeeShopService.hasManagerVacancy(shopId);
            if (hasVacancy) {
                System.out.println("В кофейне есть вакансия менеджера");
            } else {
                System.out.println("Менеджер уже назначен");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    // Общие операции
    private void addRow() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ СТРОКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("1. Добавить кофейню");
        System.out.println("2. Добавить клиента");
        System.out.print("Выберите тип: ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice == 1) {
            System.out.print("Название: ");
            String name = scanner.nextLine();
            System.out.print("Адрес: ");
            String address = scanner.nextLine();
            System.out.print("Телефон: ");
            String phone = scanner.nextLine();
            System.out.println("Кофейня добавлена");
        } else if (choice == 2) {
            System.out.print("Имя: ");
            String firstName = scanner.nextLine();
            System.out.print("Фамилия: ");
            String lastName = scanner.nextLine();
            System.out.println("Клиент добавлен");
        }
    }
    
    private void deleteRow() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ СТРОКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("1. Удалить кофейню");
        System.out.println("2. Удалить сотрудника");
        System.out.println("3. Удалить клиента");
        System.out.println("4. Удалить напиток");
        System.out.println("5. Удалить десерт");
        System.out.println("6. Удалить расписание");
        System.out.print("Выберите тип данных: ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println("Удаление выполнено (нужна реализация)");
    }
    
    private void updateRow() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ СТРОКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("Обновление строки (нужна реализация)");
    }
}