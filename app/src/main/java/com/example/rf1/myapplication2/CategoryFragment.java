package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CategoryFragment extends Fragment {

    //   private TextView textStat;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.categoris, container, false);
        return inflate;
    }
}
