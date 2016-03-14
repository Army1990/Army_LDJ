package com.ichoice.egan.eganview.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;

import com.ichoice.egan.eganview.Utils.logger.Logger;

import java.lang.reflect.Method;

/**
 * Created by Hornen on 15/11/24.
 */
public class AppBase {
    private static Context ctx;
    private static Activity activity;
    private static Handler dispatcher = new Handler();

    public static void setContext(Context curCtx) {
        ctx = curCtx;
    }

    public static void setCurrentActivity(Activity act) {
        if(ctx == null && act != null) {
            setContext(act.getApplicationContext());
        }

        activity = act;
        dispatcher = new Handler();
    }

    public static Activity getCurrentActivity() {
        if(activity != null) {
            return activity;
        } else {
            return null;
        }
    }

    public static Context getAppContext() {
        return ctx;
    }

    public static Context getContext() {
        return (Context)(activity != null?activity:ctx);
    }

    public static void releaseCurrentActivity(Activity act) {
        if(activity == act) {
            activity = null;
        }
    }

    public static boolean runOnDispatcher(final Runnable task) {
        Runnable taskWrapper = new Runnable() {
            public void run() {
                try {
                    task.run();
                } catch (Exception var2) {
                    Logger.e(var2.toString());
                }

            }
        };
        if(activity == null) {
            if(dispatcher != null) {
                dispatcher.post(taskWrapper);
                return true;
            } else {
                return false;
            }
        } else {
            activity.runOnUiThread(taskWrapper);
            return true;
        }
    }

    public static boolean runOnDispatcherDelay(Runnable task, long delay) {
        if(dispatcher == null) {
            return false;
        } else {
            dispatcher.postDelayed(task, delay);
            return true;
        }
    }

    public static boolean navigateToActivity(Class<? extends Activity> actClass) {
        if(activity == null) {
            return false;
        } else {
            Intent intent = new Intent(activity, actClass);
            activity.startActivity(intent);
            return true;
        }
    }

    public static boolean navigateToActivity(Intent intent) {
        if(activity == null) {
            return false;
        } else {
            activity.startActivity(intent);
            return true;
        }
    }

    public static int getPixelsOfDimens(int dimenId) {
        return ctx.getResources().getDimensionPixelSize(dimenId);
    }

    public static String getString(int textResId) {
        return ctx.getString(textResId);
    }

    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    private static Resources getResources() {
        return ctx.getResources();
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static Drawable getDrawableWithBounds(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    public static void openURLByBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent("android.intent.action.VIEW", uri);
        if(activity != null) {
            activity.startActivity(it);
        }

    }

    public static void backToHome() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        navigateToActivity(intent);
    }

    public static void overridePendingTransition(Activity activity, int in, int out) {
        Method overridePendingTransition = getInvokeMethod();
        if(overridePendingTransition != null) {
            Object[] objArray = new Object[]{Integer.valueOf(in), Integer.valueOf(out)};

            try {
                overridePendingTransition.invoke(activity, objArray);
            } catch (Exception var6) {
                Logger.e(var6.toString());
            }
        }

    }

    private static Method getInvokeMethod() {
        Method mOverridePendingTransition = null;

        try {
            Class[] clz = new Class[]{Integer.TYPE, Integer.TYPE};
            mOverridePendingTransition = Activity.class.getMethod("overridePendingTransition", clz);
        } catch (Exception var2) {
            Logger.e(var2.toString());
        }

        return mOverridePendingTransition;
    }
}
