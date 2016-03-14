package com.ichoice.egan.eganview.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hornen on 15/11/19.
 */
public class VerifyTools {

    /**
     * check mobile format
     * @param mobile mobile phone
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * check email format
     * @param email email
     * @return boolean
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern
                .compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
