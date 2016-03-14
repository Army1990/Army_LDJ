/*
package com.ichoice.egan.eganview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ichoice.egan.eganview.R;

*/
/**
 * Created by yanshaokun@ichoice.cc on 2015/11/16.
 *//*

public class ListViewItemWithDelete extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {
    */
/**
     * 手势识别对象
     *//*

    private GestureDetector gestureDetector;
    */
/**
     * 删除按钮是否显示
     *//*

    private boolean isDeleteShow;
    */
/**
     * 删除按钮对象
     *//*

    private Button deleteButton;
    */
/**
     * 选中的项
     *//*

    private int selectItemPosition;
    */
/**
     * 选中的item的布局
     *//*

    private ViewGroup viewGroup;

    */
/**
     * 点击条目中删除的点击事件
     *//*

    private OnItemDeleteListener onItemDeleteListener;

    public ListViewItemWithDelete(Context context) {
        super(context);
        init(context);
    }

    public ListViewItemWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
    }

    public void setOnItemDeleteListener (OnItemDeleteListener onItemDeleteListener){
        this.onItemDeleteListener = onItemDeleteListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //获得当前的触摸item
        selectItemPosition = pointToPosition((int) event.getX(), (int) event.getY());
        //显示删除按钮，从item中隐藏或删除
        if (isDeleteShow) {
            viewGroup.removeView(deleteButton);
            deleteButton = null;
            isDeleteShow = false;
            return false;
        } else {
            return gestureDetector.onTouchEvent(event);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShow) {
            selectItemPosition = pointToPosition((int) e.getX(), (int) e.getY());
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    */
/**
     * 滑动删除的主要响应方法
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     *//*

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //如果删除按钮没有显示，并且手势滑动符合我们的条件
        //此处可以根据需要进行手势滑动的判断，如限制左滑还是右滑，我这里是左滑右滑都可以
        if (!isDeleteShow && Math.abs(velocityX) > Math.abs(velocityY)) {
            //在当前布局上，动态添加我们的删除按钮，设置按钮的各种参数、事件，按钮的点击事件响应我们的删除项监听器
            deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.layout_button,null);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShow = false;
                    onItemDeleteListener.OnItemDelete(selectItemPosition);
                }
            });
            viewGroup = (ViewGroup)getChildAt(selectItemPosition - getFirstVisiblePosition());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            deleteButton.setLayoutParams(layoutParams);
            Log.d("TAG", viewGroup == null ? "viewgroup为空" : "Viewgroup部位空");
            Log.d("TAG",deleteButton == null? "deleteButton为空":"deleteButton不为空");
            viewGroup.addView(deleteButton);
            isDeleteShow = true;
        }else{
            setOnTouchListener(this);
        }
        return false;
    }

    public interface OnItemDeleteListener {
        public void OnItemDelete(int selectItemPosition);
    }
}
*/
