package com.pengxh.app.multilib.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: TODO 网络工具整合
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2021年1月12日16:39:31
 */
public class NetworkUtil {
    private static final String TAG = "NetworkUtil";

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
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 每秒ping一次目的IP
     */
    public static String ping(String address) {
        StringBuilder builder = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec("ping -c 1 -w 1 " + address);
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            String result = builder.toString();
            if (result.equals("")) {
                return null;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            runtime.gc();
        }
        return null;
    }
}
