package com.pengxh.app.androidlib;

import android.content.Intent;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.BroadcastManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BroadcastManager.getInstance(MainActivity.this).sendBroadcast("action.receiverData", "receiverData");
            }
        }, 0, 3000);
    }

    @OnClick({R.id.mButtonShow})
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }
}