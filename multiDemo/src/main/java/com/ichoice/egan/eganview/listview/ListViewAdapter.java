package com.ichoice.egan.eganview.listview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichoice.egan.eganview.R;

import java.util.ArrayList;

/**
 * Created by 刘大军 on 2015/11/14.
 */
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<String> data;
    private Context context;
    private GestureDetector gestureDetector;
    private int width;
    private ViewHolder viewHolder = null;
    private ViewHolder viewHolder1 = null;

    public ListViewAdapter(Context context, ArrayList<String> data, int width) {
        this.data = data;
        this.context = context;
        this.width = width;
        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
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
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder1.tv_1.getLayoutParams();
                int left = (int) (params.leftMargin - distanceX);
                if (left <= 0) {
                    params.setMargins(left, params.topMargin, params.rightMargin, params.bottomMargin);
                    viewHolder1.tv_1.setLayoutParams(params);
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_with_delete_listview, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_1 = (TextView) convertView.findViewById(R.id.textView_item);
        viewHolder.tv_2 = (TextView) convertView.findViewById(R.id.textView_delete);

        ViewGroup.LayoutParams layoutParams = viewHolder.tv_1.getLayoutParams();
        layoutParams.width = width;
        viewHolder.tv_1.setLayoutParams(layoutParams);

        //处理其显示逻辑
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //通过手势判断是什么操作
                viewHolder1 = (ViewHolder) v.getTag();
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_1;
        TextView tv_2;
    }
}
