package com.pengxh.app.androidlib;

import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.widget.EasyToast;

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

    }

    @OnClick({R.id.mButtonShow})
    @Override
    public void onClick(View view) {
        EasyToast.showToast(TAG, EasyToast.SUCCESS);
    }
}