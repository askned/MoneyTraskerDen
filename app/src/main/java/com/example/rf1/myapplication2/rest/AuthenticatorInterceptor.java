package com.example.rf1.myapplication2.rest;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class AuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    private static final String LOG_TAG = AuthenticatorInterceptor.class.getSimpleName();
    public static String authToken;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("authToken", authToken);
        return execution.execute(request, body);
    }
}