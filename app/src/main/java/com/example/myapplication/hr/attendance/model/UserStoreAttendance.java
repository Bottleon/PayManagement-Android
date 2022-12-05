package com.example.myapplication.hr.attendance.model;

import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserStoreAttendance implements Serializable {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("userStore")
    @Expose
    private UserStore userStore;
    @SerializedName("attendance")
    @Expose
    private Attendance attendance;
    @SerializedName("workStartTime")
    @Expose
    private String workStartTime;

    @SerializedName("workFinishTime")
    @Expose
    private String workFinishTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public String getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(String workStartTime) {
        this.workStartTime = workStartTime;
    }

    public String getWorkFinishTime() {
        return workFinishTime;
    }

    public void setWorkFinishTime(String workFinishTime) {
        this.workFinishTime = workFinishTime;
    }
}
