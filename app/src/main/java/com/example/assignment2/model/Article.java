package com.example.assignment2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Articles")
public class Article {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("author")
    @ColumnInfo(name = "Author")
    private String author;

    @SerializedName("title")
    @ColumnInfo(name = "Title")
    private String title;

    @SerializedName("description")
    @ColumnInfo(name = "Description")
    private String description;

    @SerializedName("publishedAt")
    @ColumnInfo(name = "Published_At")
    private String published_date;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublished_date() {
        return published_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
}
