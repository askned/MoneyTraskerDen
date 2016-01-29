package com.shevchenkodev.rf1.myapplication2;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "DBCategory")
public class Category extends Model {



    @Column(name = "categoryname")
    public String categoryname;

    @Column(name = "catid")
    public Integer catid;

    private static int catid1 = 0;
    public Category() {    }

    public Category(String categoryname) {
        this.categoryname = categoryname;
        catid = catid1 + 1;
        catid1 = catid;
    }

    public static List<Category> getAll() {
        return new Select()
                .from(Category.class)
                .execute();

    }


}