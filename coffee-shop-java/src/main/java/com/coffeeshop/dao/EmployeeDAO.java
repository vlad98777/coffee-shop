package com.coffeeshop.dao;

import com.coffeeshop.models.Employee;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // 1. Добавление нового сотрудника
    public int addEmployee(Employee employee) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO employees (employee_id, first_name, last_name, position, " +
                    "hire_date, salary, shop_id, email) " +
                    "VALUES (seq_employees.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"employee_id"})) {
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDate(4, new Date(employee.getHireDate().getTime()));
            pstmt.setDouble(5, employee.getSalary());
            pstmt.setInt(6, employee.getShopId());
            pstmt.setString(7, employee.getEmail());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Удаление сотрудника
    public boolean deleteEmployee(int employeeId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 3. Получение всех сотрудников
    public List<Employee> getAllEmployees() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Employee> employees = new ArrayList<>();
        
        String sql = "SELECT * FROM employees ORDER BY last_name, first_name";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPosition(rs.getString("position"));
                employee.setHireDate(rs.getDate("hire_date"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setShopId(rs.getInt("shop_id"));
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
            }
        }
        
        return employees;
    }
    
    // 4. Обновление сотрудника
    public boolean updateEmployee(Employee employee) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, position = ?, " +
                    "hire_date = ?, salary = ?, shop_id = ?, email = ? WHERE employee_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDate(4, new Date(employee.getHireDate().getTime()));
            pstmt.setDouble(5, employee.getSalary());
            pstmt.setInt(6, employee.getShopId());
            pstmt.setString(7, employee.getEmail());
            pstmt.setInt(8, employee.getEmployeeId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
}