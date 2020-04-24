package com.pengxh.app.multilib.utils;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/4/24 10:35
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static long getFileSize(File file) {
        long size = 0L;
        if (file == null) {
            return size;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileSize(f);
            } else {
                size += f.length();
            }
        }
        return size;
    }

    public static String formatFileSize(Long size) {
        String fileSize;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (size == null) {
            fileSize = df.format(0) + "B";
        } else if (size < 1024) {
            fileSize = df.format(size) + "B";
        } else if (size < 1048576) {
            fileSize = df.format(((double) size / 1024)) + "K";
        } else if (size < 1073741824) {
            fileSize = df.format(((double) size / 1048576)) + "M";
        } else {
            fileSize = df.format(((double) size / 1073741824)) + "G";
        }
        return fileSize;
    }

    //file：要删除的文件夹的所在位置
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        } else if (file.exists()) {
            file.delete();
        }
    }
}
