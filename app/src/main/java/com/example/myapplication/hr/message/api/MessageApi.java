package com.example.myapplication.hr.message.api;

import com.example.myapplication.hr.message.model.UserMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageApi {
    @POST("sns/send-one/{id}")
    Call<UserMessage> getCertificationNumber(@Path("id") String id);
    @POST("sns/verification")
    Call<UserMessage> varifyNumber(@Body UserMessage userMessage);
}
