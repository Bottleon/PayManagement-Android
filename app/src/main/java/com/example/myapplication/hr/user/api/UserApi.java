package com.example.myapplication.hr.user.api;

import com.example.myapplication.hr.user.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("user/{id}")
    Call<User> getUser(@Path("id") String id);
    @GET("user/all")
    Call<User> getAllUsers();
    @POST("user/save")
    Call<User> createUser(@Body User user);
    @POST("user/login")
    Call<User> login(@Body User user);
}
