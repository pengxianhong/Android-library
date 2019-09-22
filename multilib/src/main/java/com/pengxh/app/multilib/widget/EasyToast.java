package com.pengxh.app.multilib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pengxh.app.multilib.R;

public class EasyToast {
    private static final String TAG = "EasyToast";
    public static final int DEFAULT = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int WARING = 3;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        EasyToast.mContext = context.getApplicationContext();//获取全局上下文，最长生命周期
    }

    /**
     * 简单自定义Toast
     *
     * @param msg 消息体
     */
    public static void showToast(String msg, int toastStyle) {
        if (!TextUtils.isEmpty(msg)) {
            switch (toastStyle) {
                case DEFAULT:
                    View defaultView = LayoutInflater.from(mContext).inflate(R.layout.toast_default_style, null);
                    TextView defaultTextView = defaultView.findViewById(R.id.mToastMessage);
                    defaultTextView.setCompoundDrawablePadding(15);
                    defaultTextView.setTextSize(16.0f);
                    defaultTextView.setPadding(15, 10, 30, 10);
                    defaultTextView.setText(msg);

                    Toast defaultToast = new Toast(mContext);
                    defaultToast.setDuration(Toast.LENGTH_SHORT);
                    defaultToast.setView(defaultTextView);
                    defaultToast.show();
                    break;
                case SUCCESS:
                    View successView = LayoutInflater.from(mContext).inflate(R.layout.toast_success_style, null);
                    TextView successTextView = successView.findViewById(R.id.mToastMessage);
                    successTextView.setCompoundDrawablePadding(15);
                    successTextView.setTextSize(16.0f);
                    successTextView.setPadding(15, 10, 30, 10);
                    successTextView.setText(msg);

                    Toast successToast = new Toast(mContext);
                    successToast.setDuration(Toast.LENGTH_SHORT);
                    successToast.setView(successTextView);
                    successToast.show();
                    break;
                case ERROR:
                    View errorView = LayoutInflater.from(mContext).inflate(R.layout.toast_error_style, null);
                    TextView errorTextView = errorView.findViewById(R.id.mToastMessage);
                    errorTextView.setCompoundDrawablePadding(15);
                    errorTextView.setTextSize(16.0f);
                    errorTextView.setPadding(15, 10, 30, 10);
                    errorTextView.setText(msg);

                    Toast errorToast = new Toast(mContext);
                    errorToast.setDuration(Toast.LENGTH_SHORT);
                    errorToast.setView(errorTextView);
                    errorToast.show();
                    break;
                case WARING:
                    View warningView = LayoutInflater.from(mContext).inflate(R.layout.toast_waring_style, null);
                    TextView warningTextView = warningView.findViewById(R.id.mToastMessage);
                    warningTextView.setCompoundDrawablePadding(15);
                    warningTextView.setTextSize(16.0f);
                    warningTextView.setPadding(15, 10, 30, 10);
                    warningTextView.setText(msg);

                    Toast warningToast = new Toast(mContext);
                    warningToast.setDuration(Toast.LENGTH_SHORT);
                    warningToast.setView(warningTextView);
                    warningToast.show();
                    break;
            }
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }
}