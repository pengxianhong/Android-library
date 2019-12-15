package com.pengxh.app.multilib.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pengxh.app.multilib.R;

import java.util.List;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: 多选功能对话框。ListView和CheckBox焦点会冲突，抉择之下选择放弃ListView的焦点
 * @date: 2019/12/13 13:13
 */
public class MultiSelectDialog extends Dialog implements View.OnClickListener, MultiSelectAdapter.OnCheckedListener {

    private static final String TAG = "MultiSelectDialog";
    private Context context;
    private boolean cancelable;
    private onDialogClickListener listener;
    private String title;
    private List<MultiSelectBean> inputList;//需要绑定的数据集
    private List<String> outputList;//用户勾选多选框之后收集的数据
    private String positiveBtn;
    private String negativeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mutilselect);
        initView();
        setCanceledOnTouchOutside(false);//点击外部区域无法取消对话框
        this.setCancelable(cancelable);
    }

    private void initView() {
        TextView mDialogTitle = findViewById(R.id.multiDialogTitle);
        ListView mSelectListView = findViewById(R.id.mSelectListView);
        Button mDialogCancel = findViewById(R.id.multiDialogCancel);
        Button mDialogConfirm = findViewById(R.id.multiDialogConfirm);

        mDialogCancel.setOnClickListener(this);
        mDialogConfirm.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            mDialogTitle.setText(title);
        }
        if (!TextUtils.isEmpty(negativeBtn)) {
            mDialogCancel.setText(negativeBtn);
        }
        if (!TextUtils.isEmpty(positiveBtn)) {
            mDialogConfirm.setText(positiveBtn);
        }

        MultiSelectAdapter adapter = new MultiSelectAdapter(context, inputList);
        adapter.setOnCheckedListener(this);
        mSelectListView.setAdapter(adapter);
    }

    private MultiSelectDialog(Builder builder) {
        super(builder.mContext, R.style.MultiSelectDialog);
        this.context = builder.mContext;
        this.title = builder.title;
        this.inputList = builder.dataList;
        this.positiveBtn = builder.positiveBtn;
        this.negativeBtn = builder.negativeBtn;
        this.cancelable = builder.cancelable;
        this.listener = builder.listener;
    }

    public static class Builder {
        private Context mContext;
        private String title;//对话框标题
        private List<MultiSelectBean> dataList;//传入的数据
        private String positiveBtn;//确定按钮
        private String negativeBtn;//取消按钮
        private boolean cancelable;//对话框是否可取消
        private onDialogClickListener listener;//按钮监听

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setData(List<MultiSelectBean> list) {
            this.dataList = list;
            return this;
        }

        public Builder setPositiveButton(String name) {
            this.positiveBtn = name;
            return this;
        }

        public Builder setNegativeButton(String name) {
            this.negativeBtn = name;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnDialogClickListener(onDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public MultiSelectDialog build() {
            return new MultiSelectDialog(this);
        }
    }

    public interface onDialogClickListener {
        void onConfirmClick(Dialog dialog, List<String> list);

        void onCancelClick(Dialog dialog);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.multiDialogCancel) {
            if (listener != null) {
                listener.onCancelClick(this);
            }
        } else if (i == R.id.multiDialogConfirm) {
            if (listener != null) {
                Log.d(TAG, "onClick: " + outputList);
                listener.onConfirmClick(this, outputList);
            }
        } else {
            Log.e(TAG, "onClick: Error view ID");
        }
    }

    @Override
    public void getDataList(List<String> list) {
        this.outputList = list;
    }
}