package com.example.rf1.myapplication2;



/**

 */

public class Transaction {
    public String title;
    public int sum;
    public String date;


    public Transaction(String title, String sum, String date) {
        this.title = title;
        this.sum = Integer.valueOf(sum);
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }
}