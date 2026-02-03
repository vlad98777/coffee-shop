package com.coffeeshop.ui;

import com.coffeeshop.service.CustomerService;
import com.coffeeshop.ui.utils.InputHelper;
import java.util.Scanner;

public class CustomerMenu {
    private final Scanner scanner;
    private final InputHelper inputHelper;
    private final CustomerService customerService;
    
    public CustomerMenu(Scanner scanner, CustomerService customerService) {
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.customerService = customerService;
    }
    
    public void show() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("👤 УПРАВЛЕНИЕ КЛИЕНТАМИ");
            System.out.println("=".repeat(60));
            System.out.println("1.  Добавить нового клиента");
            System.out.println("2.  Найти клиента по телефону");
            System.out.println("3.  Найти клиента по ID");
            System.out.println("4.  Показать всех клиентов");
            System.out.println("5.  Обновить информацию о клиенте");
            System.out.println("6.  Удалить клиента");
            System.out.println("7.  Установить скидку клиенту");
            System.out.println("8.  Показать клиентов с днем рождения сегодня");
            System.out.println("9.  Показать клиентов без адреса");
            System.out.println("10. Показать клиентов по диапазону возрастов");
            System.out.println("0.  Назад в главное меню");
            System.out.println("=".repeat(60));
            
            int choice = inputHelper.getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> findCustomerByPhone();
                case 3 -> findCustomerById();
                case 4 -> showAllCustomers();
                case 5 -> updateCustomerInfo();
                case 6 -> deleteCustomer();
                case 7 -> setCustomerDiscount();
                case 8 -> showBirthdayCustomers();
                case 9 -> showCustomersWithoutAddress();
                case 10 -> showCustomersByAgeRange();
                case 0 -> exit = true;
                default -> System.out.println("Неверный выбор!");
            }
            
            if (choice != 0) {
                inputHelper.waitForEnter();
            }
        }
    }
    
    private void addCustomer() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОБАВЛЕНИЕ НОВОГО КЛИЕНТА");
        System.out.println("=".repeat(50));
        
        String firstName = inputHelper.readString("Имя: ");
        String lastName = inputHelper.readString("Фамилия: ");
        String email = inputHelper.readString("Email: ");
        String phone = inputHelper.readString("Телефон: ");
        String birthDate = inputHelper.readString("Дата рождения (ГГГГ-ММ-ДД): ");
        String address = inputHelper.readString("Адрес: ");
        double discount = inputHelper.getDoubleInput("Скидка (%): ");
        
        System.out.println("Добавлен новый клиент: " + firstName + " " + lastName);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void findCustomerByPhone() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОИСК КЛИЕНТА ПО ТЕЛЕФОНУ");
        System.out.println("=".repeat(50));
        
        String phone = inputHelper.readString("Телефон: ");
        System.out.println("Поиск клиента с телефоном: " + phone);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void findCustomerById() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОИСК КЛИЕНТА ПО ID");
        System.out.println("=".repeat(50));
        
        int customerId = inputHelper.getIntInput("ID клиента: ");
        System.out.println("Поиск клиента ID: " + customerId);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void showAllCustomers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВСЕ КЛИЕНТЫ");
        System.out.println("=".repeat(50));
        
        System.out.println("Список всех клиентов:");
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void updateCustomerInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБНОВЛЕНИЕ ИНФОРМАЦИИ О КЛИЕНТЕ");
        System.out.println("=".repeat(50));
        
        int customerId = inputHelper.getIntInput("ID клиента: ");
        System.out.println("Обновлена информация о клиенте ID: " + customerId);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void deleteCustomer() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УДАЛЕНИЕ КЛИЕНТА");
        System.out.println("=".repeat(50));
        
        int customerId = inputHelper.getIntInput("ID клиента: ");
        System.out.println("Удален клиент ID: " + customerId);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void setCustomerDiscount() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("УСТАНОВКА СКИДКИ КЛИЕНТУ");
        System.out.println("=".repeat(50));
        
        int customerId = inputHelper.getIntInput("ID клиента: ");
        double discount = inputHelper.getDoubleInput("Размер скидки (%): ");
        
        System.out.println("Установлена скидка " + discount + "% клиенту ID: " + customerId);
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void showBirthdayCustomers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("КЛИЕНТЫ С ДНЕМ РОЖДЕНИЯ СЕГОДНЯ");
        System.out.println("=".repeat(50));
        
        System.out.println("Клиенты с днем рождения сегодня:");
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void showCustomersWithoutAddress() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("КЛИЕНТЫ БЕЗ АДРЕСА");
        System.out.println("=".repeat(50));
        
        System.out.println("Клиенты без указанного адреса:");
        System.out.println("(Функция требует реализации в CustomerService)");
    }
    
    private void showCustomersByAgeRange() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("КЛИЕНТЫ ПО ВОЗРАСТУ");
        System.out.println("=".repeat(50));
        
        int minAge = inputHelper.getIntInput("Минимальный возраст: ");
        int maxAge = inputHelper.getIntInput("Максимальный возраст: ");
        
        System.out.println("Клиенты в возрасте от " + minAge + " до " + maxAge + " лет:");
        System.out.println("(Функция требует реализации в CustomerService)");
    }
}