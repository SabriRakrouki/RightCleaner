package com.example.rightcleaner.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class UserServiceProvider extends User{
    @ColumnInfo(name = "service")
    String service;
    @ColumnInfo(name = "price")
    Float price;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
