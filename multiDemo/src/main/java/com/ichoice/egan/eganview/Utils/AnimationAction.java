package com.ichoice.egan.eganview.Utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by 刘大军 on 2015/12/17.
 */


public class AnimationAction {

    /**
     * 显示组件动画
     */
    public static void ShowAction(View view,int time) {
        //位移动画
//        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
       //渐变动画
        Animation mShowAction = new AlphaAnimation(0.1f, 0.7f);

        mShowAction.setDuration(time);
        view.startAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏组件动画
     */
    public static void HiddenAction(View view,int time) {
//        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
//                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f);
        Animation mHiddenAction = new AlphaAnimation(0.7f, 0.1f);
        mHiddenAction.setDuration(time);
        view.startAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }
}
