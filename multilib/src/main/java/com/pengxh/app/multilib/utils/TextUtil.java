package com.pengxh.app.multilib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/11/16.
 */

public class TextUtil {
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
}
