package com.example.rf1.myapplication2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.CardViewHolder> {
    List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {

        this.transactions = transactions;
    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.name.setText(transaction.title);
        holder.sum.setText(transaction.sum);
        holder.date.setText(String.valueOf(transaction.date));

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView sum;
        protected TextView date;

        public CardViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            sum = (TextView) itemView.findViewById(R.id.sum);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}