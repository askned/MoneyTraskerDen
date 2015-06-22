package com.example.rf1.myapplication2;

import android.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.statistics)
public class StatmentFragment extends Fragment {

    float[] datapoints = {
            50, 60, 456, 345, 120
    };

    @ViewById(R.id.piechart)
    PieChartView pieChartView;


    @AfterViews
    void ready() {
        pieChartView.setDatapoints(datapoints);
    }

    }


