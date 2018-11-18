package com.pengxh.app.multilib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseNormalActivity extends AppCompatActivity {

    private final String TAG = "BaseNormalActivity";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        windowFeature();
        initView();
        init();
        initEvent();
        bundleInOnCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    private void windowFeature() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void showProgress(Context mContext, String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);//设置点击不消失
        }
        if (progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        } else {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void removeProgress() {
        if (progressDialog == null) {
            return;
        }
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public abstract void initView();

    public abstract void init();

    public abstract void initEvent();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void bundleInOnCreate(Bundle savedInstanceState) {

    }
}
