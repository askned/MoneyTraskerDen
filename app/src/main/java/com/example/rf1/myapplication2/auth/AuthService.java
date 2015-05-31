package com.example.rf1.myapplication2.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class AuthService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Authonticator(this).getIBinder();
    }
}
