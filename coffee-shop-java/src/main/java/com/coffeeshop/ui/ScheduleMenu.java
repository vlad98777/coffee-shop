package com.coffeeshop.ui;

import com.coffeeshop.service.ScheduleService;
import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class ScheduleMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final ScheduleService scheduleService;
    
    public ScheduleMenu(Scanner scanner, ScheduleService scheduleService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.scheduleService = scheduleService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🕐 УПРАВЛЕНИЕ РАСПИСАНИЕМ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить расписание на ближайший понедельник");
            System.out.println("2.  Изменить расписание на вторник");
            System.out.println("3.  Удалить расписание на день");
            System.out.println("4.  Удалить расписание между датами");
            System.out.println("5.  Показать расписание на день");
            System.out.println("6.  Показать расписание кофейни");
            System.out.println("7.  Показать расписание сотрудника");
            System.out.println("8.  Добавить новое расписание");
            System.out.println("9.  Обновить существующее расписание");
            System.out.println("10. Удалить расписание по ID");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addScheduleForNextMonday();
                case 2 -> updateTuesdaySchedule();
                case 3 -> deleteScheduleForDay();
                case 4 -> deleteScheduleBetweenDates();
                case 5 -> showScheduleForDay();
                case 6 -> showShopSchedule();
                case 7 -> showEmployeeSchedule();
                case 8 -> addGenericSchedule();
                case 9 -> updateSchedule();
                case 10 -> deleteScheduleById();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addScheduleForNextMonday() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ РАСПИСАНИЯ НА ПОНЕДЕЛЬНИК");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        String startTime = inputHelper.readString("Время начала (HH:MM): ");
        String endTime = inputHelper.readString("Время окончания (HH:MM): ");
        
        System.out.println("Добавлено расписание на понедельник");
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void updateTuesdaySchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИЗМЕНЕНИЕ РАСПИСАНИЯ НА ВТОРНИК");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        String newStartTime = inputHelper.readString("Новое время начала (HH:MM): ");
        String newEndTime = inputHelper.readString("Новое время окончания (HH:MM): ");
        
        System.out.println("Изменено расписание на вторник");
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void deleteScheduleForDay() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ РАСПИСАНИЯ НА ДЕНЬ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        String dayOfWeek = inputHelper.readString("День недели: ");
        
        System.out.println("Удалено расписание на " + dayOfWeek);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void deleteScheduleBetweenDates() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ РАСПИСАНИЯ МЕЖДУ ДАТАМИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        String startDate = inputHelper.readString("Начальная дата (ГГГГ-ММ-ДД): ");
        String endDate = inputHelper.readString("Конечная дата (ГГГГ-ММ-ДД): ");
        
        System.out.println("Удалено расписание с " + startDate + " по " + endDate);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void showScheduleForDay() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАСПИСАНИЕ НА ДЕНЬ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        String date = inputHelper.readString("Дата (ГГГГ-ММ-ДД): ");
        
        System.out.println("Расписание на " + date);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void showShopSchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАСПИСАНИЕ КОФЕЙНИ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        
        System.out.println("Расписание кофейни ID: " + shopId);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void showEmployeeSchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАСПИСАНИЕ СОТРУДНИКА");
        System.out.println("=".repeat(50));
        
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        
        System.out.println("Расписание сотрудника ID: " + employeeId);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void addGenericSchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ РАСПИСАНИЯ");
        System.out.println("=".repeat(50));
        
        int shopId = inputHelper.getIntInput("ID кофейни: ");
        int employeeId = inputHelper.getIntInput("ID сотрудника: ");
        String dayOfWeek = inputHelper.readString("День недели: ");
        String startTime = inputHelper.readString("Время начала (HH:MM): ");
        String endTime = inputHelper.readString("Время окончания (HH:MM): ");
        
        System.out.println("Добавлено расписание");
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void updateSchedule() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ РАСПИСАНИЯ");
        System.out.println("=".repeat(50));
        
        int scheduleId = inputHelper.getIntInput("ID расписания: ");
        
        System.out.println("Обновлено расписание ID: " + scheduleId);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
    
    private void deleteScheduleById() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ РАСПИСАНИЯ ПО ID");
        System.out.println("=".repeat(50));
        
        int scheduleId = inputHelper.getIntInput("ID расписания: ");
        
        System.out.println("Удалено расписание ID: " + scheduleId);
        System.out.println("(Функция требует реализации в ScheduleService)");
    }
}