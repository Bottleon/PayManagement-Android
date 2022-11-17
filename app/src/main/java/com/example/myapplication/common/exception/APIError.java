package com.example.myapplication.common.exception;

public class APIError {
    private int status;
    private String message;
    private String error;
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
}
