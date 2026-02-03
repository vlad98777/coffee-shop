package com.coffeeshop.ui.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;
    
    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите целое число: ");
            }
        }
    }
    
    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите число: ");
            }
        }
    }
    
    public LocalDate readDate(String prompt) {
        System.out.print(prompt + " (ГГГГ-ММ-ДД): ");
        while (true) {
            try {
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException e) {
                System.out.print("Неверный формат даты. Введите дату (ГГГГ-ММ-ДД): ");
            }
        }
    }
    
    public LocalTime readTime(String prompt) {
        System.out.print(prompt + " (ЧЧ:ММ): ");
        while (true) {
            try {
                String timeStr = scanner.nextLine().trim();
                return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.print("Неверный формат времени. Введите время (ЧЧ:ММ): ");
            }
        }
    }
    
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public void waitForEnter() {
        System.out.print("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }
}
