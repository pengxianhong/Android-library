package com.pengxh.app.multilib.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pengxh.app.multilib.R;

public class InputDialog extends Dialog implements View.OnClickListener {

    private static final String TAG = "InputDialog";
    private String title;
    private String positiveBtn;
    private String negativeBtn;
    private boolean cancelable;
    private OnDialogClickListener listener;
    private EditText mInputMessage;

    private InputDialog(Builder builder) {
        super(builder.mContext, R.style.InputDialog);
        this.title = builder.title;
        this.positiveBtn = builder.positiveBtn;
        this.negativeBtn = builder.negativeBtn;
        this.cancelable = builder.cancelable;
        this.listener = builder.listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);
        initView();
        this.setCanceledOnTouchOutside(cancelable);
    }

    private void initView() {
        TextView mDialogTitle = findViewById(R.id.mDialogTitle);
        mInputMessage = findViewById(R.id.mInputMessage);
        TextView mDialogCancel = findViewById(R.id.mDialogCancel);
        mDialogCancel.setOnClickListener(this);
        TextView mDialogConfirm = findViewById(R.id.mDialogConfirm);
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
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.mDialogCancel) {
            if (listener != null) {
                listener.onCancelClick();
            }
        } else if (i == R.id.mDialogConfirm) {
            if (listener != null) {
                String inputValue = mInputMessage.getText().toString().trim();
                Log.d(TAG, "onClick: " + inputValue);
                listener.onConfirmClick(inputValue);
            }
        }
        this.dismiss();
    }

    public static class Builder {
        private Context mContext;
        private String title;
        private String positiveBtn;
        private String negativeBtn;
        private boolean cancelable;
        private OnDialogClickListener listener;

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
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

        public Builder setOutsideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnDialogClickListener(OnDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public InputDialog build() {
            return new InputDialog(this);
        }
    }

    public interface OnDialogClickListener {
        void onConfirmClick(String value);

        void onCancelClick();
    }
}
