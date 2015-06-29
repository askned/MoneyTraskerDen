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


public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public SyncAdapter(Context context) {
        super(context, true);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        try {
            final RestClient api = new RestClient_(getContext());
            for (Transaction transaction : Transaction.getUnsynced()) {
                api.addTransactions(transaction.sum, transaction.title, transaction.date.toString());
                transaction.markSynced();
            }

            for (Transaction transaction : api.getTransactions().data)
                if (!transaction.isInDatabase())
                    transaction.save();

            for (Transaction transaction : Transaction.getSynced())
                transaction.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}