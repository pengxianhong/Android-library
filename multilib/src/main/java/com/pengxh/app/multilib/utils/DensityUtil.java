package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import java.util.HashMap;

public class DensityUtil {

    public static float RATIO = 0.95F;//缩放比例值

    /**
     * 解决ScrollView嵌套另一个可滑动的View时，高度异常的问题
     */
    public static <T extends AbsListView> void measureViewHeight(Context mContext, T view) {
        ListAdapter adapter = view.getAdapter();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        View v;
        for (int i = 0; i < adapter.getCount(); i++) {
            v = adapter.getView(i, null, view);
            int i1 = View.MeasureSpec.makeMeasureSpec(getScreenWidth(mContext), View.MeasureSpec.EXACTLY);
            int i2 = View.MeasureSpec.makeMeasureSpec(i1, View.MeasureSpec.UNSPECIFIED);
            v.measure(i1, i2);
            totalHeight += v.getMeasuredHeight();
        }
        params.height = totalHeight + (view.getLayoutDirection() * (adapter.getCount() - 1));
        view.setLayoutParams(params);
    }

    /**
     * px 转 dp【按照一定的比例】
     */
    public static int px2dipRatio(Context context, float pxValue) {
        float scale = getScreenDendity(context) * RATIO;
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * dp转px【按照一定的比例】
     */
    public static int dip2pxRatio(Context context, float dpValue) {
        float scale = getScreenDendity(context) * RATIO;
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * px 转 dp
     * 48px - 16dp
     * 50px - 17dp
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = getScreenDendity(context);
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = getScreenDendity(context);
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * 获取屏幕的宽度（像素）
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;//1080
    }

    /**
     * 获取屏幕的宽度（dp）
     */
    public static int getScreenWidthDp(Context context) {
        float scale = getScreenDendity(context);
        return (int) (context.getResources().getDisplayMetrics().widthPixels / scale);//360
    }

    /**
     * 获取屏幕的高度（像素）
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;//1776
    }

    /**
     * 获取屏幕的高度（像素）
     */
    public static int getScreenHeightDp(Context context) {
        float scale = getScreenDendity(context);
        return (int) (context.getResources().getDisplayMetrics().heightPixels / scale);//592
    }

    /**
     * 屏幕密度比例
     */
    public static float getScreenDendity(Context context) {
        return context.getResources().getDisplayMetrics().density;//3
    }

    /**
     * 获取状态栏的高度 72px
     * http://www.2cto.com/kf/201501/374049.html
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            Object object = aClass.newInstance();
            int height = Integer.parseInt(aClass.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;

        //依赖于WMS(窗口管理服务的回调)【不建议使用】
		/*Rect outRect = new Rect();
		((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
		return outRect.top;*/
    }

    /**
     * 指定机型（displayMetrics.xdpi）下dp转px
     * 18dp - 50px
     */
    public static int dpToPx(Context context, int dp) {
        return Math.round(((float) dp * getPixelScaleFactor(context)));
    }

    /**
     * 指定机型（displayMetrics.xdpi）下px 转 dp
     * 50px - 18dp
     */
    public static int pxToDp(Context context, int px) {
        return Math.round(((float) px / getPixelScaleFactor(context)));
    }

    /**
     * 获取水平方向的dpi的密度比例值
     * 2.7653186
     */
    public static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / 160.0f);
    }

    public static HashMap<String, Integer> getDisplaySize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int displayWidthPx = dm.widthPixels;
        //TODO 手机纵向像素高度还需要加上底部导航栏高度
        int height = dm.heightPixels;
        Resources res = context.getResources();
        //获取导航栏
        int navigationBarId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        int navigationBarHeight = res.getDimensionPixelSize(navigationBarId);
        int displayHeightPx = height + navigationBarHeight;

        HashMap<String, Integer> displaySizeMap = new HashMap<>();
        displaySizeMap.put("widthPx", displayWidthPx);
        displaySizeMap.put("heightPx", displayHeightPx);
        return displaySizeMap;
    }
}
