package com.ichoice.egan.eganview.Utils;

import android.util.Base64;

/**
 * Created by Hornen on 15/11/19.
 */
public class Base64Tools {
    /**
     * decode string to base64 byte
     * @param src source string
     * @return bytes array
     */
    public static byte[] deecode(String src) {
        return Base64.decode(src, Base64.DEFAULT);
    }

    /**
     * encode byte to base64 string
     * @param src source bytes
     * @return string
     */
    public static String encode(byte[] src) {
        return Base64.encodeToString(src, Base64.DEFAULT);
    }
}
