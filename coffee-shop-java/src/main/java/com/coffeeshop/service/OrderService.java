package com.coffeeshop.service;

import com.coffeeshop.dao.*;
import com.coffeeshop.models.*;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderDrinkDAO orderDrinkDAO = new OrderDrinkDAO();
    private OrderDessertDAO orderDessertDAO = new OrderDessertDAO();
    
    // 1. Создание заказа с напитками
    public int createCoffeeOrder(Order order, List<OrderDrink> coffeeItems) {
        try {
            return orderDAO.addCoffeeOrder(order, coffeeItems);
        } catch (Exception e) {
            System.err.println("Ошибка при создании заказа кофе: " + e.getMessage());
            return -1;
        }
    }
    
    // 2. Создание заказа с десертами
    public int createDessertOrder(Order order, List<OrderDessert> dessertItems) {
        try {
            return orderDAO.addDessertOrder(order, dessertItems);
        } catch (Exception e) {
            System.err.println("Ошибка при создании заказа десерта: " + e.getMessage());
            return -1;
        }
    }
    
    // 3. Обновление заказа
    public boolean updateOrder(Order order) {
        try {
            return orderDAO.updateOrder(order);
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении заказа: " + e.getMessage());
            return false;
        }
    }
    
    // 4. Удаление заказа
    public boolean deleteOrder(int orderId) {
        try {
            return orderDAO.deleteOrder(orderId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении заказа: " + e.getMessage());
            return false;
        }
    }
    
    // 5. Удаление заказов по десерту
    public int deleteOrdersByDessert(int dessertId) {
        try {
            return orderDAO.deleteOrdersByDessert(dessertId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении заказов по десерту: " + e.getMessage());
            return 0;
        }
    }
    
    // 6. Получение заказов по десерту
    public List<Order> getOrdersByDessert(int dessertId) {
        try {
            return orderDAO.getOrdersByDessert(dessertId);
        } catch (Exception e) {
            System.err.println("Ошибка при получении заказов по десерту: " + e.getMessage());
            return null;
        }
    }
    
    // 7. Получение заказов по официанту
    public List<Order> getOrdersByWaiter(int employeeId) {
        try {
            return orderDAO.getOrdersByWaiter(employeeId);
        } catch (Exception e) {
            System.err.println("Ошибка при получении заказов официанта: " + e.getMessage());
            return null;
        }
    }
    
    // 8. Получение заказов по ID клиента
    public List<Order> getOrdersByCustomerId(int customerId) {
        try {
            return orderDAO.getOrdersByCustomerId(customerId);
        } catch (Exception e) {
            System.err.println("Ошибка при получении заказов клиента: " + e.getMessage());
            return null;
        }
    }
    
    // 9. Получение заказов по телефону клиента
    public List<Order> getOrdersByCustomerPhone(String phone) {
        try {
            return orderDAO.getOrdersByCustomerPhone(phone);
        } catch (Exception e) {
            System.err.println("Ошибка при получении заказов клиента: " + e.getMessage());
            return null;
        }
    }
    
    // 10. Получение заказа по ID
    public Order getOrderById(int orderId) {
        try {
            return orderDAO.getOrderById(orderId);
        } catch (Exception e) {
            System.err.println("Ошибка при получении заказа: " + e.getMessage());
            return null;
        }
    }
}