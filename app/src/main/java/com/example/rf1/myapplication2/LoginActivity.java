package com.example.rf1.myapplication2;

import android.accounts.AccountAuthenticatorActivity;
import android.widget.EditText;

import com.example.rf1.myapplication2.auth.SessionManager;
import com.example.rf1.myapplication2.rest.AuthResult;
import com.example.rf1.myapplication2.rest.RestClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.login)
public class LoginActivity extends AccountAuthenticatorActivity {

    @RestService
    RestClient api;

    @Bean
    SessionManager sessionManager;

    @ViewById
    EditText logintx, passtx;

    @Click
    void button_login() {
        login();
    }

    @Background
    void login() {
        final AuthResult login = api.login(logintx.getText().toString(), passtx.getText().toString());
        sessionManager.createAccount(logintx.getText().toString(), login.authToken);
    }

}
