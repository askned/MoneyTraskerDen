package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@EFragment//(R.layout.fragment_transactions)
public class TransactionFragment extends Fragment {
    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;
    List<Transaction> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_transactions, container, false);
        List<Transaction> adapterData = getDataList();
        transactionAdapter = new TransactionAdapter(adapterData);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.transactions_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(transactionAdapter);
        return inflate;
    }

    private List<Transaction> getDataList() {
        DateFormat df = new SimpleDateFormat("yyyy-MMM-dd", new Locale("ru"));
        Date now_calendar = Calendar.getInstance().getTime();
        String now = df.format(now_calendar);
        data.add(new Transaction("Huawei", "9800", now));
        data.add(new Transaction("Samsung", "13000", now));
        data.add(new Transaction("T-shirt", "300", now));
        data.add(new Transaction("Jeans", "1500", now));
        data.add(new Transaction("Printer", "4500", now));
        data.add(new Transaction("Bigmac", "400", now));
        return data;
    }
}
