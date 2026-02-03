package com.coffeeshop.models;

import java.util.Date;

public class CoffeeShop {
    private int shopId;
    private String shopName;
    private String address;
    private String phone;
    private Date openingDate;
    private Integer managerId;
    
    public CoffeeShop() {}
    
    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }
    
    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Date getOpeningDate() { return openingDate; }
    public void setOpeningDate(Date openingDate) { this.openingDate = openingDate; }
    
    public Integer getManagerId() { return managerId; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }
    
    @Override
    public String toString() {
        return String.format("%s - %s, тел: %s", shopName, address, phone);
    }
}
