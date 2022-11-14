package com.example.rightcleaner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rightcleaner.dao.UserDAO;
import com.example.rightcleaner.entity.User;

@Database(entities = {User.class},exportSchema = true,version = 1)
public abstract class RightCleanerDataBase extends RoomDatabase {
    private static final String dbName="RightCleaner";
    private static RightCleanerDataBase rightCleanerDataBase;
    public static synchronized RightCleanerDataBase getRightCleanerDataBase(Context context){
        if(rightCleanerDataBase==null){
            rightCleanerDataBase= Room.databaseBuilder(context,RightCleanerDataBase.class,dbName)
                    .allowMainThreadQueries()
                    .build();
        }
        return rightCleanerDataBase;
    }
    public abstract UserDAO userDAO();

}
