package com.example.guohl.myservice.service.normal;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.guohl.myservice.Utils;

/**
 * Created by guohl on 2016/8/10.
 *
 * 与HelloService2相同--HelloService2是官方demo
 */
public class HelloService extends Service {
    private static final String TAG = HelloService.class.getSimpleName();

    private Looper mLooper;
    private ServiceHandler mHandler;

    class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage");
            Utils.printThreadInfo();
//            super.handleMessage(msg);
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
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Utils.printThreadInfo();

        HandlerThread handlerThread = new HandlerThread("handlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
		mLooper = handlerThread.getLooper();

        mHandler = new ServiceHandler(mLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mHandler.obtainMessage();
        msg.arg1 = startId;
        mHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
//        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
