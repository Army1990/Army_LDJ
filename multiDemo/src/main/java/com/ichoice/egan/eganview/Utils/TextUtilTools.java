package com.ichoice.egan.eganview.Utils;


import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import com.maychoo.meifou.MeifouApplication;
import com.maychoo.meifou.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by 刘大军 on 2015/12/24.
 */
public class TextUtilTools {

    /**
     * 关键字高亮显示
     *
     * @param key  需要高亮的关键字
     * @param text       需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     */
    public static SpannableStringBuilder highlight(String text, String key) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(key);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(MeifouApplication.getAppContext().getResources().getColor(R.color.text_pink));// 需要重复！
            spannable.setSpan(span, m.start(), m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

}