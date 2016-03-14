package com.ichoice.egan.eganview.NineSpaceGridView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.base.BaseActivity;

import java.util.ArrayList;

public class NineSpaceGridViewActivity extends BaseActivity {
    private ArrayList<String> strings;
    private GridView gridView_NineSpace;
    private static final int numColumns = 3;
    private NineSpaceGridViewAdapter.ItemClickInterface itemClickInterface = new NineSpaceGridViewAdapter.ItemClickInterface() {
        @Override
        public void itemClick(int position) {
            Toast.makeText(NineSpaceGridViewActivity.this, strings.get(position),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        initView();
        initData();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        gridView_NineSpace = ((GridView) findViewById(R.id.gridView_nineSpace));
        gridView_NineSpace.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("item:" + i);
        }

        //不能通过下面的代码获得xml布局中设置的gridView的列数
        /*int numColumns = gridView_NineSpace.getNumColumns();
        gridView_NineSpace.setNumColumns(GridView.AUTO_FIT);
        Log.d("ysk","每行个数" + numColumns);*/

        strings = oldData2NewData(strings, numColumns);
        gridView_NineSpace.setAdapter(new NineSpaceGridViewAdapter(this, strings, gridView_NineSpace, numColumns, itemClickInterface));
    }

    /**
     * 对数据进行填充，实转虚
     * @param oldData 需要填充的真实数据
     * @param numberColumns 每行的个数
     * @return
     */
    private ArrayList<String> oldData2NewData(ArrayList<String> oldData, int numberColumns) {
        int size = oldData.size();
        for (int i = size - 1; i >= 0; i--) {
            if (size >= numberColumns*numberColumns){
                if (i%(numberColumns*numberColumns) == numberColumns*numberColumns -1){
                    for (int j = 0; j < numberColumns; j++) {
                        oldData.add(i + j + 1, "ysk_null");
                    }
                }
            }
        }
        Log.d("ysk","size: " + oldData.size());
        return oldData;
    }
}
