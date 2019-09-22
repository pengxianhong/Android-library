package com.pengxh.app.androidlib;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.widget.EasyToast;
import com.pengxh.app.multilib.widget.InputDialog;

import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity
        implements View.OnClickListener, InputDialog.onDialogClickListener {

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
        new InputDialog.Builder()
                .setContext(this)
                .setTitle("提示")
                .setNegativeButton("取消")
                .setPositiveButton("确定")
                .setCancelable(false)
                .setOnDialogClickListener(this)
                .build().show();
    }

    @OnClick({R.id.mButtonShow})
    @Override
    public void onClick(View view) {
        EasyToast.showToast(TAG, EasyToast.SUCCESS);
    }

    @Override
    public void onConfirmClick(Dialog dialog, String input) {
        if (TextUtils.isEmpty(input)) {
            EasyToast.showToast("啥都还没输入呢", EasyToast.WARING);
        } else {
            EasyToast.showToast(input, EasyToast.SUCCESS);
            dialog.dismiss();
        }
    }

    @Override
    public void onCancelClick(Dialog dialog) {

    }
}