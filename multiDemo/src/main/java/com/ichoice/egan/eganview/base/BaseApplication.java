package com.ichoice.egan.eganview.base;

import android.app.Application;

/**
 * Created by 刘大军 on 2015/11/4.
 */
public class BaseApplication extends Application {
    /**
     * 私有化成员属性
     */
    private static BaseApplication baseApplication;

    /**
     * 获得context
     * @return
     */
    public static BaseApplication getInstance (){
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //为其赋值
        baseApplication = this;
    }
}
