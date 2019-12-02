package com.pengxh.app.multilib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO 广播管理者
 * @date: 2019/12/2 14:49
 */
public class BroadcastManager {
    private static final String TAG = "BroadcastManager";
    private Context mContext;
    private static BroadcastManager broadcastManager;
    private Map<String, BroadcastReceiver> receiverMap;

    private BroadcastManager(Context context) {
        this.mContext = context;
        receiverMap = new HashMap<>();
    }

    /**
     * 双重锁单例
     */
    public static BroadcastManager getInstance(Context context) {
        if (broadcastManager == null) {
            synchronized (BroadcastManager.class) {
                if (broadcastManager == null) {
                    broadcastManager = new BroadcastManager(context);
                }
            }
        }
        return broadcastManager;
    }

    /**
     * 添加单个Action,广播的初始化
     */
    public void addAction(String action, BroadcastReceiver receiver) {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            mContext.registerReceiver(receiver, filter);
            receiverMap.put(action, receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加多个Action,广播的初始化
     */
    public void addAction(String[] actions, BroadcastReceiver receiver) {
        try {
            IntentFilter filter = new IntentFilter();
            for (String action : actions) {
                filter.addAction(action);
                receiverMap.put(action, receiver);
            }
            mContext.registerReceiver(receiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送广播
     *
     * @param action 唯一码
     * @param msg    参数
     */
    public void sendBroadcast(String action, String msg) {
        try {
            Intent intent = new Intent();
            intent.setAction(action);
            intent.putExtra("data", msg);
            mContext.sendBroadcast(intent);
            Log.d(TAG, "sendBroadcast: " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁广播
     *
     * @param actions action集合
     */
    public void destroy(String... actions) {
        try {
            if (receiverMap != null) {
                for (String action : actions) {
                    BroadcastReceiver receiver = receiverMap.get(action);
                    if (receiver != null) {
                        mContext.unregisterReceiver(receiver);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}