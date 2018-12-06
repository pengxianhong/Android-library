package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/11/16.
 */

public class TextUtil {

    private static final String TAG = "TextUtil";

    /**
     * @param mContext
     * @param msg
     */
    public static void showShortToast(Context mContext, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }

    /**
     * @param mContext
     * @param msg
     */
    public static void showLongToast(Context mContext, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }

    /**
     * @param str
     */
    public static String regExString(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>《》/?~！@#￥%……&*（）——+|{}【】'；：”“’。，、？]";
        Matcher matcher = Pattern.compile(regEx).matcher(str);
        return matcher.replaceAll("").trim();
    }
}
