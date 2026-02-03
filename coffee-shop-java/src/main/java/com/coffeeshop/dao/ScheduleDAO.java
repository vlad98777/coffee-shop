package com.coffeeshop.dao;

import com.coffeeshop.models.Schedule;
import com.coffeeshop.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    
    // 1. Добавление информации о графике работы в ближайший понедельник
    public int addScheduleForNextMonday(Schedule schedule) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        // Находим ближайший понедельник
        LocalDate nextMonday = LocalDate.now();
        while (nextMonday.getDayOfWeek().getValue() != 1) {
            nextMonday = nextMonday.plusDays(1);
        }
        
        String sql = "INSERT INTO shop_schedules (schedule_id, shop_id, work_date, day_of_week, " +
                    "opening_time, closing_time, notes) " +
                    "VALUES (seq_schedules.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"schedule_id"})) {
            pstmt.setInt(1, schedule.getShopId());
            pstmt.setDate(2, Date.valueOf(nextMonday));
            pstmt.setString(3, "Понедельник");
            pstmt.setTimestamp(4, Timestamp.valueOf(nextMonday.atTime(schedule.getOpeningTime())));
            pstmt.setTimestamp(5, Timestamp.valueOf(nextMonday.atTime(schedule.getClosingTime())));
            pstmt.setString(6, schedule.getNotes());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 2. Изменение расписания на вторник
    public boolean updateTuesdaySchedule(int shopId, LocalTime newOpening, LocalTime newClosing) 
            throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        // Находим ближайший вторник
        LocalDate nextTuesday = LocalDate.now();
        while (nextTuesday.getDayOfWeek().getValue() != 2) {
            nextTuesday = nextTuesday.plusDays(1);
        }
        
        String sql = "UPDATE shop_schedules SET opening_time = ?, closing_time = ? " +
                    "WHERE shop_id = ? AND work_date = ? AND day_of_week = 'Вторник'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(nextTuesday.atTime(newOpening)));
            pstmt.setTimestamp(2, Timestamp.valueOf(nextTuesday.atTime(newClosing)));
            pstmt.setInt(3, shopId);
            pstmt.setDate(4, Date.valueOf(nextTuesday));
            
            int affected = pstmt.executeUpdate();
            
            // Если запись не найдена, создаем новую
            if (affected == 0) {
                Schedule schedule = new Schedule();
                schedule.setShopId(shopId);
                schedule.setWorkDate(nextTuesday);
                schedule.setDayOfWeek("Вторник");
                schedule.setOpeningTime(newOpening);
                schedule.setClosingTime(newClosing);
                schedule.setNotes("Обновленное расписание");
                addSchedule(schedule);
                return true;
            }
            
            return affected > 0;
        }
    }
    
    // 3. Удалить расписание работы в определённый день
    public boolean deleteScheduleForDay(int shopId, String dayOfWeek) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM shop_schedules WHERE shop_id = ? AND day_of_week = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setString(2, dayOfWeek);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 4. Удалить расписание на конкретный промежуток между датами
    public int deleteScheduleBetweenDates(int shopId, String startDate, String endDate) 
            throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        
        String sql = "DELETE FROM shop_schedules WHERE shop_id = ? " +
                    "AND work_date BETWEEN ? AND ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setDate(2, Date.valueOf(start));
            pstmt.setDate(3, Date.valueOf(end));
            
            return pstmt.executeUpdate();
        }
    }
    
    // 5. Показать расписание работы в конкретный день
    public List<Schedule> getScheduleForDay(int shopId, String dateStr) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        List<Schedule> schedules = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        String sql = "SELECT * FROM shop_schedules WHERE shop_id = ? AND work_date = ? " +
                    "ORDER BY opening_time";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shopId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setShopId(rs.getInt("shop_id"));
                schedule.setWorkDate(rs.getDate("work_date").toLocalDate());
                schedule.setDayOfWeek(rs.getString("day_of_week"));
               schedule.setOpeningTime(rs.getTime("opening_time").toLocalTime());
				schedule.setClosingTime(rs.getTime("closing_time").toLocalTime());		
                schedule.setNotes(rs.getString("notes"));
                schedules.add(schedule);
            }
        }
        
        return schedules;
    }
    
    // 6. Добавление строки расписания
    public int addSchedule(Schedule schedule) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "INSERT INTO shop_schedules (schedule_id, shop_id, work_date, day_of_week, " +
                    "opening_time, closing_time, notes) " +
                    "VALUES (seq_schedules.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                new String[]{"schedule_id"})) {
            pstmt.setInt(1, schedule.getShopId());
            pstmt.setDate(2, Date.valueOf(schedule.getWorkDate()));
            pstmt.setString(3, schedule.getDayOfWeek());
            pstmt.setTimestamp(4, Timestamp.valueOf(schedule.getWorkDate().atTime(schedule.getOpeningTime())));
            pstmt.setTimestamp(5, Timestamp.valueOf(schedule.getWorkDate().atTime(schedule.getClosingTime())));
            pstmt.setString(6, schedule.getNotes());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return -1;
    }
    
    // 7. Удаление строки расписания
    public boolean deleteSchedule(int scheduleId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "DELETE FROM shop_schedules WHERE schedule_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleId);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // 8. Обновление строки расписания
    public boolean updateSchedule(Schedule schedule) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql = "UPDATE shop_schedules SET shop_id = ?, work_date = ?, day_of_week = ?, " +
                    "opening_time = ?, closing_time = ?, notes = ? " +
                    "WHERE schedule_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, schedule.getShopId());
            pstmt.setDate(2, Date.valueOf(schedule.getWorkDate()));
            pstmt.setString(3, schedule.getDayOfWeek());
            pstmt.setTimestamp(4, Timestamp.valueOf(schedule.getWorkDate().atTime(schedule.getOpeningTime())));
            pstmt.setTimestamp(5, Timestamp.valueOf(schedule.getWorkDate().atTime(schedule.getClosingTime())));
            pstmt.setString(6, schedule.getNotes());
            pstmt.setInt(7, schedule.getScheduleId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
}