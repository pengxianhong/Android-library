package com.pengxh.app.multilib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgUtil {

    /**
     * 根据URL返回Bitmap对象
     * */
    public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        try {
            URL imgurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) imgurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
