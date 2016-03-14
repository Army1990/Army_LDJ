package com.ichoice.egan.eganview.SwipeRefreshLayoutActivity;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.Utils.ToastUtil;
import com.ichoice.egan.eganview.base.BaseActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

public class GitHubSwipeRefreshLayoutActivity extends BaseActivity {

    private SwipyRefreshLayout swipyRefreshLayout;
    private ListView listView_testSwipyRefreshLayout;
    private int[] images = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_swipe_refresh_layout);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("item: " + i);
        }

        ArrayList<String> strings_head2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings_head2.add("headItem: " + i);
        }

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShortTime("viewPager Item " + (finalI + 1));
                }
            });
            views.add(imageView);
        }

        swipyRefreshLayout = ((SwipyRefreshLayout) findViewById(R.id.swipyRefreshLayout));
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_green_dark,
                android.R.color.holo_blue_bright, android.R.color.darker_gray);

        listView_testSwipyRefreshLayout = ((ListView) findViewById(R.id.listView_testSwipyRereshLayout));

        listView_testSwipyRefreshLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShortTime("listView item " + position);
            }
        });

        SwipeRefreshLayoutAdapter adapter = new SwipeRefreshLayoutAdapter(views, strings_head2, strings, this);
        listView_testSwipyRefreshLayout.setAdapter(adapter);

        //刷新方法
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    down2RefreshData();
                }
                if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
                    up2RefreshData();
                }
                //模拟2秒后加载结束
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GitHubSwipeRefreshLayoutActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    /**
     * 下拉去刷新
     */
    private void down2RefreshData() {
        Toast.makeText(this,"下拉刷新",Toast.LENGTH_SHORT).show();
    }

    /**
     * 上拉加载
     */
    private void up2RefreshData() {
        Toast.makeText(this,"上拉加载",Toast.LENGTH_SHORT).show();
    }

}