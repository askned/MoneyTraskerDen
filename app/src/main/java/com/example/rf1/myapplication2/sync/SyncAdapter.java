package com.example.rf1.myapplication2.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.example.rf1.myapplication2.Transaction;
import com.example.rf1.myapplication2.rest.RestClient;
import com.example.rf1.myapplication2.rest.RestClient_;


@EBean
public class SyncAdapter extends AbstractThreadedSyncAdapter {

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
                api.addTransaction(transaction.sum, transaction.comment, transaction.trDate.toString());
                transaction.markSynced();
            }

            final Transaction[] serverTransactions = api.getTransactions().data;
            for (Transaction transaction : serverTransactions)
                if (!transaction.isInDatabase())
                    transaction.save();

            final List<Transaction> syncedTransactions = Transaction.getSynced();
            for (Transaction transaction : syncedTransactions)
                transaction.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
