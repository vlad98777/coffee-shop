package com.coffeeshop.service;

import com.coffeeshop.dao.*;
import com.coffeeshop.models.*;
import java.util.List;

public class MenuService {
    private DrinkDAO drinkDAO = new DrinkDAO();
    private DessertDAO dessertDAO = new DessertDAO();
    private CoffeeShopDAO coffeeShopDAO = new CoffeeShopDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    
    // 1. Добавление нового кофе
    public int addNewDrink(Drink drink) {
        try {
            return drinkDAO.addNewDrink(drink);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении напитка: " + e.getMessage());
            return -1;
        }
    }
    
    // 2. Изменение названия кофе
    public boolean updateDrinkName(int drinkId, String newName) {
        try {
            return drinkDAO.updateDrinkName(drinkId, newName);
        } catch (Exception e) {
            System.err.println("Ошибка при изменении названия напитка: " + e.getMessage());
            return false;
        }
    }
    
    // 3. Получение всех напитков
    public List<Drink> getAllDrinks() {
        try {
            return drinkDAO.getAllDrinks();
        } catch (Exception e) {
            System.err.println("Ошибка при получении напитков: " + e.getMessage());
            return null;
        }
    }
    
    // 4. Добавление напитка в кофейню
    public boolean addDrinkToShop(int shopId, int drinkId, double price) {
        try {
            return drinkDAO.addDrinkToShop(shopId, drinkId, price);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении напитка в кофейню: " + e.getMessage());
            return false;
        }
    }
    
    // 5. Изменение названия десерта
    public boolean updateDessertName(int dessertId, String newName) {
        try {
            return dessertDAO.updateDessertName(dessertId, newName);
        } catch (Exception e) {
            System.err.println("Ошибка при изменении названия десерта: " + e.getMessage());
            return false;
        }
    }
    
    // 6. Добавление нового десерта
    public int addNewDessert(Dessert dessert) {
        try {
            return dessertDAO.addNewDessert(dessert);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении десерта: " + e.getMessage());
            return -1;
        }
    }
    
    // 7. Получение всех десертов
    public List<Dessert> getAllDesserts() {
        try {
            return dessertDAO.getAllDesserts();
        } catch (Exception e) {
            System.err.println("Ошибка при получении десертов: " + e.getMessage());
            return null;
        }
    }
    
    // 8. Удаление напитка
    public boolean deleteDrink(int drinkId) {
        try {
            return drinkDAO.deleteDrink(drinkId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении напитка: " + e.getMessage());
            return false;
        }
    }
    
    // 9. Удаление десерта
    public boolean deleteDessert(int dessertId) {
        try {
            return dessertDAO.deleteDessert(dessertId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении десерта: " + e.getMessage());
            return false;
        }
    }
    
    // 10. Добавление кофейни
    public boolean addCoffeeShop(CoffeeShop shop) {
        try {
            int id = coffeeShopDAO.addCoffeeShop(shop);
            return id > 0;
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 11. Удаление кофейни
    public boolean deleteCoffeeShop(int shopId) {
        try {
            return coffeeShopDAO.deleteCoffeeShop(shopId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 12. Получение всех кофеен
    public List<CoffeeShop> getAllCoffeeShops() {
        try {
            return coffeeShopDAO.getAllCoffeeShops();
        } catch (Exception e) {
            System.err.println("Ошибка при получении кофеен: " + e.getMessage());
            return null;
        }
    }
    
    // 13. Добавление сотрудника
    public boolean addEmployee(Employee employee) {
        try {
            int id = employeeDAO.addEmployee(employee);
            return id > 0;
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении сотрудника: " + e.getMessage());
            return false;
        }
    }
    
    // 14. Удаление сотрудника
    public boolean deleteEmployee(int employeeId) {
        try {
            return employeeDAO.deleteEmployee(employeeId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении сотрудника: " + e.getMessage());
            return false;
        }
    }
}