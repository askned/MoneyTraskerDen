package com.example.rf1.myapplication2;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.query.Select;
import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_transactions)
public class TransactionFragment extends Fragment {
    private TransactionAdapter adapter;
    private android.support.v7.view.ActionMode actionMode;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();

    @ViewById(R.id.transactions_list)
    RecyclerView recyclerView;

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
                adapter = (new TransactionAdapter(data, new TransactionAdapter.CardViewHolder.ClickListener() {

                    @Override
                    public void onItemClicked(int position) {
                        if (actionMode != null) {
                            togggleSelection(position);
                        }
                    }

                    @Override
                    public boolean onItemLongClicked(int position) {
                        if (actionMode == null) {
                            ActionBarActivity activity = (ActionBarActivity) getActivity();
                            actionMode = activity.startSupportActionMode(actionModeCallback);
                        }
                        togggleSelection(position);
                        return true;
                    }
                }));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onLoaderReset(Loader<List<Transaction>> loader) {
            }
        });
    }

    private void togggleSelection(int position) {
        adapter.togggleSelection(position);
        int count = adapter.getSelectedItemCount();
        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
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

    private class ActionModeCallback implements android.support.v7.view.ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contectual_action_bar, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_remove:
                    adapter.removeItems(adapter.getSelectedItems());
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {
            adapter.clearSelection();
            actionMode = null;
        }
    }
}
