package com.coffeeshop.service;

import com.coffeeshop.dao.*;
import com.coffeeshop.models.*;
import java.time.LocalTime;
import java.util.List;

public class ScheduleService {
    private ScheduleDAO scheduleDAO = new ScheduleDAO();
    
    // 1. Добавление расписания на ближайший понедельник
    public int addScheduleForNextMonday(Schedule schedule) {
        try {
            return scheduleDAO.addScheduleForNextMonday(schedule);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении расписания: " + e.getMessage());
            return -1;
        }
    }
    
    // 2. Изменение расписания на вторник
    public boolean updateTuesdaySchedule(int shopId, LocalTime newOpening, LocalTime newClosing) {
        try {
            return scheduleDAO.updateTuesdaySchedule(shopId, newOpening, newClosing);
        } catch (Exception e) {
            System.err.println("Ошибка при изменении расписания: " + e.getMessage());
            return false;
        }
    }
    
    // 3. Удаление расписания на день
    public boolean deleteScheduleForDay(int shopId, String dayOfWeek) {
        try {
            return scheduleDAO.deleteScheduleForDay(shopId, dayOfWeek);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении расписания: " + e.getMessage());
            return false;
        }
    }
    
    // 4. Удаление расписания между датами
    public int deleteScheduleBetweenDates(int shopId, String startDate, String endDate) {
        try {
            return scheduleDAO.deleteScheduleBetweenDates(shopId, startDate, endDate);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении расписания: " + e.getMessage());
            return 0;
        }
    }
    
    // 5. Получение расписания на день
    public List<Schedule> getScheduleForDay(int shopId, String dateStr) {
        try {
            return scheduleDAO.getScheduleForDay(shopId, dateStr);
        } catch (Exception e) {
            System.err.println("Ошибка при получении расписания: " + e.getMessage());
            return null;
        }
    }
    
    // 6. Добавление расписания
    public int addSchedule(Schedule schedule) {
        try {
            return scheduleDAO.addSchedule(schedule);
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении расписания: " + e.getMessage());
            return -1;
        }
    }
    
    // 7. Удаление расписания по ID
    public boolean deleteSchedule(int scheduleId) {
        try {
            return scheduleDAO.deleteSchedule(scheduleId);
        } catch (Exception e) {
            System.err.println("Ошибка при удалении расписания: " + e.getMessage());
            return false;
        }
    }
    
    // 8. Обновление расписания
    public boolean updateSchedule(Schedule schedule) {
        try {
            return scheduleDAO.updateSchedule(schedule);
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении расписания: " + e.getMessage());
            return false;
        }
    }
}