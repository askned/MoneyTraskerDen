package com.example.rf1.myapplication2;


import android.text.Editable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "DBCategory")
public class Category extends Model {
//    private static final int ID_UNSYNCED = 0;
//    private static final int ID_SYNCED = -1;

    @Column(name = "categoryname")
    public String categoryname;
 //   @Column(name = "catid")
 //   private int catid;

    public Category() {    }

    public Category(String categoryname) {
        this.categoryname = categoryname;
      //  this.catid = catid;
    }

    public static List<Category> getAll() {
        return new Select()
                .from(Category.class)
             //   .where("categoryname  = ?")
             //   .orderBy("date DESC")
                .execute();
//        if (!TextUtils.isEmpty(filter))
//            from.where("title LIKE ?", "%" + filter + "%");

    }

    public void loadcategorii(){
         ArrayList<String> ar = new ArrayList<>();
        String s1 = "Food";
        String s2 = "Entertaimnet";
        String s3 = "Other";
        ar.add(s1);
        ar.add(s2);
        ar.add(s3);
    }

}