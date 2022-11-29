package com.example.myapplication.hr.store.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseStoreList {
    private List<Store> stores = new ArrayList<>();

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
