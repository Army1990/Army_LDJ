package com.ichoice.egan.eganview.SwipeRefreshLayoutActivity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by YSK on 2015/11/27.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewPagerData = new ArrayList<>();

    public ViewPagerAdapter(ArrayList<View> viewPagerData) {
        this.viewPagerData = viewPagerData;
    }

    @Override
    public int getCount() {
        return viewPagerData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewPagerData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewPagerData.get(position));
        return viewPagerData.get(position);
    }
}
