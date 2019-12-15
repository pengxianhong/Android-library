package com.pengxh.app.androidlib;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.StringUtil;
import com.pengxh.app.multilib.widget.EasyToast;
import com.pengxh.app.multilib.widget.MultiSelectBean;
import com.pengxh.app.multilib.widget.MultiSelectDialog;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.mButton)
    Button mButton;

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

    @OnClick(R.id.mButton)
    @Override
    public void onClick(View view) {
        String data = StringUtil.getAssetsData(this, "testData.json");
        Log.d(TAG, "onClick: " + data);
        Type type = new TypeToken<List<MultiSelectBean>>() {
        }.getType();
        List<MultiSelectBean> mItemList = new Gson().fromJson(data, type);
        new MultiSelectDialog.Builder().setContext(this)
                .setTitle("选择设备")
                .setData(mItemList)
                .setNegativeButton("取消")
                .setPositiveButton("确定")
                .setOnDialogClickListener(new MultiDialogClickListener())
                .setCancelable(false)
                .build().show();
    }

    class MultiDialogClickListener implements MultiSelectDialog.onDialogClickListener {

        @Override
        public void onConfirmClick(Dialog dialog, List<String> list) {
            if (list == null) {
                EasyToast.showToast("什么都还没选中，无法添加！", EasyToast.ERROR);
            } else {
                EasyToast.showToast("当前选中: " + list, EasyToast.SUCCESS);
                dialog.dismiss();
            }
        }

        @Override
        public void onCancelClick(Dialog dialog) {
            dialog.dismiss();
        }
    }
}