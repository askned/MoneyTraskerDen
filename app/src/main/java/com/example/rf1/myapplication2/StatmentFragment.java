package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.graphics.Color;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@EFragment(R.layout.statistics)
public class StatmentFragment extends Fragment {

    Map<String, Float> map = new HashMap<>();
    
     @ViewById(R.id.chart)
     PieChart chart;

    @AfterViews
    void ready() {
        map.put(getString(R.string.dom), 10.7f);
        map.put(getString(R.string.eda), 102.0f);
        map.put(getString(R.string.juvotn), 25.0f);
        map.put(getString(R.string.zdorov), 9.1f);
        Random r;
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();

            r = new Random();
            int color = Color.argb(100, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            chart.addPieSlice(new PieModel(key, value, color));
        }
        chart.startAnimation();
    }

}

