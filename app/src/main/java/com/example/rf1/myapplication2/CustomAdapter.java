package com.example.rf1.myapplication2;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    List<Category> categories;


    private Context context;
    private int textViewResourceId;

    public static boolean flag = false;

    public CustomAdapter(Context context, int textViewResourceId, List<Category> categories) {
        super(context, textViewResourceId, categories);
        this.context = context;
        this.textViewResourceId = textViewResourceId;

        this.categories = categories;
        this.context = context;
    }

}