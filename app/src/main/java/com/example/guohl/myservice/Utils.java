package com.example.guohl.myservice;

import android.os.Process;
import android.util.Log;

/**
 * Created by guohl on 2016/8/10.
 */
public class Utils {
    public static void printThreadInfo() {
        Thread thread = Thread.currentThread();
        Log.e(Utils.class.getSimpleName(), "threadId:" + thread.getId() + " priority:" +thread.getPriority() + " name:" + thread.getName());
    }
}
