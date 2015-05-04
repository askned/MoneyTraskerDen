package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1 on 04.05.2015.
 */
public class StatmentFragment extends Fragment {

    //   private TextView textStat;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.statistics, container, false);
        return inflate;
    }

    //  public TextView getTextStat() {
    //      return textStat;
    //  }
}