package com.example.myapplication.common.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//http 통신 객체
public class RetrofitClient {
    private static final String BASE_URL = "http://220.89.79.187:8080";
    // 가상머신에서는 개인 서버에 접속할 때 ip주소를 10.0.2.2로 설정
    private static Retrofit retrofit;
    private static Gson gson;
    private RetrofitClient(){

    }
    public static Retrofit getInstance(){
        if(retrofit==null){
            if(gson==null){
                gson = new GsonBuilder().setLenient().create();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
