package com.coffeeshop.service;

import com.coffeeshop.dao.*;
import com.coffeeshop.models.*;
import java.util.List;

public class CoffeeShopService {
    private CoffeeShopDAO coffeeShopDAO = new CoffeeShopDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    
    // 1. Получить информацию о всех кофейнях
    public List<CoffeeShop> getAllCoffeeShops() {
        try {
            return coffeeShopDAO.getAllCoffeeShops();
        } catch (Exception e) {
            System.err.println("Ошибка при получении списка кофеен: " + e.getMessage());
            return null;
        }
    }
    
    // 2. Получить информацию о конкретной кофейне
    public CoffeeShop getCoffeeShopById(int shopId) {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            for (CoffeeShop shop : shops) {
                if (shop.getShopId() == shopId) {
                    return shop;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Ошибка при получении кофейни: " + e.getMessage());
            return null;
        }
    }
    
    // 3. Добавить новую кофейню
    public boolean addCoffeeShop(CoffeeShop coffeeShop) {
        try {
            int newShopId = coffeeShopDAO.addCoffeeShop(coffeeShop);
            return newShopId > 0;
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 4. Обновить информацию о кофейне
    public boolean updateCoffeeShop(CoffeeShop coffeeShop) {
        try {
            return coffeeShopDAO.updateCoffeeShop(coffeeShop);
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 5. Удалить кофейню
    public boolean deleteCoffeeShop(int shopId) {
        try {
            return coffeeShopDAO.deleteCoffeeShop(shopId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 6. Получить сотрудников кофейни
    public List<Employee> getEmployeesByShop(int shopId) {
        try {
            List<Employee> allEmployees = employeeDAO.getAllEmployees();
            List<Employee> shopEmployees = new java.util.ArrayList<>();
            
            for (Employee employee : allEmployees) {
                if (employee.getShopId() == shopId) {
                    shopEmployees.add(employee);
                }
            }
            
            return shopEmployees;
        } catch (Exception e) {
            System.err.println("Ошибка при получении сотрудников кофейни: " + e.getMessage());
            return null;
        }
    }
    
    // 7. Получить статистику по кофейне
    public String getCoffeeShopStatistics(int shopId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            if (shop == null) {
                return "Кофейня с ID " + shopId + " не найдена";
            }
            
            List<Employee> employees = getEmployeesByShop(shopId);
            int employeeCount = employees != null ? employees.size() : 0;
            
            // Здесь можно добавить логику для получения статистики продаж,
            // количества заказов и т.д. из OrderDAO
            
            return String.format(
                "Статистика кофейни \"%s\":\n" +
                "Адрес: %s\n" +
                "Телефон: %s\n" +
                "Количество сотрудников: %d\n" +
                "Дата открытия: %s",
                shop.getShopName(),
                shop.getAddress(),
                shop.getPhone(),
                employeeCount,
                shop.getOpeningDate().toString()
            );
            
        } catch (Exception e) {
            return "Ошибка при получении статистики: " + e.getMessage();
        }
    }
    
    // 8. Найти кофейню по названию
    public CoffeeShop findCoffeeShopByName(String shopName) {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            for (CoffeeShop shop : shops) {
                if (shop.getShopName().equalsIgnoreCase(shopName)) {
                    return shop;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Ошибка при поиске кофейни: " + e.getMessage());
            return null;
        }
    }
    
    // 9. Получить менеджера кофейни
    public Employee getShopManager(int shopId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            if (shop == null || shop.getManagerId() == null) {
                return null;
            }
            
            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Employee employee : employees) {
                if (employee.getEmployeeId() == shop.getManagerId()) {
                    return employee;
                }
            }
            
            return null;
        } catch (Exception e) {
            System.err.println("Ошибка при получении менеджера: " + e.getMessage());
            return null;
        }
    }
    
    // 10. Назначить менеджера кофейни
    public boolean setShopManager(int shopId, int employeeId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            if (shop == null) {
                System.err.println("Кофейня не найдена");
                return false;
            }
            
            // Проверяем, существует ли сотрудник
            List<Employee> employees = employeeDAO.getAllEmployees();
            boolean employeeExists = false;
            for (Employee emp : employees) {
                if (emp.getEmployeeId() == employeeId) {
                    employeeExists = true;
                    break;
                }
            }
            
            if (!employeeExists) {
                System.err.println("Сотрудник не найден");
                return false;
            }
            
            shop.setManagerId(employeeId);
            return coffeeShopDAO.updateCoffeeShop(shop);
            
        } catch (Exception e) {
            System.err.println("Ошибка при назначении менеджера: " + e.getMessage());
            return false;
        }
    }
    
    // 11. Проверить, существует ли кофейня
    public boolean coffeeShopExists(int shopId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            return shop != null;
        } catch (Exception e) {
            System.err.println("Ошибка при проверке кофейни: " + e.getMessage());
            return false;
        }
    }
    
    // 12. Получить общее количество кофеен
    public int getTotalCoffeeShopsCount() {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            return shops != null ? shops.size() : 0;
        } catch (Exception e) {
            System.err.println("Ошибка при подсчете кофеен: " + e.getMessage());
            return 0;
        }
    }
    
    // 13. Получить самую старую кофейню
    public CoffeeShop getOldestCoffeeShop() {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            if (shops == null || shops.isEmpty()) {
                return null;
            }
            
            CoffeeShop oldest = shops.get(0);
            for (CoffeeShop shop : shops) {
                if (shop.getOpeningDate().before(oldest.getOpeningDate())) {
                    oldest = shop;
                }
            }
            
            return oldest;
        } catch (Exception e) {
            System.err.println("Ошибка при поиске самой старой кофейни: " + e.getMessage());
            return null;
        }
    }
    
    // 14. Получить самую новую кофейню
    public CoffeeShop getNewestCoffeeShop() {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            if (shops == null || shops.isEmpty()) {
                return null;
            }
            
            CoffeeShop newest = shops.get(0);
            for (CoffeeShop shop : shops) {
                if (shop.getOpeningDate().after(newest.getOpeningDate())) {
                    newest = shop;
                }
            }
            
            return newest;
        } catch (Exception e) {
            System.err.println("Ошибка при поиске самой новой кофейни: " + e.getMessage());
            return null;
        }
    }
    
    // 15. Получить кофейни по местоположению (по адресу)
    public List<CoffeeShop> getCoffeeShopsByLocation(String locationKeyword) {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            List<CoffeeShop> filteredShops = new java.util.ArrayList<>();
            
            for (CoffeeShop shop : shops) {
                if (shop.getAddress().toLowerCase().contains(locationKeyword.toLowerCase())) {
                    filteredShops.add(shop);
                }
            }
            
            return filteredShops;
        } catch (Exception e) {
            System.err.println("Ошибка при поиске кофеен по местоположению: " + e.getMessage());
            return null;
        }
    }
    
    // 16. Обновить контактную информацию кофейни
    public boolean updateContactInfo(int shopId, String phone, String address) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            if (shop == null) {
                return false;
            }
            
            shop.setPhone(phone);
            shop.setAddress(address);
            
            return coffeeShopDAO.updateCoffeeShop(shop);
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении контактной информации: " + e.getMessage());
            return false;
        }
    }
    
    // 17. Получить краткую информацию о всех кофейнях
    public void displayAllCoffeeShopsBrief() {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            if (shops == null || shops.isEmpty()) {
                System.out.println("Кофейни не найдены.");
                return;
            }
            
            System.out.println("\n=== СПИСОК КОФЕЕН ===");
            System.out.println("Всего кофеен: " + shops.size());
            System.out.println("─────────────────────────────");
            
            for (CoffeeShop shop : shops) {
                Employee manager = getShopManager(shop.getShopId());
                List<Employee> employees = getEmployeesByShop(shop.getShopId());
                
                System.out.println("ID: " + shop.getShopId());
                System.out.println("Название: " + shop.getShopName());
                System.out.println("Адрес: " + shop.getAddress());
                System.out.println("Телефон: " + shop.getPhone());
                System.out.println("Менеджер: " + (manager != null ? 
                    manager.getFirstName() + " " + manager.getLastName() : "не назначен"));
                System.out.println("Сотрудников: " + (employees != null ? employees.size() : 0));
                System.out.println("─────────────────────────────");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при выводе информации о кофейнях: " + e.getMessage());
        }
    }
    
    // 18. Получить детальную информацию о кофейне
    public void displayCoffeeShopDetails(int shopId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            if (shop == null) {
                System.out.println("Кофейня с ID " + shopId + " не найдена.");
                return;
            }
            
            Employee manager = getShopManager(shopId);
            List<Employee> employees = getEmployeesByShop(shopId);
            
            System.out.println("\n=== ДЕТАЛЬНАЯ ИНФОРМАЦИЯ О КОФЕЙНЕ ===");
            System.out.println("ID: " + shop.getShopId());
            System.out.println("Название: " + shop.getShopName());
            System.out.println("Адрес: " + shop.getAddress());
            System.out.println("Телефон: " + shop.getPhone());
            System.out.println("Дата открытия: " + shop.getOpeningDate());
            System.out.println("Менеджер: " + (manager != null ? 
                manager.getFirstName() + " " + manager.getLastName() + 
                " (ID: " + manager.getEmployeeId() + ")" : "не назначен"));
            
            System.out.println("\nСОТРУДНИКИ:");
            if (employees != null && !employees.isEmpty()) {
                System.out.println("Всего сотрудников: " + employees.size());
                for (Employee emp : employees) {
                    System.out.println("  - " + emp.getFirstName() + " " + emp.getLastName() + 
                        " (" + emp.getPosition() + ")");
                }
            } else {
                System.out.println("Сотрудники не найдены.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при выводе детальной информации: " + e.getMessage());
        }
    }
    
    // 19. Получить список кофеен, открытых в определенный год
    public List<CoffeeShop> getCoffeeShopsOpenedInYear(int year) {
        try {
            List<CoffeeShop> shops = coffeeShopDAO.getAllCoffeeShops();
            List<CoffeeShop> filteredShops = new java.util.ArrayList<>();
            
            for (CoffeeShop shop : shops) {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(shop.getOpeningDate());
                int openingYear = cal.get(java.util.Calendar.YEAR);
                
                if (openingYear == year) {
                    filteredShops.add(shop);
                }
            }
            
            return filteredShops;
        } catch (Exception e) {
            System.err.println("Ошибка при поиске кофеен по году открытия: " + e.getMessage());
            return null;
        }
    }
    
    // 20. Проверить, есть ли вакансия менеджера в кофейне
    public boolean hasManagerVacancy(int shopId) {
        try {
            CoffeeShop shop = getCoffeeShopById(shopId);
            return shop != null && shop.getManagerId() == null;
        } catch (Exception e) {
            System.err.println("Ошибка при проверке вакансии менеджера: " + e.getMessage());
            return false;
        }
    }
}
