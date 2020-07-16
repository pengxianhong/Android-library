package com.pengxh.app.androidlib;

import android.content.Intent;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;

import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    public int initLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        startService(new Intent(this, KeepAliveService.class));
    }

    @OnClick({R.id.startView, R.id.stopView})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startView:

                break;
        }
    }
}