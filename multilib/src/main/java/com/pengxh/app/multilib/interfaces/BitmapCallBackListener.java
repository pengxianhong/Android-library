package com.pengxh.app.multilib.interfaces;

import android.graphics.Bitmap;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/2/26 17:14
 */
public interface BitmapCallBackListener {
    void onSuccess(Bitmap bitmap);

    void onFailure(Throwable t);
}
