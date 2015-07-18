package com.example.rf1.myapplication2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@EActivity(R.layout.addtrans)
public class AddTransactionActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener {


    @ViewById
    Toolbar toolbar;

    @ViewById
    TextView enterdata;

    @ViewById
    EditText sum, title;

    @StringArrayRes(R.array.category)
    String values[];

    String oldstring;
    Date trandate;


    @ViewById
    Spinner spinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtrans);


    }


    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.add_transactions));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<Category> catall = Category.getAll();


        CustomAdapter adapter = new CustomAdapter(this,
                android.R.layout.simple_spinner_item, catall);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);


    }
            @Click
            void addTransaction() {
                if (title.getText().length() != 0 && sum.getText().length() != 0 && enterdata.getText().length() != 12) {
                    Integer checkcategory = spinner.getSelectedItemPosition();
                    new Transaction(title.getText().toString(), Integer.valueOf(sum.getText().toString()), trandate, 12).save();
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

    private void setHintFromLastTransaction() {
        Transaction lastTransaction = getLastTransaction();
        if (lastTransaction != null) {
            title.setHint(lastTransaction.getName());
            sum.setHint(String.valueOf(lastTransaction.getSum()));
        }
    }

    private Transaction getLastTransaction() {
        return new Select().from(Transaction.class).orderBy("CreateDate Desc").executeSingle();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //do some stuff for example write on log and update TextField on activity
        Log.w("DatePicker", "Date = " + year);
        enterdata.setText(new StringBuilder().append(day)
                .append("-").append(month + 1).append("-").append(year)
                .append(" "));
        oldstring = enterdata.getText().toString();
        try {
            trandate = new SimpleDateFormat("dd-MM-yyyy").parse(oldstring);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.w("trandate", "Date = " + trandate);
        }
    }

}





