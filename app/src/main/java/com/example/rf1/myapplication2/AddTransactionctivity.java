package com.example.rf1.myapplication2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by 1 on 23.05.2015.
 */
@EActivity(R.layout.addtrans)
public class AddTransactionctivity extends ActionBarActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    TextView sum, title;

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.add_transactions));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}





