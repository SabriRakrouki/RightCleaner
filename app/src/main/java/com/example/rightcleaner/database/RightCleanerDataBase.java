package com.example.rightcleaner.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;

@Database(entities = {User.class, UserServiceProvider.class},version = 2, exportSchema = false)
public abstract class RightCleanerDataBase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract UserServiceProviderDAO userServiceProviderDAO();
    private static final String dbName="RightCleaner";
    private static RightCleanerDataBase rightCleanerDataBase;
    public static synchronized RightCleanerDataBase getRightCleanerDataBase(Context context){
        if(rightCleanerDataBase==null){
            rightCleanerDataBase= Room.databaseBuilder(context.getApplicationContext(),RightCleanerDataBase.class,dbName)
                    .allowMainThreadQueries()
                    .build();
        }
        return rightCleanerDataBase;
    }
}
