package com.example.myapplication.hr.store.model;

import com.example.myapplication.hr.userstore.model.UserStore;

import org.intellij.lang.annotations.Pattern;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String id;
    private String name;
    private String phoneNumber;
    private String basicAddress;
    private String detailAddress;
    private String size;
    private String lateAllowTime;
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
