package com.pengxh.app.androidlib;

import android.view.View;
import android.widget.Button;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.ColorUtil;
import com.pengxh.app.multilib.utils.ToastUtil;

public class MainActivity extends BaseNormalActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ToastUtil.init(this);
    }

    @Override
    public void initData() {
        mButton = findViewById(R.id.mButton);
    }

    @Override
    public void initEvent() {
        mButton.setBackgroundColor(ColorUtil.getRandomColor());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(TAG, ToastUtil.WARING);
            }
        });
    }
}