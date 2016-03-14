package com.ichoice.egan.eganview.Utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maychoo.meifou.R;
import com.maychoo.meifou.common.AccountDataRepo;
import com.maychoo.meifou.data.network.request.IRequestCallback;
import com.maychoo.meifou.data.network.request.PraiseAskRequest;
import com.maychoo.meifou.data.network.response.StringResponse;
import com.maychoo.meifou.module.AnswerInfo;
import com.maychoo.meifou.module.GetAnswerDetail;

/**
 * Created by CC on 2015/12/19.
 */

public class AskPraise {

    public static PraiseAskRequest praiseAskRequest = new PraiseAskRequest();
    public static String userid = AccountDataRepo.instance.getAccount().uid;

    //点赞的请求
    public static void clickPriseData(String id, String a, final TextView tv, final Object
            object) {

        praiseAskRequest.id = id;
        praiseAskRequest.a = a;
        praiseAskRequest.userId = userid;

        praiseAskRequest.post(new IRequestCallback<StringResponse>() {
            @Override
            public void onResponse(StringResponse response) {
                if (response.isSuccess()) {
                    // MessageNotifyTools.showToast("成功");
                    if (object instanceof AnswerInfo) {
                        AnswerInfo answerInfo = (AnswerInfo) object;
                        tv.setText(response.obj.toString());
                        answerInfo.praises = Integer.parseInt(response.obj);
                    }
                    if (object instanceof GetAnswerDetail.AnswerEntity) {
                        GetAnswerDetail.AnswerEntity answerEntity = (GetAnswerDetail.AnswerEntity) object;
                        tv.setText(response.obj.toString());
                        answerEntity.setPraisenum(response.obj);
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

    //已点赞
    public static void Praised(final Context context, final TextView tv, final ImageView iv, final String id, final
    View view, final Object object) {
        tv.setTextColor(context.getResources().getColor(R.color.text_pink));
        iv.setImageResource(R.mipmap.prise_press);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object instanceof AnswerInfo) {
                    AnswerInfo answerInfo = (AnswerInfo) object;
                    clickPriseData(id, "unPraiseAnswer", tv, answerInfo);
                    answerInfo.praisestatus = 0;
                }
                if (object instanceof GetAnswerDetail.AnswerEntity) {
                    GetAnswerDetail.AnswerEntity answerEntity = (GetAnswerDetail.AnswerEntity) object;
                    clickPriseData(id, "unPraiseAnswer", tv, answerEntity);
                    answerEntity.setPraisestatus(0);
                }
                unPraised(context, tv, iv, id, view, object);
            }
        });
    }

    //未点赞
    public static void unPraised(final Context context, final TextView tv, final ImageView iv, final String id, final
    View view, final Object object) {
        tv.setTextColor(context.getResources().getColor(R.color.text_deepgray));
        iv.setImageResource(R.mipmap.discover_recommend_dianzan);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object instanceof AnswerInfo) {
                    AnswerInfo answerInfo = (AnswerInfo) object;
                    clickPriseData(id, "praiseAnswer", tv, answerInfo);
                    answerInfo.praisestatus = 1;
                }
                if (object instanceof GetAnswerDetail.AnswerEntity) {
                    GetAnswerDetail.AnswerEntity answerEntity = (GetAnswerDetail.AnswerEntity) object;
                    clickPriseData(id, "praiseAnswer", tv, answerEntity);
                    answerEntity.setPraisestatus(1);
                }
                Praised(context, tv, iv, id, view, object);
            }
        });
    }

    public static void isPraised(final Context context, final TextView tv, final ImageView iv, final String id, final
    View view, int priseStatus, Object object) {

        if (priseStatus == 1) {
            Praised(context, tv, iv, id, view, object);
        } else {
            unPraised(context, tv, iv, id, view, object);
        }
    }


}
