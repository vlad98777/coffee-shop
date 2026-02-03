package com.coffeeshop.service;

import com.coffeeshop.dao.*;
import com.coffeeshop.models.*;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();
    
    // 1. Добавление клиента
    public int addCustomer(Customer customer) {
        try {
            return customerDAO.addCustomer(customer);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении клиента: " + e.getMessage());
            return -1;
        }
    }
    
    // 2. Удаление клиента
    public boolean deleteCustomer(int customerId) {
        try {
            return customerDAO.deleteCustomer(customerId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении клиента: " + e.getMessage());
            return false;
        }
    }
    
    // 3. Получение клиента по ID
    public Customer getCustomerById(int customerId) {
        try {
            return customerDAO.getCustomerById(customerId);
        } catch (Exception e) {
            System.err.println("Ошибка при получении клиента: " + e.getMessage());
            return null;
        }
    }
    
    // 4. Получение клиента по телефону
    public Customer getCustomerByPhone(String phone) {
        try {
            return customerDAO.getCustomerByPhone(phone);
        } catch (Exception e) {
            System.err.println("Ошибка при получении клиента: " + e.getMessage());
            return null;
        }
    }
    
    // 5. Получение всех клиентов
    public List<Customer> getAllCustomers() {
        try {
            return customerDAO.getAllCustomers();
        } catch (Exception e) {
            System.err.println("Ошибка при получении клиентов: " + e.getMessage());
            return null;
        }
    }
}