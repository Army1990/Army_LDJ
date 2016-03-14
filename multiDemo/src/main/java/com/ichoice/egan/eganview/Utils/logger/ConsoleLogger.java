package com.ichoice.egan.eganview.Utils.logger;

import android.util.Log;

/**
 * Created by Hornen on 15/9/29.
 */
public class ConsoleLogger implements ILogger {


    @Override
    public void error(String tag, String msg) {
            Log.e(tag, msg);
    }

    @Override
    public void warn(String tag, String msg) {
            Log.w(tag, msg);
    }

    @Override
    public void fatal(String tag, String msg) {
            Log.wtf(tag, msg);
    }

    @Override
    public void debug(String tag, String msg) {
            Log.d(tag, msg);
    }
}
