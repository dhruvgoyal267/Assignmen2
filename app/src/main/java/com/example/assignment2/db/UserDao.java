package com.example.assignment2.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.assignment2.model.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM Users WHERE Users.Email = :email AND " +
            "Users.Password = :password LIMIT 1")
    User getUser(String email, String password);
}
