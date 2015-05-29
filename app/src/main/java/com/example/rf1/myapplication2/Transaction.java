package com.example.rf1.myapplication2;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;


@Table(name = "DBTransaction")
public class Transaction extends Model {
    @Column(name = "comment")
    public String comment;
    @Column(name = "sum")
    public String sum;
    @Column(name = "date")
    public Date date;

    public Transaction() {
    }

    public Transaction(String comment, String sum) {
        this.comment = comment;
        //   this.sum = Integer.valueOf(sum);
        this.sum = sum;
        date = new Date();
    }

}

