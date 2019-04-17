package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/11/16.
 */

public class TextUtil {

    private static final String TAG = "TextUtil";
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context mContext) {
        TextUtil.mContext = mContext.getApplicationContext();//获取全局上下文，最长生命周期
    }

    /**
     * 简单封装原生Toast
     *
     * @param msg     消息体
     * @param isShort 是否短时间显示
     */
    public static void showToast(String msg, boolean isShort) {
        if (!TextUtils.isEmpty(msg)) {
            if (isShort) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }

    /**
     * 简单封装Toast三方框架，高效果更炫，使用更简单
     *
     * @param msg
     * @param level [0,5]
     */
    public static void showBeautifulToast(String msg, int level) {
        if (!TextUtils.isEmpty(msg)) {
            if (level > 5 || level < 0) {
                Log.e(TAG, "Toast's level field is [0,5]");
            } else {
                switch (level) {
                    case 0:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.DEFAULT).show();
                        break;
                    case 1:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.INFO).show();
                        break;
                    case 2:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.WARNING).show();
                        break;
                    case 3:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.SUCCESS).show();
                        break;
                    case 4:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.CONFUSING).show();
                        break;
                    case 5:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.ERROR).show();
                        break;
                    default:
                        break;
                }
            }
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }

    /**
     * 匹配中文符号
     *
     * @param str
     */
    public static String regExString(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>《》/?~！@#￥%……&*（）——+|{}【】'；：”“’。，、？]";
        Matcher matcher = Pattern.compile(regEx).matcher(str);
        return matcher.replaceAll("").trim();
    }
}
