package com.pengxh.app.multilib.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pengxh.app.multilib.R;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO 自定义权限申请对话框
 * @date: 2020/2/18 14:55
 */
public class PermissionDialog extends AlertDialog implements View.OnClickListener {

    private static final String TAG = "PermissionDialog";
    private Context context;
    private onDialogClickListener dialogListener;
    private String[] permissionArrays;

    public PermissionDialog(Builder builder) {
        super(builder.mContext, R.style.PermissionDialog);
        this.context = builder.mContext;
        this.permissionArrays = builder.arrays;
        this.dialogListener = builder.listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission);
        initView();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initView() {
        ImageView mPermissionCancel = findViewById(R.id.permissionCancel);
        if (mPermissionCancel != null) {
            mPermissionCancel.setOnClickListener(this);
        }

        Button mPermissionBtn = findViewById(R.id.permissionBtn);
        if (mPermissionBtn != null) {
            mPermissionBtn.setOnClickListener(this);
        }

        RecyclerView mPermissionRecyclerView = findViewById(R.id.permissionList);
        PermissionAdapter adapter = new PermissionAdapter(context, permissionArrays);
        if (mPermissionRecyclerView != null) {
            mPermissionRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mPermissionRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.permissionBtn) {
            if (dialogListener != null) {
                dismiss();
                dialogListener.onButtonClick();
            }
        } else if (i == R.id.permissionCancel) {
            if (dialogListener != null) {
                dismiss();
                dialogListener.onCancelClick();
            }
        } else {
            Log.e(TAG, "onClick: Error view id = " + i);
        }
    }

    public static class Builder {
        private Context mContext;
        private String[] arrays;
        private onDialogClickListener listener;

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setPermission(String[] arrays) {
            this.arrays = arrays;
            return this;
        }

        public Builder setOnDialogClickListener(onDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public PermissionDialog build() {
            return new PermissionDialog(this);
        }
    }

    public interface onDialogClickListener {
        void onButtonClick();

        void onCancelClick();
    }
}