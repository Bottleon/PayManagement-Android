package com.example.myapplication.hr.notice.api;

import com.example.myapplication.hr.notice.model.Notice;
import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NoticeApi {
    @POST("notice/save")
    Call<Notice> saveNotice(@Header("authorization") String accessToken, @Body Notice notice);

    @GET("notice/all")
    Call<List<Notice>> getNoticeAll(@Header ("authorization") String accessToken, @Query("userStoreId") Long userStoreId);

    @GET("notice/one")
    Call<Notice> getNotice(@Header ("authorization") String accessToken, @Query("noticeId") Long noticeId);
}
