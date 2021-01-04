package com.example.assignment2.Network;


import com.example.assignment2.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Retrofit_Client {

    @GET("top-headlines?sources=techcrunch&apiKey=e0d8658aa05547eaa007678ee6706f25")
    Call<Response> getArticles();
}
