package com.ichoice.egan.eganview.Utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maychoo.meifou.R;
import com.maychoo.meifou.common.AccountDataRepo;
import com.maychoo.meifou.data.network.request.IRequestCallback;
import com.maychoo.meifou.data.network.request.PraiseClickRequest;
import com.maychoo.meifou.data.network.response.StringResponse;
import com.maychoo.meifou.module.TextComment;

/**
 * Created by CC on 2015/12/11.
 */

//点赞与取消点赞模块
public class Praise {

    public static PraiseClickRequest praiseClickRequest = new PraiseClickRequest();
    public static String userid = AccountDataRepo.instance.getAccount().uid;

    //点赞的请求
    public static void clickPriseData(String id, String a, String flag, String flagTag, final TextView tv, final TextComment
            object) {

        praiseClickRequest.textId = id;
        praiseClickRequest.a = a;
        praiseClickRequest.flag = flag;
        praiseClickRequest.userId = userid;
        praiseClickRequest.flagTag = flagTag;

        praiseClickRequest.post(new IRequestCallback<StringResponse>() {
            @Override
            public void onResponse(StringResponse response) {
                if (response.isSuccess()) {
                    // MessageNotifyTools.showToast("成功");
                    tv.setText(response.obj.toString());
                    object.setPraisenum(response.obj.toString());


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

    //已点赞
    public static void Praised(final Context context, final TextView tv, final ImageView iv, final String id, final
    View view, final String una, final String unflag, final String flagTag, final String a, final String flag, final
                               TextComment object) {
        tv.setTextColor(context.getResources().getColor(R.color.text_pink));
        iv.setImageResource(R.mipmap.prise_press);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPriseData(id, una, unflag, flagTag, tv, object);
                object.setPraisestatus(0);

                unPraised(context, tv, iv, id, view, a, flag, flagTag, una, unflag, object);
            }
        });
    }

    //未点赞
    public static void unPraised(final Context context, final TextView tv, final ImageView iv, final String id, final
    View view, final String a, final String flag, final String flagTag, final String una, final String unflag, final
                                 TextComment object) {
        tv.setTextColor(context.getResources().getColor(R.color.text_deepgray));
        iv.setImageResource(R.mipmap.discover_recommend_dianzan);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPriseData(id, a, flag, flagTag, tv, object);
                object.setPraisestatus(1);
                Praised(context, tv, iv, id, view, una, unflag, flagTag, a, flag, object);
            }
        });
    }

    public static void isPraised(final Context context, final TextView tv, final ImageView iv, final String id, final View view, int priseStatus, TextComment comment) {

        if (priseStatus == 1) {
            Praised(context, tv, iv, id, view, "unpraiseByType", "whattounpraise", "mfactivityshowcomment", "praiseByType", "whattopraise", comment);
        } else {
            unPraised(context, tv, iv, id, view, "praiseByType", "whattopraise", "mfactivityshowcomment", "unpraiseByType", "whattounpraise", comment);
        }
    }


}
