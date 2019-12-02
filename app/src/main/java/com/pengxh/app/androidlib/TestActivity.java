package com.pengxh.app.androidlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.BroadcastManager;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2019/12/2 15:31
 */
public class TestActivity extends BaseNormalActivity {

    private String TAG = "TestActivity";
    private String USER_ACTION = "action.receiverData";
    private BroadcastManager broadcastManager;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        broadcastManager = BroadcastManager.getInstance(this);
    }

    @Override
    public void initEvent() {
        broadcastManager.addAction(USER_ACTION, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    if (action.equals(USER_ACTION)) {
                        String stringExtra = intent.getStringExtra("data");
                        Log.d(TAG, "onReceive: " + stringExtra);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.destroy(USER_ACTION);
    }
}
