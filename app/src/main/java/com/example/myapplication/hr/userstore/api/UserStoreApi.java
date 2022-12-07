package com.example.myapplication.hr.userstore.api;

import com.example.myapplication.hr.userstore.model.UserStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UserStoreApi {
    @GET("user-store/one")
    Call<UserStore> getUserStore(@Header("Authorization") String accessToken,@Query("userId") String userId,@Query("storeId") String storeId);

    @GET("user-store/related-one")
    Call<UserStore> getRelatedUserStore(@Header("Authorization") String accessToken,@Query("storeId") String storeId);

    @GET("user-store/stores-related-user")
    Call<List<UserStore>> getStoresByUserId(@Header("Authorization") String accessToken,@Query("userId") String userId);
}
