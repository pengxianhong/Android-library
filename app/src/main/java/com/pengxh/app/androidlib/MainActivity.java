package com.pengxh.app.androidlib;

import android.util.Log;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.widget.KeyBoardView;

import butterknife.BindView;

public class MainActivity extends BaseNormalActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.keyboardView)
    KeyBoardView keyboardView;


    @Override
    public int initLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        keyboardView.setKeyboardClickListener(new KeyBoardView.KeyboardClickListener() {
            @Override
            public void onClick(String number) {
                Log.d(TAG, "onClick: " + number);
            }

            @Override
            public void onDelete() {

            }
        });
    }

    @Override
    public void initEvent() {

    }
}