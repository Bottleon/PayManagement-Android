package com.example.myapplication.hr.message.api;

import com.example.myapplication.hr.message.model.CertificationNumber;
import com.example.myapplication.hr.user.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageApi {
    @POST("sns/send-one/{id}")
    Call<CertificationNumber> getCertificationNumber(@Path("id") String id);
    @POST("sns/verification")
    Call<CertificationNumber> varifyNumber(@Body CertificationNumber certificationNumber);
}
