package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 快速创建通知
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2020/7/12 22:57
 */
public class NotificationHelper {

    private Context mContext;
    private Map<String, Integer> notificationIdMap;//存放通知id
    @SuppressLint("StaticFieldLeak")
    private static NotificationHelper notificationHelper;

    private NotificationHelper(Context context) {
        this.mContext = context;
        notificationIdMap = new HashMap<>();
    }

    /**
     * 双重锁单例
     */
    public static NotificationHelper getInstance(Context context) {
        if (notificationHelper == null) {
            synchronized (NotificationHelper.class) {
                if (notificationHelper == null) {
                    notificationHelper = new NotificationHelper(context);
                }
            }
        }
        return notificationHelper;
    }

    //创建通知
    public Notification create() {


        return null;
    }

    //显示通知
    public void push(int notificationId, Notification notification) {

    }

    //取消通知
    public void cancel(int notificationId) {

    }
}
