package com.shevchenkodev.rf1.myapplication2;

import android.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.razrabotka)
public class RazrabotkaFragment extends Fragment {

    @ViewById(R.id.textRazrabotka)
    TextView textView;


    @AfterViews
    void ready() {

    }

}
