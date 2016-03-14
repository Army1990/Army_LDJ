package com.ichoice.egan.eganview.Utils;

import android.content.Context;

/**
 * Created by Hornen on 15/12/15.
 */
public class DipPixelsTools {

    // dip转像素
    public static int dipToPixels(Context context, int dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;

        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);

        return valuePixels;

    }

    // 像素转dip
    public static float pixelsToDip(Context context, int Pixels) {
        final float SCALE = context.getResources().getDisplayMetrics().density;

        float dips = Pixels / SCALE;

        return dips;

    }
}
