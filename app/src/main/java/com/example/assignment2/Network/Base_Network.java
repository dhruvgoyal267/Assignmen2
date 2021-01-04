package com.example.assignment2.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Base_Network {
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        String BASE_URL = "https://newsapi.org/v2/";
        if(retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
