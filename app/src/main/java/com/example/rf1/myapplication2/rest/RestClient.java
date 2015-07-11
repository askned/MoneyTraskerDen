package com.example.rf1.myapplication2.rest;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;

@Rest(rootUrl = "http://62.109.17.114/", converters = MessageConverter.class, interceptors = AuthenticatorInterceptor.class)
public interface RestClient {

    @Get("/auth?login={login}&password={password}")
    AuthResult login(CharSequence login, CharSequence password);

    @Get("/auth?login={login}&password={password}&register=1")
    Result registration(CharSequence login, CharSequence password);

    @Get("/transactions")
    TransactionsResult getTransactions();


    @Post("/transactions/add?sum={sum}&comment={comment}&tr_date={tr_date}&category_id=1")
    Result addTransactions(Integer sum, String comment, String tr_date);


    @Post("/categories/add?title={title}")
    Result addCategory(String title);


    @Get("/categories")
    CategoryResult getCategory();


    @Post("/balance?set={balance}")
    Result addBalance(int balance);
}
