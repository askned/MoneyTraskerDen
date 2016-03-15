package com.example.rf1.myapplication2.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
              return SyncAdapter_.getInstance_(getApplicationContext()).getSyncAdapterBinder();
    }
}
