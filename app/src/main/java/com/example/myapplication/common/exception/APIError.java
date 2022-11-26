package com.example.myapplication.common.exception;

import org.json.JSONObject;

import java.util.Map;

public class APIError {
    private int status;
    private String message;
    private String error;
    private String defaultMessage;
    public APIError() {
    }

    public int status() {
        return status;
    }

    public String message() {
        return message;
    }

    public String error(){
        return error;
    }

    public String defaultMessage(){return defaultMessage;}
}
