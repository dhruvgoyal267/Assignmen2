package com.example.assignment2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("articles")
    public List<Article> articles;
}
