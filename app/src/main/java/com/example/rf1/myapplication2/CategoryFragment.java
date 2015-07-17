package com.example.rf1.myapplication2;

import android.app.Dialog;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.categoris)
public class CategoryFragment extends Fragment {
  // public ArrayList<String> ar = new ArrayList<>();

    private CategoryAdapter adapter;
    private android.support.v7.view.ActionMode actionMode;
    private ActionModeCallback actionModeCallback = new ActionModeCallback();


    @ViewById(R.id.category_list)
    RecyclerView recyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout msSwipeRefreshLayout;



    @ViewById(R.id.fab)
    FloatingActionButton fab;

    //  @StringArrayRes(R.array.category)
    //  String values[];

    @Click(R.id.fab)
    void alert() {
        alertDialog();
    }

    @AfterViews()
    void ready() {

        new Category("Loft");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        fab.attachToRecyclerView(recyclerView);
        msSwipeRefreshLayout.setColorSchemeColors(R.color.green_refresh, R.color.orange, R.color.blue);
        msSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();

            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);








  //      ar.add(getString(R.string.dom));
 //       ar.add(getString(R.string.eda));
  //      ar.add(getString(R.string.juvotn));
  //      ar.add(getString(R.string.zdorov));
  //      ar.add(getString(R.string.odejda));
  //      ar.add(getString(R.string.teknika));
  //      ar.add(getString(R.string.svyaz));
 //       ar.add(getString(R.string.obrazovan));
    //    ar.add(getString(R.string.drygoe));


   //     fab.attachToRecyclerView(recyclerView);



    }






    private void alertDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_window);
        TextView textView = (TextView) dialog.findViewById(R.id.title);
        final EditText editText = (EditText) dialog.findViewById(R.id.edittext);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);

        textView.setText(getString(R.string.add_category));
        okButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Editable text = editText.getText();
                                            if (!TextUtils.isEmpty(text)) {
                                                new Category(text.toString()).save();
                                                //  category.add(text.toString());

                                                dialog.dismiss();
                                            } else {
                                                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                                        getString(R.string.tosterror), Toast.LENGTH_SHORT);
                                                toast.show();

                                            }
                                        }
                                    }

        );

            cancelButton.setOnClickListener(new View.OnClickListener()

                                            {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            }

            );
            dialog.getWindow().

            setBackgroundDrawableResource(android.R.color.transparent);

        dialog.show();
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

    private void loadData() {
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Category>>() {
            @Override
            public Loader<List<Category>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Category>> transactionsLoader = new AsyncTaskLoader<List<Category>>(getActivity()) {
                    @Override
                    public List<Category> loadInBackground() {
                        return Category.getAll();
                    }
                };
                transactionsLoader.forceLoad();
                return transactionsLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
                msSwipeRefreshLayout.setRefreshing(false);
                adapter = (new CategoryAdapter(data, getActivity(), new CategoryAdapter.CardViewHolder.ClickListener() {

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
            public void onLoaderReset(Loader<List<Category>> loader) {
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

    private void loadData(final String filter) {
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Category>>() {
            @Override
            public Loader<List<Category>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Category>> transactionsLoader = new AsyncTaskLoader<List<Category>>(getActivity()) {
                    @Override
                    public List<Category> loadInBackground() {
                        return Category.getAll();
                    }
                };
                transactionsLoader.forceLoad();
                return transactionsLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
                recyclerView.setAdapter(new CategoryAdapter(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Category>> loader) {
            }
        });
    }


}
