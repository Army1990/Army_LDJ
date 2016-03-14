package com.ichoice.egan.eganview.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maychoo.meifou.R;
import com.maychoo.meifou.common.AccountDataRepo;
import com.maychoo.meifou.data.network.request.CollectionClickRequest;
import com.maychoo.meifou.data.network.request.IRequestCallback;
import com.maychoo.meifou.data.network.response.StringResponse;
import com.maychoo.meifou.ui.LoginActivity;

/**
 * Created by CC on 2015/12/11.
 */

//收藏与取消收藏模块
public class Collection {

    public static CollectionClickRequest collectionRequest = new CollectionClickRequest();

    //收藏的请求
    public static void getCollectionData(final String a,String activityid) {
        collectionRequest.id = activityid;
        collectionRequest.a = a;
        collectionRequest.userid = AccountDataRepo.instance.getAccount().uid;
        collectionRequest.post(new IRequestCallback<StringResponse>() {

            @Override
            public void onResponse(StringResponse response) {
                if (response.isSuccess()) {
                    if (response.obj.equals("")) {
                        MessageNotifyTools.showToast("已取消收藏");
                    } else if (response.obj.equals("收藏成功") || response.obj.equals("取消收藏")) {
                        MessageNotifyTools.showToast(response.obj);
                    } else {
                        MessageNotifyTools.showToast("收藏成功");
                    }
                }
            }

            @Override
            public void onException(Throwable throwable) {

            }

            @Override
            public void onCancelled() {

            }
        });

    }

    //已收藏说说
    public static void Collection(final Context context, final TextView tv, final ImageView iv, final String activityid, final View view, final boolean isLogin) {
        tv.setTextColor(context.getResources().getColor(R.color.text_pink));
        iv.setImageResource(R.mipmap.icon_favorite);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                        getCollectionData("cancelfavoriteActivity", activityid);
                        unCollection(context, tv, iv,activityid, view, isLogin);
                }
            }
        });
    }

    //未收藏说说
    public static void unCollection(final Context context, final TextView tv, final ImageView iv,final String activityid, final View view, final boolean isLogin) {
        tv.setTextColor(context.getResources().getColor(R.color.text_thingray));
        iv.setImageResource(R.mipmap.icon_unfavorite);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                        getCollectionData("favoriteActivity", activityid);
                        Collection(context, tv, iv, activityid, view, isLogin);
                }
            }
        });
    }

    public static void isCollection(final Context context, final TextView tv, final ImageView iv,final String activityid, final View view, int flag, boolean isLogin) {

        if (flag == 1) {
                Collection(context, tv, iv, activityid, view, isLogin);

        } else {
                unCollection(context, tv, iv,activityid, view, isLogin);
        }
    }


}
