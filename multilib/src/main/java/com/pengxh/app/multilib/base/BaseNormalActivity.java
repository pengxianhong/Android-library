package com.pengxh.app.multilib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseNormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutView());
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    /**
     * 初始化xml布局
     */
    public abstract int initLayoutView();

    /**
     * 初始化默认数据
     */
    public abstract void initData();

    /**
     * 初始化业务逻辑
     */
    public abstract void initEvent();
}
