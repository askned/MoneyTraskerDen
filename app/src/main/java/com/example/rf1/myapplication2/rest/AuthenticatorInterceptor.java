package com.example.rf1.myapplication2.rest;

import com.example.rf1.myapplication2.auth.SessionManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@EBean
public class AuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    private static final String LOG_TAG = AuthenticatorInterceptor.class.getSimpleName();
    public static String authToken;

    @Bean
    SessionManager sessionManager;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("authToken", sessionManager.getAuthToken());
        return execution.execute(request, body);
    }
}