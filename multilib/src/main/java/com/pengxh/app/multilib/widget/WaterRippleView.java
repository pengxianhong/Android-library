package com.pengxh.app.multilib.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 水波纹进度条
 */
public class WaterRippleView extends View {

    private static final String TAG = "WaterRippleView";

    public WaterRippleView(Context context) {
        this(context, null);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取到attrs属性
         * */

        initPaint();
        initEvent();
    }

    private void initPaint() {

    }

    private void initEvent() {

    }
}
