package com.coffeeshop.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private int scheduleId;
    private int shopId;
    private LocalDate workDate;
    private String dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String notes;
    
    public Schedule() {}
    
    public Schedule(int shopId, LocalDate workDate, String dayOfWeek, 
                   LocalTime openingTime, LocalTime closingTime) {
        this.shopId = shopId;
        this.workDate = workDate;
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    
    // Геттеры и сеттеры
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    
    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }
    
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
    
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    
    public LocalTime getOpeningTime() { return openingTime; }
    public void setOpeningTime(LocalTime openingTime) { this.openingTime = openingTime; }
    
    public LocalTime getClosingTime() { return closingTime; }
    public void setClosingTime(LocalTime closingTime) { this.closingTime = closingTime; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    @Override
    public String toString() {
        return String.format("Расписание [ID: %d, Дата: %s, День: %s, Время: %s - %s]",
                scheduleId, workDate, dayOfWeek, openingTime, closingTime);
    }
}