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
    @Query("SELECT * FROM UserServiceProvider")
    List<UserServiceProvider> getAll();
    @Query("SELECT * FROM USERSERVICEPROVIDER where service=:service")
    List<UserServiceProvider> getByService(String service);
    @Query("SELECT  * FROM USERSERVICEPROVIDER WHERE email=:email")
    UserServiceProvider getByEmail(String email);
    @Insert
    void register(UserServiceProvider userServiceProvider);
    @Update
    void updateUserServiceProvider(UserServiceProvider userServiceProvider);
    @Delete
    void deleteUserServiceProvider(UserServiceProvider userServiceProvider);

}
