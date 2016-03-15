package com.example.rf1.myapplication2;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rf1.myapplication2.auth.SessionManager;
import com.example.rf1.myapplication2.rest.AuthResult;
import com.example.rf1.myapplication2.rest.RestClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.login)
public class LoginActivity extends AccountAuthenticatorActivity {

    @RestService
    RestClient api;

    @Bean
    SessionManager sessionManager;

    @ViewById
    TextView logintx, passtx;

    @Click
    void button_login() {
        login();
    }

    @Background
    void login() {
        
        final String loginName = this.logintx.getText().toString();
        final AuthResult loginResult = api.login(loginName, passtx.getText().toString());
        handleLoginResult(loginName, loginResult);
    }

    @UiThread
    void handleLoginResult(String loginName, AuthResult loginResult) {
        if (loginResult.isSuccessfull()) {
            sessionManager.createAccount(loginName, loginResult.authToken);
            setAccountAuthenticatorResult(new Bundle());
            finish();
        } else
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();
    }
}
