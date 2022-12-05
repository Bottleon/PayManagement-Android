package com.example.myapplication.hr.store.model;

import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.Pattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("basicAddress")
    @Expose
    private String basicAddress;
    @SerializedName("detailAddress")
    @Expose
    private String detailAddress;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("lateAllowTime")
    @Expose
    private String lateAllowTime;
    @SerializedName("breakTime")
    @Expose
    private String breakTime;

    private List<UserStore> users = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBasicAddress() {
        return basicAddress;
    }

    public void setBasicAddress(String basicAddress) {
        this.basicAddress = basicAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLateAllowTime() {
        return lateAllowTime;
    }

    public void setLateAllowTime(String lateAllowTime) {
        this.lateAllowTime = lateAllowTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public List<UserStore> getUsers() {
        return users;
    }

    public void setUsers(List<UserStore> users) {
        this.users = users;
    }
}
