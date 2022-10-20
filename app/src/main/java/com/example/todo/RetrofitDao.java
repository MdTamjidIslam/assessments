package com.example.todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitDao {

    @GET("users")
    Call<List<User>> getUsers();


    @GET("todos")
    Call<List<Todo>> getTodos();

}
