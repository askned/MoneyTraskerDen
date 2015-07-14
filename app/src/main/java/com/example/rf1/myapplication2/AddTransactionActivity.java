package com.example.rf1.myapplication2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import fr.ganfra.materialspinner.MaterialSpinner;


@EActivity(R.layout.addtrans)
public class AddTransactionActivity extends ActionBarActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    MaterialSpinner spinner1;

    @ViewById
    EditText sum, title;

    @StringArrayRes(R.array.category)
    String values[];
    
     private ArrayAdapter<String> adapter;

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.add_transactions));


        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //    setHintFromLastTransaction();
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

 String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 spinner.setAdapter(adapter);

  
  //  spinner = (MaterialSpinner) findViewById(R.id.spinner);
   

}





