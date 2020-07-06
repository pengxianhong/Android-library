package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import java.util.HashMap;

public class DensityUtil {

    /**
     * 解决ScrollView嵌套另一个可滑动的View时，高度异常的问题
     */
    public static <T extends AbsListView> void measureViewHeight(Context context, T view) {
        ListAdapter adapter = view.getAdapter();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        View v;
        for (int i = 0; i < adapter.getCount(); i++) {
            v = adapter.getView(i, null, view);
            Integer horizontalPixels = getDisplaySize(context).get("HorizontalPixels");
            int i1 = View.MeasureSpec.makeMeasureSpec(horizontalPixels, View.MeasureSpec.EXACTLY);
            int i2 = View.MeasureSpec.makeMeasureSpec(i1, View.MeasureSpec.UNSPECIFIED);
            v.measure(i1, i2);
            totalHeight += v.getMeasuredHeight();
        }
        params.height = totalHeight + (view.getLayoutDirection() * (adapter.getCount() - 1));
        view.setLayoutParams(params);
    }

    /**
     * 获取设备横向和纵向像素，以及dpi
     */
    public static HashMap<String, Integer> getDisplaySize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int displayWidthPx = dm.widthPixels;
        // 手机纵向像素高度还需要加上底部导航栏高度
        int height = dm.heightPixels;
        Resources res = context.getResources();
        //获取导航栏
        int navigationBarId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        int navigationBarHeight = res.getDimensionPixelSize(navigationBarId);
        int displayHeightPx = height + navigationBarHeight;
        //获取手机dpi
        int displayDpi = dm.densityDpi;
        HashMap<String, Integer> displaySizeMap = new HashMap<>();
        displaySizeMap.put("HorizontalPixels", displayWidthPx);
        displaySizeMap.put("VerticalPixels", displayHeightPx);
        displaySizeMap.put("PixelDensity", displayDpi);
        return displaySizeMap;
    }

    /**
     * 获取屏幕密度
     * <p>
     * Dpi（dots per inch 像素密度）
     * Density 密度
     */
    public static float getScreenDensity(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    /**
     * px转dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = getScreenDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
