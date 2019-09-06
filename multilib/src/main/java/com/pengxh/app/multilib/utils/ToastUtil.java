package com.pengxh.app.multilib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pengxh.app.multilib.R;

public class ToastUtil {
    private static final String TAG = "ToastUtil";
    public static final int DEFAULT = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int WARING = 3;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        ToastUtil.mContext = context.getApplicationContext();//获取全局上下文，最长生命周期
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
                    View defautView = LayoutInflater.from(mContext).inflate(R.layout.toast_default_style, null);
                    TextView defautTextView = defautView.findViewById(R.id.mToastMessage);
                    defautTextView.setTextSize(18.0f);
                    defautTextView.setCompoundDrawablePadding(20);
                    defautTextView.setPadding(50, 10, 50, 10);
                    defautTextView.setText(msg);

                    Toast defautToast = new Toast(mContext);
                    defautToast.setDuration(Toast.LENGTH_LONG);
                    defautToast.setView(defautTextView);
                    defautToast.show();
                    break;
                case SUCCESS:
                    View successView = LayoutInflater.from(mContext).inflate(R.layout.toast_success_style, null);
                    TextView successTextView = successView.findViewById(R.id.mToastMessage);
                    successTextView.setTextSize(18.0f);
                    successTextView.setCompoundDrawablePadding(20);
                    successTextView.setPadding(50, 10, 50, 10);
                    successTextView.setText(msg);

                    Toast successToast = new Toast(mContext);
                    successToast.setDuration(Toast.LENGTH_LONG);
                    successToast.setView(successTextView);
                    successToast.show();
                    break;
                case ERROR:
                    View errorView = LayoutInflater.from(mContext).inflate(R.layout.toast_error_style, null);
                    TextView errorTextView = errorView.findViewById(R.id.mToastMessage);
                    errorTextView.setTextSize(18.0f);
                    errorTextView.setCompoundDrawablePadding(20);
                    errorTextView.setPadding(50, 10, 50, 10);
                    errorTextView.setText(msg);

                    Toast errorToast = new Toast(mContext);
                    errorToast.setDuration(Toast.LENGTH_LONG);
                    errorToast.setView(errorTextView);
                    errorToast.show();
                    break;
                case WARING:
                    View warningView = LayoutInflater.from(mContext).inflate(R.layout.toast_waring_style, null);
                    TextView warningTextView = warningView.findViewById(R.id.mToastMessage);
                    warningTextView.setTextSize(18.0f);
                    warningTextView.setCompoundDrawablePadding(20);
                    warningTextView.setPadding(50, 10, 50, 10);
                    warningTextView.setText(msg);

                    Toast warningToast = new Toast(mContext);
                    warningToast.setDuration(Toast.LENGTH_LONG);
                    warningToast.setView(warningTextView);
                    warningToast.show();
                    break;
            }
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }
}