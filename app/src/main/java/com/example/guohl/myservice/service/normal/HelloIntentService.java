package com.example.guohl.myservice.service.normal;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.guohl.myservice.Utils;

/**
 * Created by guohl on 2016/8/10.
 *
 * IntentService： 用于顺序处理耗时task
 */
public class HelloIntentService extends IntentService {

    private static final String TAG = HelloIntentService.class.getSimpleName();
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public HelloIntentService() {
        super("HelloIntentService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: " + intent.getStringExtra("from"));
        Utils.printThreadInfo();
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        long endTime = System.currentTimeMillis() + 5*1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        Utils.printThreadInfo();
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart. startId:" + startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}