package com.example.rightcleaner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.dao.UserServiceProviderDAO;
import com.example.rightcleaner.entity.User;
import com.example.rightcleaner.entity.UserServiceProvider;

@Database(entities = {User.class, UserServiceProvider.class},version = 1)
public abstract class RightCleanerDataBase extends RoomDatabase {
    private static final String dbName="RightCleaner";
    private static RightCleanerDataBase rightCleanerDataBase;
    public static synchronized RightCleanerDataBase getRightCleanerDataBase(Context context){
        if(rightCleanerDataBase==null){
            rightCleanerDataBase= Room.databaseBuilder(context,RightCleanerDataBase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return rightCleanerDataBase;
    }
    public abstract UserDAO userDAO();
    public abstract UserServiceProviderDAO userServiceProviderDAO();

}
