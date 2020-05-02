package com.pengxh.app.multilib.utils;

import android.util.Log;

import java.io.IOException;

/**
 * @description: TODO ping网络
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2019/12/29 15:05
 */
public class PingUtil {
    private static final String TAG = "PingUtil";

    /**
     * 专网判断
     * <p>
     * -c 1是指ping的次数为1次
     * -w 5是指每次ping 5秒
     */
    public static boolean hasAccessNetwork(String host) {
        if (!host.isEmpty()) {
            try {
                Process process = Runtime.getRuntime().exec("ping -c 1 -w 5 " + host);
                int status = process.waitFor();
                Log.d(TAG, "hasAccessNetwork: status = " + status);
                return status == 0;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}