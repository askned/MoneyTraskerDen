package com.example.rf1.myapplication2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

@SuppressWarnings("UnusedAssignment")
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
        String color = "#f9de11";
        if (position % 3 == 0)
            color = "#f9de11";
        else if (position % 2 == 0)
            color = "#ffd7a0";
        else
            color = "#caf8ff";

        convertView.setBackgroundColor(Color.parseColor(color));
        return convertView;
    }
}