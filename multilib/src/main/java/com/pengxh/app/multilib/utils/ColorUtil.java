package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

public class ColorUtil {
    /**
     * 随机颜色
     */
    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    /**
     * 获取xml颜色值
     */
    public static int getResourcesColor(Context context, int res) {
        return context.getResources().getColor(res);
    }
}
