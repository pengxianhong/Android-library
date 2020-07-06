package com.pengxh.app.androidlib;

import android.util.Log;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.DensityUtil;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    public int initLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: " + DensityUtil.px2dp(this, 90));
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}