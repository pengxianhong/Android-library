package com.pengxh.app.multilib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
            Toast toast = new Toast(mContext);
            switch (toastStyle) {
                case DEFAULT:
                    View defaultView = LayoutInflater.from(mContext).inflate(R.layout.toast_default_style, null);

                    Drawable defaultDrawable = mContext.getResources().getDrawable(R.drawable.defaultt);
                    defaultDrawable.setBounds(0, 0, 96, 96);

                    TextView defaultTextView = defaultView.findViewById(R.id.mToastMessage);
                    defaultTextView.setCompoundDrawables(defaultDrawable, null, null, null);
                    defaultTextView.setCompoundDrawablePadding(15);
                    defaultTextView.setTextSize(16.0f);
                    defaultTextView.setPadding(15, 15, 30, 15);
                    defaultTextView.setText(msg);

                    toast.setView(defaultTextView);
                    break;
                case SUCCESS:
                    View successView = LayoutInflater.from(mContext).inflate(R.layout.toast_success_style, null);

                    Drawable successDrawable = mContext.getResources().getDrawable(R.drawable.right);
                    successDrawable.setBounds(0, 0, 96, 96);

                    TextView successTextView = successView.findViewById(R.id.mToastMessage);
                    successTextView.setCompoundDrawables(successDrawable, null, null, null);
                    successTextView.setCompoundDrawablePadding(15);
                    successTextView.setTextSize(16.0f);
                    successTextView.setPadding(15, 15, 30, 15);
                    successTextView.setText(msg);

                    toast.setView(successTextView);
                    break;
                case ERROR:
                    View errorView = LayoutInflater.from(mContext).inflate(R.layout.toast_error_style, null);

                    Drawable errorDrawable = mContext.getResources().getDrawable(R.drawable.error);
                    errorDrawable.setBounds(0, 0, 96, 96);

                    TextView errorTextView = errorView.findViewById(R.id.mToastMessage);
                    errorTextView.setCompoundDrawables(errorDrawable, null, null, null);
                    errorTextView.setCompoundDrawablePadding(15);
                    errorTextView.setTextSize(16.0f);
                    errorTextView.setPadding(15, 15, 30, 15);
                    errorTextView.setText(msg);

                    toast.setView(errorTextView);
                    break;
                case WARING:
                    View warningView = LayoutInflater.from(mContext).inflate(R.layout.toast_waring_style, null);

                    Drawable warningDrawable = mContext.getResources().getDrawable(R.drawable.waring);
                    warningDrawable.setBounds(0, 0, 96, 96);

                    TextView warningTextView = warningView.findViewById(R.id.mToastMessage);
                    warningTextView.setCompoundDrawables(warningDrawable, null, null, null);
                    warningTextView.setCompoundDrawablePadding(15);
                    warningTextView.setTextSize(16.0f);
                    warningTextView.setPadding(15, 15, 30, 15);
                    warningTextView.setText(msg);

                    toast.setView(warningTextView);
                    break;
            }
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Log.e(TAG, "msg must not be empty");
        }
    }
}