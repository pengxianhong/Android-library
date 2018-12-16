package com.pengxh.app.androidlib;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.TextUtil;
import com.pengxh.app.multilib.widget.VerificationCodeView;


public class MainActivity extends BaseNormalActivity {

    private VerificationCodeView mVerificationCodeView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        mVerificationCodeView = (VerificationCodeView) findViewById(R.id.mVerificationCodeView);
    }

    @Override
    public void initEvent() {
        mVerificationCodeView.setOnCodeChangedListenser(new VerificationCodeView.OnCodeChangedListenser() {
            @Override
            public void getCode(String code) {
                TextUtil.showShortToast(MainActivity.this, code);
            }
        });
    }
}
