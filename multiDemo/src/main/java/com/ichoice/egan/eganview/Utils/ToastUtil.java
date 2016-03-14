package com.ichoice.egan.eganview.Utils;

import android.widget.Toast;

import com.ichoice.egan.eganview.base.BaseApplication;

/**
 * Toast工具类
 * @author Egan.ysk@Gmail.com
 * @data 2015/11/4.
 */
public class ToastUtil {

    /**
     * ToastShortTime
     * @param stringMessage
     */
    public static void showShortTime(String stringMessage){
        Toast.makeText(BaseApplication.getInstance(),stringMessage,Toast.LENGTH_SHORT).show();
    }

    /**
     * ToastLongTime
     * @param stringMessage
     */
    public static void showLongTime(String stringMessage){
        Toast.makeText(BaseApplication.getInstance(),stringMessage,Toast.LENGTH_SHORT).show();
    }
}
