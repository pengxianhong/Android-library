package com.pengxh.app.androidlib;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.pengxh.app.multilib.utils.LogToFile;
import com.pengxh.app.multilib.utils.NotificationHelper;
import com.pengxh.app.multilib.utils.TimeUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description: TODO
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2020/7/15 22:22
 */
public class KeepAliveService extends Service {

    private static final String TAG = "KeepAliveService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 启动计时");
        LogToFile.d(TAG, "onCreate: 启动计时");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, TimeUtil.timestampToTime(System.currentTimeMillis(), TimeUtil.TIME));
                LogToFile.d(TAG, TimeUtil.timestampToTime(System.currentTimeMillis(), TimeUtil.TIME));
            }
        }, 0, 10 * 1000);
        NotificationHelper notificationHelper = NotificationHelper.getInstance(this);
        Notification notification = notificationHelper.createKeepAlive("通知", "这是一条测试通知");
        startForeground(101, notification);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: 重启KeepAliveService服务");
        LogToFile.d(TAG, "onDestroy: 重启KeepAliveService服务");
        Intent intent = new Intent(this, KeepAliveService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        super.onDestroy();
    }
}
