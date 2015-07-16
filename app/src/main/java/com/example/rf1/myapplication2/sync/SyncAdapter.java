package com.example.rf1.myapplication2.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.example.rf1.myapplication2.Transaction;
import com.example.rf1.myapplication2.rest.RestClient;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.text.SimpleDateFormat;
import java.util.List;


@EBean
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    public static final String SYNCED = "synced";
    public SyncAdapter(Context context) {
        super(context, true);
    }

    @RestService
    RestClient api;

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        try {
            final List<Transaction> unsyncedTransactions = Transaction.getUnsynced();
            for (Transaction transaction : unsyncedTransactions) {
                String newstring = new SimpleDateFormat("dd-MM-yyyy").format(transaction.tr_date);
                api.addTransactions(transaction.sum, transaction.comment, newstring);
                transaction.markSynced();
            }

            final Transaction[] serverTransactions = api.getTransactions().data;
            for (Transaction transaction : serverTransactions)
                if (!transaction.isInDatabase())
                    transaction.save();

            final List<Transaction> syncedTransactions = Transaction.getSynced();
            for (Transaction transaction : syncedTransactions)
                transaction.delete();
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(SYNCED));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
