package com.pengxh.app.androidlib;

import android.app.Dialog;
import android.util.Log;
import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.widget.EasyToast;
import com.pengxh.app.multilib.widget.dialog.MultiSelectBean;
import com.pengxh.app.multilib.widget.dialog.MultiSelectDialog;

import java.util.ArrayList;
import java.util.List;

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
            new MultiSelectDialog.Builder()
                    .setContext(this)
                    .setTitle("请选择")
                    .setData(getData())
                    .setOutsideCancelable(false)
                    .setNegativeButton("取消")
                    .setPositiveButton("确定")
                    .setOnDialogClickListener(new MultiSelectDialog.OnDialogClickListener() {
                        @Override
                        public void onConfirmClick(Dialog dialog, List<String> list) {
                            if (list == null || list.size() == 0) {
                                EasyToast.showToast("至少需要选中一条数据", EasyToast.WARING);
                            } else {
                                Log.d(TAG, "onConfirmClick: " + list);
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelClick() {

                        }
                    })
                    .build().show();
        }
    }

    private List<MultiSelectBean> getData() {
        List<MultiSelectBean> beanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultiSelectBean bean = new MultiSelectBean();
            bean.setPicture("https://img.zcool.cn/community/014f95573484436ac72580edfb370b.png@1280w_1l_2o_100sh.png");
            bean.setTips("设备" + i);
            bean.setDescription("11201909000" + i);
            beanList.add(bean);
        }
        return beanList;
    }
}