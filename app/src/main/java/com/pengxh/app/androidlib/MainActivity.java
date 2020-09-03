package com.pengxh.app.androidlib;

import android.app.Dialog;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.widget.EasyToast;
import com.pengxh.app.multilib.widget.dialog.InputDialog;

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

    }

    @OnClick({R.id.startView})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startView) {
            new InputDialog.Builder()
                    .setTitle("测试")
                    .setContext(this)
                    .setNegativeButton("取消")
                    .setPositiveButton("确定")
                    .setOutsideCancelable(false)
                    .setOnDialogClickListener(new InputDialog.DialogClickListener() {
                        @Override
                        public void onConfirmClick(Dialog dialog, String value) {
                            if (value.isEmpty()) {
                                EasyToast.showToast("什么都还没输入", EasyToast.WARING);
                            } else {
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelClick() {

                        }
                    })
                    .build()
                    .show();
        }
    }
}