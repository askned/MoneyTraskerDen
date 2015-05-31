package com.example.rf1.myapplication2;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;


@Table(name = "DBTransaction")
public class Transaction extends Model {
    @Column(name = "title")
    public String title;
    @Column(name = "sum")
    public String sum;
    @Column(name = "date")
    public Date date;

    public Transaction() {
    }

    public Transaction(String title, String sum) {
        this.title = title;
        this.sum = sum;
        date = new Date();
    }

}

