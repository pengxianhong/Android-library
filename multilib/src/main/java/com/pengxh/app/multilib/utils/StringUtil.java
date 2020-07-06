package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/11/16.
 */

public class StringUtil {

    private static final String TAG = "StringUtil";

    /**
     * 匹配中文符号
     *
     * @param str
     */
    public static String regExString(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>《》/?~！@#￥%……&*（）——+|{}【】'；：”“’。，、？]";
        Matcher matcher = Pattern.compile(regEx).matcher(str);
        return matcher.replaceAll("").trim();
    }

    /**
     * 判断输入的是否是数字
     */
    public static boolean isNumber(String str) {
        if (!str.isEmpty()) {
            String regExp = "^[-\\+]?[\\d]*$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(str);
            boolean isMatch = m.matches();
            if (!isMatch) {
                Log.w(TAG, "输入的是不是数字：" + str);
            }
            return isMatch;
        }
        return false;
    }

    /**
     * 匹配电话号码
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            Log.e(TAG, "手机号应为11位数：" + phoneNumber);
            return false;
        } else {
            String regExp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(phoneNumber);
            boolean isMatch = m.matches();
            if (!isMatch) {
                Log.e(TAG, "输入的手机号格式不对：" + phoneNumber);
            }
            return isMatch;
        }
    }

    /**
     * 匹配邮箱地址
     */
    public static boolean isEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            Log.e(TAG, "邮箱地址不能为空：" + email);
            return false;
        } else {
            String regExp = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(email);
            boolean isMatch = m.matches();
            if (!isMatch) {
                Log.e(TAG, "输入的邮箱地址格式不对：" + email);
            }
            return isMatch;
        }
    }

    /**
     * 获取本地Asserts文件内容
     */
    public static String getAssetsData(Context context, String fileName) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder data = new StringBuilder();
            String s;
            try {
                while ((s = bufferedReader.readLine()) != null) {
                    data.append(s);
                }
                Log.d(TAG, "getAssetsData: " + data);
                return data.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
