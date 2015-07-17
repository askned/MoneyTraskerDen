package com.example.rf1.myapplication2;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rf1.myapplication2.auth.SessionManager;
import com.example.rf1.myapplication2.rest.AuthResult;
import com.example.rf1.myapplication2.rest.RestClient;
import com.example.rf1.myapplication2.rest.Result;

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

    @Click
    void registrationbutton() {
        registration();
    }

    @Background
    void login() {

        final String loginName = this.logintx.getText().toString();
        final AuthResult loginResult = api.login(loginName, passtx.getText().toString());
        handleLoginResult(loginName, loginResult);
    }

    @Background
    void registration() {
        final String loginName = this.logintx.getText().toString();
        final Result registration = api.registration(loginName, passtx.getText().toString());
        handleregistration(registration);
    }


    @UiThread
    void handleLoginResult(String loginName, AuthResult loginResult) {
        if (loginResult.isSuccessfull()) {
            sessionManager.createAccount(loginName, loginResult.authToken);
            setAccountAuthenticatorResult(new Bundle());
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.loginisok), Toast.LENGTH_SHORT);
            toast.show();
            finish();
        } else
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();
    }
    @UiThread
    void handleregistration(Result registration) {
        if (registration.isSuccessfull()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.registratok), Toast.LENGTH_SHORT);
            toast.show();
            finish();
        } else
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();
    }


}
