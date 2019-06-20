package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

public class ToastUtil {
    private static final String TAG = "ToastUtil";
    public static final int DEFAULT = 0;
    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int SUCCESS = 3;
    public static final int CONFUSING = 4;
    public static final int ERROR = 5;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context mContext) {
        ToastUtil.mContext = mContext.getApplicationContext();//获取全局上下文，最长生命周期
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
                        TastyToast.makeText(mContext, msg, 0, TastyToast.DEFAULT);
                        break;
                    case 1:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.INFO);
                        break;
                    case 2:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.WARNING);
                        break;
                    case 3:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.SUCCESS);
                        break;
                    case 4:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.CONFUSING);
                        break;
                    case 5:
                        TastyToast.makeText(mContext, msg, 0, TastyToast.ERROR);
                        break;
                    default:
                        break;
                }
            }
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }
}
