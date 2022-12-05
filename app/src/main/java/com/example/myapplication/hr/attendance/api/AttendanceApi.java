package com.example.myapplication.hr.attendance.api;

import com.example.myapplication.hr.attendance.model.UserStoreAttendance;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AttendanceApi {
    @POST("attendance/save")
    Call<UserStoreAttendance> saveUserAttendance(@Header ("authorization") String accessToken, @Body UserStoreAttendance userStoreAttendances);


    @POST("attendance/update")
    Call<UserStoreAttendance> updateUserAttendance(@Header ("authorization") String accessToken, @Body UserStoreAttendance userStoreAttendances);

}
