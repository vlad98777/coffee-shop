package com.coffeeshop;

import com.coffeeshop.ui.MainMenuController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("     🏪 СИСТЕМА УПРАВЛЕНИЯ КОФЕЙНЕЙ");
            System.out.println("=".repeat(60));
            
            // Инициализация базы данных
            System.out.println("\n🔧 Инициализация системы...");
            DatabaseConnection.initializeH2Database();
            DatabaseConnection.testConnection();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("     СИСТЕМА ГОТОВА К РАБОТЕ");
            System.out.println("=".repeat(60) + "\n");
            
            // Пауза для чтения
            System.out.print("Нажмите Enter для продолжения...");
            scanner.nextLine();
            
            // Запуск главного меню
            MainMenuController controller = new MainMenuController(scanner);
            controller.run();
            
        } catch (Exception e) {
            System.err.println("\n💥 КРИТИЧЕСКАЯ ОШИБКА: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            DatabaseConnection.closeConnection();
            System.out.println("\n" + "=".repeat(60));
            System.out.println("     СИСТЕМА ЗАВЕРШЕНА");
            System.out.println("=".repeat(60));
        }
    }
}