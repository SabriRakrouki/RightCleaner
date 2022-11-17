package com.example.rightcleaner.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rightcleaner.entity.Review;

import java.util.List;

public interface ReviewDAO {
    @Query("SELECT * FROM review")
    List<Review> getAll();
    @Query("SELECT r.* FROM review as  r WHERE r.emailServiceUser=:emailServiceUser")
    Review getReviewByEmailServiceUser(String emailServiceUser);
    @Insert
    void addReview(Review review);
    @Update
    void updateReview(Review review);
    @Delete
    void deleteReview(Review review);
}
