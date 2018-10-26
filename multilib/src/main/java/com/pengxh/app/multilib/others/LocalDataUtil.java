package com.pengxh.app.multilib.others;

import android.app.Activity;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018/3/17.
 */

public class LocalDataUtil {

    public static String getStringFromAssets(Activity activity, String filename) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = activity.getAssets();
            //通过管理器打开文件并读取
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    assetManager.open(filename)));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
