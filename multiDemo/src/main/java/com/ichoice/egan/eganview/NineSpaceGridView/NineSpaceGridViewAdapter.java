package com.ichoice.egan.eganview.NineSpaceGridView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.Utils.ViewUtil;

import java.util.ArrayList;

/**
 * Created by YSK on 2015/10/29.
 */
public class NineSpaceGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> strings;
    private GridView gridView;
    private int numColumns;
    private ItemClickInterface itemClickInterface;

    public NineSpaceGridViewAdapter(Context context, ArrayList<String> strings, GridView gridView, int numColumns, ItemClickInterface itemClickInterface) {
        this.context = context;
        this.strings = strings;
        this.gridView = gridView;
        this.numColumns = numColumns;
        this.itemClickInterface = itemClickInterface;
    }

    @Override
    public int getCount() {
        return strings.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if ("ysk_null".equals(strings.get(position))) {
            if (convertView == null || convertView.findViewById(R.id.textView_userName) != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_null_nine_space_gridview, parent, false);
            }
        } else {
            if (convertView == null || convertView.findViewById(R.id.textView_userName) == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_nine_space_gridview,parent,false);
            }
            TextView textView_userName = (TextView) ViewUtil.getItemChildView(convertView, R.id.textView_userName);
            textView_userName.setText(strings.get(position));
            Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
            //设置图片时，注意与setCompoundDrawables()的不同（需要事先设置好Drawables的Bounds）
            textView_userName.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);

            //根据屏幕的宽度来设置convertView的高度
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            int activityWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
            layoutParams.height = (activityWidth - (numColumns-1) * gridView.getVerticalSpacing())/(numColumns);
            convertView.setLayoutParams(layoutParams);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickInterface.itemClick(position);
                }
            });
        }
        return convertView;
    }

    /**
     * 定义一个接口，实现有数据的item点击事件
     */
    public interface ItemClickInterface{
        public void itemClick(int position);
    }

}
