package com.ichoice.egan.eganview.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hornen on 15/11/19.
 */
public class TimeTools {

    /**
     * get current time seconds
     * @return second time
     */
    public static long currentTimeSeconds() {
        return (System.currentTimeMillis() / 1000);
    }

    /**
     * get current time millis
     * @return millis time
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 根据提供的秒值，计算相对时间戳
     *
     * @param senconds
     * @return
     */
    public static String calRelativeTimestap(long senconds) {
        long interval = currentTimeSeconds() - senconds;

        if(interval < 60 * 5) {
            return "刚刚";
        }

        if(interval < 60 * 60 ) {
            return (interval / 60) + "分钟前";
        }

        if(interval < 60 * 60 * 24) {
            return (interval / (60 * 60)) + "小时前";
        }

        if(interval < 60 * 60 * 24 * 30) {
            return (interval / (60 * 60 * 24)) + "天前";
        }

        if(interval < 60 * 60 * 24 * 30 * 12) {
            return (interval / (60*60*24*30)) + "个月前";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyy.MM.dd");
        Date date = new Date(senconds * 1000);
        return formatter.format(date);
    }

    public static String calRelativeTimestap(String senconds) {
        return calRelativeTimestap(Long.parseLong(senconds));
    }

}
