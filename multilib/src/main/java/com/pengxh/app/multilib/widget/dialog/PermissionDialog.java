package com.pengxh.app.multilib.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private OnDialogClickListener dialogListener;
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
                dialogListener.onButtonClick();
            }
        } else if (i == R.id.permissionCancel) {
            if (dialogListener != null) {
                dialogListener.onCancelClick();
            }
        }
        dismiss();
    }

    public static class Builder {
        private Context mContext;
        private String[] arrays;
        private OnDialogClickListener listener;

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setPermissions(String[] arrays) {
            this.arrays = arrays;
            return this;
        }

        public Builder setOnDialogClickListener(OnDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public PermissionDialog build() {
            return new PermissionDialog(this);
        }
    }

    public interface OnDialogClickListener {
        void onButtonClick();

        void onCancelClick();
    }
}
