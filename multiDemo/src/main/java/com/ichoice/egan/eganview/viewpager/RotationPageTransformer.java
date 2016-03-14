package com.ichoice.egan.eganview.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 旋转角度的切换viewpager效果
 * Created by 刘大军 on 2015/11/12.
 */
public class RotationPageTransformer implements ViewPager.PageTransformer {
    /**
     * 旋转的最大角度
     */
    private static final float ROTATION_MAX = 20.0f;
    /**
     * 需要动态设置的角度变量
     */
    private float mRotation;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        if (position < -1) {
            //该view已经不显示了
            page.setRotation(0);
        }

        if (position <= 1) {
            //旋转
            mRotation = ROTATION_MAX * position;
            page.setPivotX(page.getMeasuredWidthAndState() * 0.5f);
            page.setPivotY(page.getMeasuredHeightAndState());
            page.setRotation(mRotation);
            //另一种效果的实现
            float scaleFactor = Math.max(0.85f, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0)
            {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else
            {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }

        if (position > 1){
            page.setRotation(0);
        }
    }
}
