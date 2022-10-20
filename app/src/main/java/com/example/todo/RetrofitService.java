package com.example.todo;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit instance;

    public static Retrofit getService() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public <T> T getApi(@NonNull Class<T> apiClass){
        return getService().create(apiClass);
    }

}
