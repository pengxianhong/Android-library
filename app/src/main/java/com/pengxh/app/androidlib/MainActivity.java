package com.pengxh.app.androidlib;

import android.view.View;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.SaveKeyValues;
import com.pengxh.app.multilib.widget.EasyToast;
import com.pengxh.app.multilib.widget.EditTextWithDelete;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.mEditTextWithDelete)
    EditTextWithDelete mEditTextWithDelete;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        SaveKeyValues.putValue("tag", "MainActivity");
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.mButtonShow})
    @Override
    public void onClick(View view) {
        mEditTextWithDelete.setText(SaveKeyValues.getValue("tag", "").toString());
        String trim = mEditTextWithDelete.getText().toString().trim();
        EasyToast.showToast(trim, EasyToast.SUCCESS);
    }
}