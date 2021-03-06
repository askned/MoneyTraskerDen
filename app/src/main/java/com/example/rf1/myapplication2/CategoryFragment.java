package com.example.rf1.myapplication2;

import android.app.Dialog;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

@EFragment(R.layout.categoris)
public class CategoryFragment extends Fragment {
    private ArrayList<String> ar = new ArrayList<>();

    @ViewById(R.id.card_listView)
    ListView listView;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @StringArrayRes(R.array.category)
    String values[];

    @Click(R.id.fab)
    void alert() {
        alertDialog();
    }

    @AfterViews()
    void ready() {
        String s1 = "Food";
        String s2 = "Entertaimnet";
        String s3 = "Other";
        ar.add(s1);
        ar.add(s2);
        ar.add(s3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ar);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fab.attachToListView(listView);
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
                    ar.add(text.toString());
                    dialog.dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }


}
