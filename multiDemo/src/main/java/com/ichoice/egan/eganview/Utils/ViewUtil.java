package com.ichoice.egan.eganview.Utils;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder等视图工具栏
 *
 * @author Egan.ysk@Gmail.com
 * @data 2015/10/29.
 */
public class ViewUtil {

    /**
     * ViewHolder的极简写法
     *
     * @param convertView
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T getItemChildView(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
