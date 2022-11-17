package com.example.rightcleaner.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
public class Review {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "review")
    String review;
    @ColumnInfo(name = "emailSimpleUser")
    String emailSimpleUser;
    @ColumnInfo(name = "emailServiceUser")
    String emailServiceUser;
}
