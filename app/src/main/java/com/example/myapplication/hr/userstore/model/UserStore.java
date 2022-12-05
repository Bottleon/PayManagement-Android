package com.example.myapplication.hr.userstore.model;

import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserStore implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("store")
    @Expose
    private Store store;

    @SerializedName("acceptStatue")
    @Expose
    private boolean acceptStatus;

    @SerializedName("hourlyWage")
    @Expose
    private String hourlyWage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean isAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(String hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
