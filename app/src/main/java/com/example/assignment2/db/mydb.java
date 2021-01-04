package com.example.assignment2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment2.model.Article;
import com.example.assignment2.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Article.class},version = 1,exportSchema = false)
public abstract class mydb extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract APIDao apiDao();
    private static mydb INSTANCE;

    public static mydb getInstance(Context context) {
        synchronized (mydb.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        mydb.class, "APP_DB")
                        .build();
            }
        }        return INSTANCE;
    }
}
