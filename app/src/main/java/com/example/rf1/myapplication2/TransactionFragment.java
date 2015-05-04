package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TransactionFragment extends Fragment {
    private ListView listView;
    private TransactionAdapter transactionAdapter;
    List<Transactions> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_transactions, container, false);

        List<Transactions> adapterData = getDataList();
        transactionAdapter = new TransactionAdapter(getActivity(), adapterData);

        listView = (ListView) inflate.findViewById(R.id.list_view_id);
        listView.setAdapter(transactionAdapter);


        return inflate;
        //  return super.onCreateView(inflater, container, savedInstanceState);
    }

    private List<Transactions> getDataList() {
        DateFormat df = new SimpleDateFormat("yyyy-MMM-dd", new Locale("ru"));
        Date now_calendar = Calendar.getInstance().getTime();
        String now = df.format(now_calendar);
        data.add(new Transactions("Huawei", "9800", now));
        data.add(new Transactions("Samsung", "13000", now));
        data.add(new Transactions("T-shirt", "300", now));
        data.add(new Transactions("Jeans", "1500", now));
        data.add(new Transactions("Printer", "4500", now));
        data.add(new Transactions("Bigmac", "400", now));
        return data;
    }
}
