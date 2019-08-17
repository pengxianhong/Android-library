package com.pengxh.app.androidlib;

import android.view.View;
import android.widget.Button;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.SaveKeyValues;
import com.pengxh.app.multilib.utils.ToastUtil;

public class MainActivity extends BaseNormalActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ToastUtil.init(this);
        SaveKeyValues.initSharedPreferences(this);
    }

    @Override
    public void init() {
        mButton = (Button) findViewById(R.id.mButton);
    }

    @Override
    public void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveKeyValues.putValue("test", "key", "SaveKeyValues");
                ToastUtil.showBeautifulToast("OK", ToastUtil.SUCCESS);
            }
        });
    }
}