package com.coffeeshop.ui;

import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class GeneralMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    
    public GeneralMenu(Scanner scanner) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("⚙️  ОБЩИЕ ОПЕРАЦИИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить строку (общая функция)");
            System.out.println("2.  Удалить строку (общая функция)");
            System.out.println("3.  Обновить строку (общая функция)");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addNewRow();
                case 2 -> deleteRow();
                case 3 -> updateRow();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addNewRow() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ СТРОКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("1. Добавить кофейню");
        System.out.println("2. Добавить сотрудника");
        System.out.println("3. Добавить клиента");
        System.out.println("4. Добавить напиток");
        System.out.println("5. Добавить десерт");
        System.out.println("6. Добавить расписание");
        
        int choice = inputHelper.getIntInput("Выберите тип данных: ");
        
        System.out.println("Функция добавления строки. Реализация зависит от типа данных.");
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
        
        int choice = inputHelper.getIntInput("Выберите тип данных: ");
        int id = inputHelper.getIntInput("Введите ID для удаления: ");
        
        System.out.println("Удалена строка типа " + choice + " с ID: " + id);
    }
    
    private void updateRow() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ СТРОКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("1. Обновить кофейню");
        System.out.println("2. Обновить сотрудника");
        System.out.println("3. Обновить клиента");
        System.out.println("4. Обновить напиток");
        System.out.println("5. Обновить десерт");
        System.out.println("6. Обновить расписание");
        
        int choice = inputHelper.getIntInput("Выберите тип данных: ");
        
        System.out.println("Функция обновления строки. Реализация зависит от типа данных.");
    }
}