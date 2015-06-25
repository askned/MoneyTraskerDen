package com.example.rf1.myapplication2.rest;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;

@Rest(rootUrl = "http://62.109.17.114/", converters = MessageConverter.class, interceptors = AuthenticatorInterceptor.class)
public interface RestClient {

    @Get("/auth?login={login}&password={password}")
    AuthResult login(CharSequence login, CharSequence password);

    @Get("/transactions")
    TransactionsResult getTransactions();


    @Post("/transactions/add?sum={sum}&comment={title}&tr_date={date}&category_id=1")
    Result addTransactions(int sum, String title, String date);


    @Post("/categories/add?title={title}")
    Result addCategory(String title);


    @Post("/balance?set={balance}")
    Result addBalance(int balance);
}
