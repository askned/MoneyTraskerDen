package com.example.rf1.myapplication2.rest;

import android.text.TextUtils;


public class Result {
    String status;
    int id;
    public boolean isSuccessfull() {
        return TextUtils.equals(status, "success");
    }
}
