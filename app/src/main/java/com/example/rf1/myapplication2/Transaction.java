package com.example.rf1.myapplication2;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;


@Table(name = "DBTransaction")
public class Transaction extends Model {
    private static final int ID_UNSYNCED = 0;
    private static final int ID_SYNCED = -1;

    @Column(name = "title")
    public String comment;
    @Column(name = "sum")
    public Integer sum;
    @Column(name = "date")
    public Date tr_date;
    @Column(name = "uuid")
    private int id;
    @Column(name = "trcategory")
    public int trcategory;



    public Transaction() {
    }


    public Transaction(String title, Integer sum, Date dateenter) {
        this.comment = title;
        this.sum = sum;
        tr_date = dateenter;
    }
    public Transaction(String title, Integer sum, Date dateenter, Integer trcategory) {
        this.comment = title;
        this.sum = sum;
        this.tr_date = dateenter;
        this.trcategory = trcategory;
    }
public void markSynced() {
        id = ID_SYNCED;
       save();
    }

    public boolean isInDatabase() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", id)

                .executeSingle() != null;
    }

    public static List<Transaction> getAll() {
        return new Select()
                .from(Transaction.class)

                .orderBy("date DESC")
                .execute();

    }

    public static List<Transaction> getUnsynced() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", ID_UNSYNCED)

                .execute();
    }

    public static List<Transaction> getSynced() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", ID_SYNCED)
                .orderBy("date DESC")
                .execute();
    }


    public String getName() {
        return comment;
    }

    public Integer getSum() {
        return sum;
    }

    public Date getDate() {
        return tr_date;
    }
}
