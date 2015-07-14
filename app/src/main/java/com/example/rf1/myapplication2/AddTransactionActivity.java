package com.example.rf1.myapplication2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;


@EActivity(R.layout.addtrans)
public class AddTransactionActivity extends ActionBarActivity {
    
      public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";


    @ViewById
    Toolbar toolbar;

    @ViewById
    MaterialSpinner spinner;

    @ViewById
    EditText sum, title;

    @StringArrayRes(R.array.category)
    String values[];
    
     private ArrayAdapter<String> adapter;

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.add_transactions));

        String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //    setHintFromLastTransaction();

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((DatePickerDialog.OnDateSetListener) this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance((TimePickerDialog.OnTimeSetListener) this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);

        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(isVibrate());
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        findViewById(R.id.timeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.setVibrate(isVibrate());
                timePickerDialog.setCloseOnSingleTapMinute(isCloseOnSingleTapMinute());
                timePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });
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
       // Intent intent = new Intent(this, MainActivity_.class);
       // startActivity(intent);
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




  //String[] ITEMS = getResources().getStringArray(R.array.countries_list);
  //  spinner = (MaterialSpinner) findViewById(R.id.spinner);
   
//    if (savedInstanceState != null) {
//            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
//            if (dpd != null) {
//                dpd.setOnDateSetListener(this);
//            }
//
//            TimePickerDialog tpd = (TimePickerDialog) getSupportFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
//            if (tpd != null) {
//                tpd.setOnTimeSetListener(this);
//            }
//        }
//    }

    private boolean isVibrate() {
        return ((CheckBox) findViewById(R.id.checkBoxVibrate)).isChecked();
    }

    private boolean isCloseOnSingleTapDay() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapDay)).isChecked();
    }

    private boolean isCloseOnSingleTapMinute() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapMinute)).isChecked();
    }

    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(AddTransactionActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
    }


    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        Toast.makeText(AddTransactionActivity.this, "new time:" + hourOfDay + "-" + minute, Toast.LENGTH_LONG).show();
    }

}





