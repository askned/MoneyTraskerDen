package com.example.rf1.myapplication2.auth;


import android.accounts.Account;
import android.accounts.AccountManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

@EBean
public class SessionManager {
    private static final String AUTH_ACCOUNT_TYPE = "com.example.rf1.myapplication2";

    @SystemService
    AccountManager accountManager;

    public void createAccount(String login, String authIoken) {
        Account account = new Account(login, AUTH_ACCOUNT_TYPE);
        accountManager.addAccountExplicitly(account, null, null);
    }
}
