package com.ichoice.egan.eganview.SwipeRefreshLayoutActivity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.Utils.ViewUtil;

import java.util.ArrayList;

/**
 * Created by YSK on 2015/11/26.
 */
public class SwipeRefreshLayoutAdapter extends BaseAdapter {
    private ArrayList<View> data_head1 = new ArrayList<>();
    private ArrayList<String> data_head = new ArrayList<>();
    private ArrayList<String> data_body = new ArrayList<>();
    private Context context;

    public SwipeRefreshLayoutAdapter(ArrayList<View> data_head1, ArrayList<String> data_head, ArrayList<String> data_body, Context context) {
        this.data_head1 = data_head1;
        this.data_head = data_head;
        this.data_body = data_body;
        this.context = context;
    }

    public SwipeRefreshLayoutAdapter(ArrayList<String> data_head, ArrayList<String> data_body, Context context) {
        this.data_head = data_head;
        this.data_body = data_body;
        this.context = context;
    }

    public SwipeRefreshLayoutAdapter(ArrayList<String> data_body, Context context) {
        this.data_body = data_body;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data_body.size() + 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            switch (getItemViewType(position)){
                case 0:
                    //头viewPager布局
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_head1, null);
                    break;
                case 1:
                    //头水平滑动布局
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_horizontal, null);
                    break;
                case 2:
                    //正常listView布局
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, null);
                    break;
            }
        }

        switch (getItemViewType(position)){
            case 0:
                ViewPager viewPager = (ViewPager) ViewUtil.getItemChildView(convertView, R.id.view_pager);
                viewPager.setAdapter(new ViewPagerAdapter(data_head1));
                break;
            case 1:
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view_1)).setText("text1");
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view_2)).setText("text2");
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view_3)).setText("text3");
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view_4)).setText("text4");
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view_5)).setText("text5");
                break;
            case 2:
                ((TextView) ViewUtil.getItemChildView(convertView, R.id.text_view)).setText(data_body.get(position - 2));
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : (position == 1 ? 1 : 2);
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
