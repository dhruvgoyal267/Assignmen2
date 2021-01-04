package com.example.assignment2.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment2.model.Article;

import java.util.List;

@Dao
public interface APIDao {

    @Insert
    void insertArticles(List<Article> articles);

    @Query("SELECT * FROM Articles")
    List<Article> getArticles();
}
