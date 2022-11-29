package com.example.myapplication.hr.user.model;

import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.Pattern;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("authType")
    @Expose
    private String authType;


    @SerializedName("profileName")
    @Expose
    private String profileName;

    private List<UserStore> stores = new ArrayList<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<UserStore> getStores() {
        return stores;
    }

    public void setStores(List<UserStore> stores) {
        this.stores = stores;
    }

}
