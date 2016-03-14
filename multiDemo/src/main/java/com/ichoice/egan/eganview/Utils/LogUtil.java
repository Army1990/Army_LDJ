package com.ichoice.egan.eganview.Utils;

import android.util.Log;

/**
 * LogCat工具类
 * @author Egan.ysk.Gmail.com
 * @data 2015/11/4.
 */
public class LogUtil {
    private static final String TAG = "Egan";

    public static void logMessage(String stringLogMessage){
        Log.d(TAG, Thread.currentThread().getName() + "-->> " + stringLogMessage);
    }
}
