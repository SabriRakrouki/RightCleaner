package com.example.rightcleaner.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;

import com.example.rightcleaner.dao.MeetDAO;
import com.example.rightcleaner.dao.ReviewDAO;
import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.entity.Meet;
import com.example.rightcleaner.entity.Review;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;


@Database(entities = {User.class, UserServiceProvider.class, Review.class, Meet.class},version = 2, exportSchema = false)

public abstract class RightCleanerDataBase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract MeetDAO meetDAO();
    public abstract ReviewDAO reviewDAO();
    public abstract UserServiceProviderDAO userServiceProviderDAO();
    private static final String dbName="RightCleaner";
    private static RightCleanerDataBase rightCleanerDataBase;
    public static synchronized RightCleanerDataBase getRightCleanerDataBase(Context context){
        if(rightCleanerDataBase==null){

            rightCleanerDataBase= Room.databaseBuilder(context,RightCleanerDataBase.class,dbName)

                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return rightCleanerDataBase;
    }
}