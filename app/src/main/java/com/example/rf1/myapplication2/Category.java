package com.example.rf1.myapplication2;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "DBCategory")
public class Category extends Model {


    @Column(name = "categoryname")
    public String categoryname;


    public Category() {    }

    public Category(String categoryname) {
        this.categoryname = categoryname;
      //  this.catid = catid;
    }

    public static List<Category> getAll() {
        return new Select()
                .from(Category.class)
                .orderBy("date DESC")
                .execute();

    }


}