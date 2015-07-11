package com.example.rf1.myapplication2.rest;

import android.text.TextUtils;

/**
 * Created by 1 on 29.05.2015.
 */
public class Result {
    String status;
    int id;
    public boolean isSuccessfull() {
        return TextUtils.equals(status, "success");
    }
}
