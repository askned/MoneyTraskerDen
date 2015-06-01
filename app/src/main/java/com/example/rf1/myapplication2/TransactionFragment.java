package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.activeandroid.query.Select;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_transactions)
public class TransactionFragment extends Fragment {

    @ViewById(R.id.transactions_list)
    RecyclerView recyclerView;

    //  TransactionAdapter transactionAdapter;

    @ViewById
    FloatingActionButton fab;

    @AfterViews
    void ready() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        fab.attachToRecyclerView(recyclerView);

    }

    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Transaction>>() {
            @Override
            public Loader<List<Transaction>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Transaction>> transactionsLoader = new AsyncTaskLoader<List<Transaction>>(getActivity()) {
                    @Override
                    public List<Transaction> loadInBackground() {
                        return getDataList();
                    }
                };
                transactionsLoader.forceLoad();
                return transactionsLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<Transaction>> loader, List<Transaction> data) {
                recyclerView.setAdapter(new TransactionAdapter(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Transaction>> loader) {
            }
        });
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        recyclerView.setAdapter(new TransactionAdapter(getDataList()));
//    }

    @Click
    void fabClicked() {
        AddTransactionActivity_.intent(getActivity()).start();
    }

    private List<Transaction> getDataList() {
        return new Select()
                .from(Transaction.class)
    //              .where("Category = ?", category.getId())
                .orderBy("date DESC")
                .execute();
    }
}
