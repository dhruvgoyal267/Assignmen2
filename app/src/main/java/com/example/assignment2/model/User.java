package com.example.assignment2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Email")
    private final String email;

    @ColumnInfo(name = "FULL_NAME")
    private final String fullName;

    @ColumnInfo(name = "Password")
    private final String password;

    @ColumnInfo(name = "Phone_Number")
    private final String phoneNumber;

    public User(@NotNull String email, String fullName, String password, String phoneNumber) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}