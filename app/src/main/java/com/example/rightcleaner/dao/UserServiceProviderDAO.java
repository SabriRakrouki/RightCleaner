package com.example.rightcleaner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rightcleaner.entity.UserServiceProvider;

import java.util.List;

@Dao
public interface UserServiceProviderDAO {
    @Query("SELECT * FROM users")
    List<UserServiceProvider> getAll();
    @Insert
    void register(UserServiceProvider userServiceProvider);
    @Update
    void updateUserServiceProvider(UserServiceProvider userServiceProvider);
    @Delete
    void deleteUserServiceProvider(UserServiceProvider userServiceProvider);

}
