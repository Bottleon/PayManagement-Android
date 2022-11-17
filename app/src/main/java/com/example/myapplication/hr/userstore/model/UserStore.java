package com.example.myapplication.hr.userstore.model;

import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.model.User;

public class UserStore {
    private User user;
    private Store store;
    private boolean acceptStatus;
    private String hourlyWage;

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
