package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

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

        FloatingActionButton fab = (FloatingActionButton) inflate.findViewById(R.id.fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(transactionAdapter);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTransactionctivity_.class);
                getActivity().startActivity(intent);
            }
        });

        return inflate;
    }

    private List<Transaction> getDataList() {
        return new Select()
                .from(Transaction.class)
    //              .where("Category = ?", category.getId())
    //            .orderBy("Name ASC")
                .execute();
    }
}
