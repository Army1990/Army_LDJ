package com.ichoice.egan.eganview.GestureDetector;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.Utils.LogUtil;
import com.ichoice.egan.eganview.base.BaseActivity;

public class GestureDetectorActivity extends BaseActivity implements View.OnTouchListener {

    private GestureDetector gestureDetector;
    private GestureDetector.OnGestureListener onGestureListener;
    private RelativeLayout rl_1;
    private RelativeLayout rl_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        getSupportActionBar().hide();
        rl_1 = ((RelativeLayout) findViewById(R.id.rl_1));
        rl_2 = ((RelativeLayout) findViewById(R.id.rl_2));

        onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                LogUtil.logMessage("e.X:" + e.getX());
                LogUtil.logMessage("e.Y:" + e.getY());
                LogUtil.logMessage("onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                LogUtil.logMessage("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                LogUtil.logMessage("onSingleTapUp");
                return false;
            }

            /**
             *
             * @param e1 起点坐标
             * @param e2 当前坐标
             * @param distanceX 本次x移动距离
             * @param distanceY 本次y移动距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                LogUtil.logMessage("onScroll");
                LogUtil.logMessage("e1.x:" + e1.getX());
                LogUtil.logMessage("e1.y:" + e1.getY());
                LogUtil.logMessage("e2.x:" + e2.getX());
                LogUtil.logMessage("e2.y:" + e2.getY());
                LogUtil.logMessage("distanceX:" + distanceX);
                LogUtil.logMessage("distanceY:" + distanceY);

                //为rl_1动态设置位置属性
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_1.getLayoutParams();
                int left = (int) (params.leftMargin - distanceX);
                if (left <= 0) {
                    params.setMargins(left, params.topMargin, params.rightMargin, params.bottomMargin);
                    rl_1.setLayoutParams(params);
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                LogUtil.logMessage("onLongPress");

            }

            /**
             *
             * @param e1
             * @param e2
             * @param velocityX 想轴速度、速率
             * @param velocityY
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                LogUtil.logMessage("onFling");
                LogUtil.logMessage("velocityX:" + velocityX);
                LogUtil.logMessage("velocityY:" + velocityY);
                return false;
            }
        };
        gestureDetector = new GestureDetector(this, onGestureListener);

        rl_1.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //将图片的触摸事件传递给手势识别对象来处理
        gestureDetector.onTouchEvent(event);
        return true;
    }

}
