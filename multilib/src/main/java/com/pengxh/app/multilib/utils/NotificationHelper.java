package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.pengxh.app.multilib.R;

/**
 * @description: TODO 快速创建通知
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2020/7/12 22:57
 */
public class NotificationHelper {

    private static final String TAG = "NotificationHelper";
    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static NotificationHelper notificationHelper;
    private NotificationManager notificationManager;

    private NotificationHelper(Context context) {
        this.mContext = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
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

    private Notification.Builder createBuilder() {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = mContext.getResources().getString(R.string.app_name);
            //创建渠道
            String id = name + "_DefaultChannel";
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(mChannel);
            builder = new Notification.Builder(mContext, id);
        } else {
            builder = new Notification.Builder(mContext);
        }
        return builder;
    }

    /**
     * 创建常驻状态栏通知
     *
     * @param title   通知标题
     * @param message 通知内容
     */
    public Notification createKeepAlive(String title, String message) {
        Notification.Builder builder = createBuilder();
        builder.setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        return notification;
    }

    /**
     * 创建无动作的默认通知
     *
     * @param title   通知标题
     * @param message 通知内容
     */
    public Notification createDefault(String title, String message) {
        Notification.Builder builder = createBuilder();
        builder.setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setAutoCancel(true);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    /**
     * 创建有动作的默认通知
     *
     * @param title       通知标题
     * @param message     通知内容
     * @param targetClass 点击通知之后跳转的页面
     */
    public Notification createDefault(String title, String message, Class<?> targetClass) {
        Notification.Builder builder = createBuilder();
        Intent intent = new Intent(mContext, targetClass);
        builder.setContentTitle(title)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(PendingIntent.getActivity(mContext, 0, intent, 0))
                .setAutoCancel(true);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    //显示通知
    public void push(int notificationId, Notification notification) {
        if (notificationManager != null) {
            notificationManager.notify(notificationId, notification);
        }
    }

    //取消通知
    public void cancel(int notificationId) {
        if (notificationManager != null) {
            notificationManager.cancel(notificationId);
        }
    }
}
