package com.pengxh.app.androidlib;

import com.pengxh.app.multilib.base.BaseNormalActivity;

public class MainActivity extends BaseNormalActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {

    }

    @Override
    public void initEvent() {
        showShortToast("MainActivity");
        showProgress(this, "加载中，请稍后~");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    removeProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
