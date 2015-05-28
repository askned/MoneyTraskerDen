package com.example.rf1.myapplication2.rest;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://62.109.17.114/", converters = GsonHttpMessageConverter.class)
public interface RestClient {

    @Get("/auth?login={login}&password={password}")
    AuthResult login(CharSequence login, CharSequence password);

}
