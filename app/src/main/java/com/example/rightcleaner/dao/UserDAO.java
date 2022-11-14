package com.example.rightcleaner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rightcleaner.entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    List<User> getAll();
    @Query("SELECT u.* FROM users as  u WHERE id=:userId")
    User getUserId(int userId);

    @Insert
    void register(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);


}
