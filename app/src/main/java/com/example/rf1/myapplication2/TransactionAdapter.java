package com.example.rf1.myapplication2;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transactions> {
    List<Transactions> transactions;

    public TransactionAdapter(Context context, List<Transactions> transactions) {
        super(context, 0, transactions);
        this.transactions = transactions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Transactions transactions = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title_id);
        TextView sum = (TextView) convertView.findViewById(R.id.sum_id);
        TextView date = (TextView) convertView.findViewById(R.id.date_id);

        title.setText(transactions.getTitle());
        sum.setText(Integer.toString(transactions.getSum()));
        date.setText(transactions.getDate());
        String color = position % 2 == 0 ? "#FEFF91" : "#CBFDD2";
        convertView.setBackgroundColor(Color.parseColor(color));
        return convertView;
    }
}