package com.elapse.demoencrypt.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * author : Kevin.ning
 * e-mail :
 * date   : 2019/10/21 16:13
 * desc   :
 * version: 1.0
 */
public class Base64Util {

    /**
     * 编码
     *
     * @param message 需编码的信息
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeWord(String message) throws UnsupportedEncodingException {
        return Base64.encodeToString(message.getBytes("utf-8"), Base64.NO_WRAP);
    }

    /**
     * 解码
     *
     * @param encodeWord 编码后的内容
     * @return decode info
     * @throws UnsupportedEncodingException
     */
    public static String decodeWord(String encodeWord) throws UnsupportedEncodingException {
        return new String(Base64.decode(encodeWord, Base64.NO_WRAP), "utf-8");
    }

}
