package com.coffeeshop.ui;

import com.coffeeshop.service.CoffeeShopService;
import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class CoffeeShopMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final CoffeeShopService coffeeShopService;
    
    public CoffeeShopMenu(Scanner scanner, CoffeeShopService coffeeShopService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.coffeeShopService = coffeeShopService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🏪 УПРАВЛЕНИЕ КОФЕЙНЯМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Показать все кофейни");
            System.out.println("2.  Показать детальную информацию о кофейне");
            System.out.println("3.  Показать всех менеджеров");
            System.out.println("4.  Назначить менеджера кофейне");
            System.out.println("5.  Показать статистику кофейни");
            System.out.println("6.  Показать сотрудников кофейни");
            System.out.println("7.  Найти кофейню по названию");
            System.out.println("8.  Показать самую старую/новую кофейню");
            System.out.println("9.  Показать кофейни по году открытия");
            System.out.println("10. Обновить контактную информацию кофейни");
            System.out.println("11. Проверить вакансию менеджера");
            System.out.println("12. Добавить новую кофейню");
            System.out.println("13. Обновить информацию о кофейне");
            System.out.println("14. Удалить кофейню");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> showAllCoffeeShops();
                case 2 -> showCoffeeShopDetails();
                case 3 -> showAllManagers();
                case 4 -> assignManagerToCoffeeShop();
                case 5 -> showCoffeeShopStatistics();
                case 6 -> showEmployeesByShop();
                case 7 -> findCoffeeShopByName();
                case 8 -> getOldestNewestCoffeeShop();
                case 9 -> getShopsByYear();
                case 10 -> updateCoffeeShopContactInfo();
                case 11 -> checkManagerVacancy();
                case 12 -> addCoffeeShop();
                case 13 -> updateCoffeeShopInfo();
                case 14 -> deleteCoffeeShop();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void showAllCoffeeShops() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список кофеен:");
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void showCoffeeShopDetails() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДЕТАЛЬНАЯ ИНФОРМАЦИЯ О КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Информация о кофейне ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void showAllManagers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ МЕНЕДЖЕРЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список менеджеров:");
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void assignManagerToCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("НАЗНАЧЕНИЕ МЕНЕДЖЕРА КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int managerId = inputHelper.getIntInput("ID сотрудника (менеджера): ");
        
        System.out.println("Назначен менеджер ID " + managerId + " на кофейню ID " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void showCoffeeShopStatistics() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СТАТИСТИКА КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Статистика кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void showEmployeesByShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СОТРУДНИКИ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Сотрудники кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void findCoffeeShopByName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОИСК КОФЕЙНИ ПО НАЗВАНИЮ");
        System.out.println("=".repeat(50));
        
        String shopName = inputHelper.readString("Название кофейни: ");
        System.out.println("Поиск кофейни: " + shopName);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void getOldestNewestCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("САМАЯ СТАРАЯ И НОВАЯ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        System.out.println("Самая старая и новая кофейни:");
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void getShopsByYear() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("КОФЕЙНИ ПО ГОДУ ОТКРЫТИЯ");
        System.out.println("=".repeat(50));
        
        int year = inputHelper.getIntInput("Год открытия: ");
        System.out.println("Кофейни, открытые в " + year + " году:");
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void updateCoffeeShopContactInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ КОНТАКТНОЙ ИНФОРМАЦИИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        String newPhone = inputHelper.readString("Новый телефон: ");
        String newAddress = inputHelper.readString("Новый адрес: ");
        
        System.out.println("Обновлена контактная информация кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void checkManagerVacancy() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПРОВЕРКА ВАКАНСИИ МЕНЕДЖЕРА");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Проверка вакансии менеджера в кофейне ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void addCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОЙ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        String name = inputHelper.readString("Название кофейни: ");
        String address = inputHelper.readString("Адрес: ");
        String phone = inputHelper.readString("Телефон: ");
        String openDate = inputHelper.readString("Дата открытия (ГГГГ-ММ-ДД): ");
        
        System.out.println("Добавлена новая кофейня: " + name);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void updateCoffeeShopInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ ИНФОРМАЦИИ О КОФЕЙНЕ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Обновлена информация о кофейне ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
    
    private void deleteCoffeeShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Удалена кофейня ID: " + shopId);
        System.out.println("(Функция требует реализации в CoffeeShopService)");
    }
}
