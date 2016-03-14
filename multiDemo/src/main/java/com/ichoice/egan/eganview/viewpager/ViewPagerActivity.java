package com.ichoice.egan.eganview.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ichoice.egan.eganview.R;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPage;
    private int[] images = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        getSupportActionBar().hide();
        int flag = getIntent().getIntExtra("flag", 0);

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            views.add(imageView);
        }

        viewPage = ((ViewPager) findViewById(R.id.viewPager));
        viewPage.setAdapter(new ViewPaperAdapter(views));
        switch (flag) {
            case 1:
                break;
            case 2:
                viewPage.setPageTransformer(true, new DepthPageTransformer());
                break;
            case 3:
                viewPage.setPageTransformer(true, new ZoomOutPageTransformer());
                break;
            case 4:
                viewPage.setPageTransformer(true, new RotationPageTransformer());
                break;
        }
    }


}

class ViewPaperAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public ViewPaperAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        } else
            return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
}
