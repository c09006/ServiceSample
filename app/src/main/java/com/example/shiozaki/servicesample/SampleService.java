package com.example.shiozaki.servicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by shiozaki on 2016/01/20.
 */
public class SampleService extends Service {
    static final String TAG = "MySampleService";

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.d(TAG, "onDestroy");
    }

    public class ServiceLocalBinder extends Binder{
        SampleService getService(){
            return SampleService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onRebind(Intent intent){
        Log.d(TAG, "onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.d(TAG, "onUnbind");
        return false;
    }
}
