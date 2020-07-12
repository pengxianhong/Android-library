package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;

import com.pengxh.app.multilib.widget.EasyToast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: TODO
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2020/7/10 21:43
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtil {

    public static final String TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 时间戳转时间
     */
    public static String timestampToTime(long timestamp, String patter) {
        SimpleDateFormat format = new SimpleDateFormat(patter);
        return format.format(new Date(timestamp));
    }

    /**
     * 时间转时间戳
     */
    public static long timeToTimestamp(String time, String patter) {
        SimpleDateFormat format = new SimpleDateFormat(patter);
        try {
            Date date = format.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            EasyToast.showToast("时间格式不匹配", EasyToast.WARING);
        }
        return 0L;
    }
}
