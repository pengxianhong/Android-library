package com.pengxh.app.multilib.interfaces;

import java.util.List;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/4/22 15:05
 */
public interface ImageCompressListener {
    void onSuccess(List<String> result);

    void onFailure(Throwable t);
}