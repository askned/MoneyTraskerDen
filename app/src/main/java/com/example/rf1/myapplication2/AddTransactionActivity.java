package com.example.rf1.myapplication2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.addtrans)
public class AddTransactionActivity extends ActionBarActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    EditText sum, title;

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.add_transactions));

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Click
    void addTransaction() {
        if (title.getText().length() != 0 && sum.getText().length() != 0) {
            new Transaction(title.getText().toString(), Integer.valueOf(sum.getText().toString())).save();
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.tosterror), Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    @OptionsItem
    void homeSelected() {
        onBackPressed();
    }
}





