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
    public String title;
    @Column(name = "sum")
    public Integer sum;
    @Column(name = "date")
    public Date date;
    @Column(name = "uuid")
    private int id;

    public Transaction() {
    }

    public Transaction(String title, Integer sum) {
        this.title = title;
        this.sum = sum;
        date = new Date();
    }
public void markSynced() {
        id = ID_SYNCED;
       save();
    }

    public boolean isInDatabase() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", id)
                        //      .orderBy("date DESC")
                .executeSingle() != null;
    }

    public static List<Transaction> getAll() {
        return new Select()
                .from(Transaction.class)

                .orderBy("date DESC")
                .execute();
//        if (!TextUtils.isEmpty(filter))
//            from.where("title LIKE ?", "%" + filter + "%");

    }

    public static List<Transaction> getUnsynced() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", ID_UNSYNCED)
                        //     .orderBy("date DESC")
                .execute();
    }

    public static List<Transaction> getSynced() {
        return new Select()
                .from(Transaction.class)
                .where("uuid = ?", ID_SYNCED)
                .orderBy("date DESC")
                .execute();
    }
}
