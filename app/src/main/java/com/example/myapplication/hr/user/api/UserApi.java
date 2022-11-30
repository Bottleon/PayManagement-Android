package com.example.myapplication.hr.user.api;

import com.example.myapplication.common.token.TokenInfo;
import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    @GET("user/{id}")
    Call<User> getUser(@Path("id") String id);
    @GET("user/all")
    Call<User> getAllUsers();
    @GET("user/stores")
    Call<List<Store>> getStoresByUser(@Header ("authorization") String accessToken, @Query("userId") String id);
    @POST("user/save")
    Call<User> saveUser(@Body User user);
    @POST("user/login")
    Call<TokenInfo> login(@Body User user);
}
