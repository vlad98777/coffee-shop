package com.coffeeshop.ui;

import com.coffeeshop.service.MenuService;
import com.coffeeshop.service.ScheduleService;
import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class EmployeeMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final MenuService menuService;
    private final ScheduleService scheduleService;
    
    public EmployeeMenu(Scanner scanner, MenuService menuService, ScheduleService scheduleService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.menuService = menuService;
        this.scheduleService = scheduleService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("👥 УПРАВЛЕНИЕ СОТРУДНИКАМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить нового сотрудника");
            System.out.println("2.  Показать всех сотрудников");
            System.out.println("3.  Показать сотрудников кофейни");
            System.out.println("4.  Найти сотрудника по ID");
            System.out.println("5.  Обновить информацию о сотруднике");
            System.out.println("6.  Удалить сотрудника");
            System.out.println("7.  Показать расписание сотрудника");
            System.out.println("8.  Показать баристов");
            System.out.println("9.  Показать официантов");
            System.out.println("10. Показать менеджеров");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> showAllEmployees();
                case 3 -> showEmployeesByShopId();
                case 4 -> findEmployeeById();
                case 5 -> updateEmployeeInfo();
                case 6 -> deleteEmployee();
                case 7 -> showEmployeeScheduleById();
                case 8 -> showBaristas();
                case 9 -> showWaiters();
                case 10 -> showManagers();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addEmployee() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО СОТРУДНИКА");
        System.out.println("=".repeat(50));
        
        String firstName = inputHelper.readString("Имя: ");
        String lastName = inputHelper.readString("Фамилия: ");
        String position = inputHelper.readString("Должность: ");
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        double salary = inputHelper.getDoubleInput("Зарплата: ");
        String email = inputHelper.readString("Email: ");
        String hireDate = inputHelper.readString("Дата приема (ГГГГ-ММ-ДД): ");
        
        System.out.println("Добавлен новый сотрудник: " + firstName + " " + lastName);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showAllEmployees() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ СОТРУДНИКИ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список всех сотрудников:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showEmployeesByShopId() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СОТРУДНИКИ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        System.out.println("Сотрудники кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void findEmployeeById() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОИСК СОТРУДНИКА ПО ID");
        System.out.println("=".repeat(50));
        
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        System.out.println("Поиск сотрудника ID: " + employeeId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void updateEmployeeInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ ИНФОРМАЦИИ О СОТРУДНИКЕ");
        System.out.println("=".repeat(50));
        
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        System.out.println("Обновлена информация о сотруднике ID: " + employeeId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void deleteEmployee() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ СОТРУДНИКА");
        System.out.println("=".repeat(50));
        
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        System.out.println("Удален сотрудник ID: " + employeeId);
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showEmployeeScheduleById() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАСПИСАНИЕ СОТРУДНИКА");
        System.out.println("=".repeat(50));
        
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        System.out.println("Расписание сотрудника ID: " + employeeId);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void showBaristas() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("БАРИСТЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список баристов:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showWaiters() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОФИЦИАНТЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список официантов:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
    
    private void showManagers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("МЕНЕДЖЕРЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список менеджеров:");
        System.out.println("(Функция требует реализации в MenuService)");
    }
}