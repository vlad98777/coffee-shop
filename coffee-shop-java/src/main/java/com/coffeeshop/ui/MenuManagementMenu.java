package com.coffeeshop.ui;

import com.coffeeshop.service.MenuService;
import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class MenuManagementMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final MenuService menuService;
    
    public MenuManagementMenu(Scanner scanner, MenuService menuService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.menuService = menuService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("📋 УПРАВЛЕНИЕ МЕНЮ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить новый кофе в каталог");
            System.out.println("2.  Добавить новый десерт в каталог");
            System.out.println("3.  Изменить название кофе");
            System.out.println("4.  Изменить название десерта");
            System.out.println("5.  Показать все напитки");
            System.out.println("6.  Показать все десерты");
            System.out.println("7.  Добавить напиток в кофейню");
            System.out.println("8.  Добавить десерт в кофейню");
            System.out.println("9.  Обновить цену напитка в кофейне");
            System.out.println("10. Обновить цену десерта в кофейне");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addNewCoffee();
                case 2 -> addNewDessert();
                case 3 -> updateCoffeeName();
                case 4 -> updateDessertName();
                case 5 -> showAllDrinks();
                case 6 -> showAllDesserts();
                case 7 -> addDrinkToShop();
                case 8 -> addDessertToShop();
                case 9 -> updateDrinkPrice();
                case 10 -> updateDessertPrice();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addNewCoffee() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО КОФЕ В КАТАЛОГ");
        System.out.println("=".repeat(50));
        
        String name = inputHelper.readString("Название кофе: ");
        String description = inputHelper.readString("Описание: ");
        String category = inputHelper.readString("Категория: ");
        double price = inputHelper.getDoubleInput("Базовая цена: ");
        
        System.out.println("Добавлен кофе: " + name);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void addNewDessert() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО ДЕСЕРТА В КАТАЛОГ");
        System.out.println("=".repeat(50));
        
        String name = inputHelper.readString("Название десерта: ");
        String description = inputHelper.readString("Описание: ");
        String category = inputHelper.readString("Категория: ");
        double price = inputHelper.getDoubleInput("Базовая цена: ");
        
        System.out.println("Добавлен десерт: " + name);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void updateCoffeeName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ НАЗВАНИЯ КОФЕ");
        System.out.println("=".repeat(50));
        
        int drinkId = inputHelper.getIntInput("Введите ID кофе: ");
        String newName = inputHelper.readString("Новое название: ");
        
        System.out.println("Изменено название кофе ID " + drinkId + " на: " + newName);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void updateDessertName() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ НАЗВАНИЯ ДЕСЕРТА");
        System.out.println("=".repeat(50));
        
        int dessertId = inputHelper.getIntInput("Введите ID десерта: ");
        String newName = inputHelper.readString("Новое название: ");
        
        System.out.println("Изменено название десерта ID " + dessertId + " на: " + newName);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showAllDrinks() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ НАПИТКИ В КАТАЛОГЕ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список напитков:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showAllDesserts() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ ДЕСЕРТЫ В КАТАЛОГЕ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список десертов:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void addDrinkToShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НАПИТКА В КОФЕЙНЮ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int drinkId = inputHelper.getIntInput("ID напитка: ");
        double price = inputHelper.getDoubleInput("Цена в этой кофейне: ");
        
        System.out.println("Добавлен напиток ID " + drinkId + " в кофейню ID " + shopId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void addDessertToShop() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ ДЕСЕРТА В КОФЕЙНЮ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int dessertId = inputHelper.getIntInput("ID десерта: ");
        double price = inputHelper.getDoubleInput("Цена в этой кофейне: ");
        
        System.out.println("Добавлен десерт ID " + dessertId + " в кофейню ID " + shopId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void updateDrinkPrice() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ ЦЕНЫ НАПИТКА");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int drinkId = inputHelper.getIntInput("ID напитка: ");
        double newPrice = inputHelper.getDoubleInput("Новая цена: ");
        
        System.out.println("Обновлена цена напитка ID " + drinkId + " в кофейне ID " + shopId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void updateDessertPrice() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ ЦЕНЫ ДЕСЕРТА");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int dessertId = inputHelper.getIntInput("ID десерта: ");
        double newPrice = inputHelper.getDoubleInput("Новая цена: ");
        
        System.out.println("Обновлена цена десерт ID " + dessertId + " в кофейне ID " + shopId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
}