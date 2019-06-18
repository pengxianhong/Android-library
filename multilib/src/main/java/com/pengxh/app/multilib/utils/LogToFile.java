package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将Log日志写入文件中
 */
public class LogToFile {
    private static String logPath = null;//log日志存放路径
    private static SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    private static Date date = new Date();//因为log日志是使用日期命名的，使用静态成员变量主要是为了在整个程序运行期间只存在一个.log文件中;

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param mContext    上下文
     * @param logFileName log文件夹名称
     */
    public static void init(Context mContext, String logFileName) {
        logPath = getFilePath(mContext) + "/" + logFileName + "-Logs";//获得文件储存路径,在后面加"/Logs"建立子文件夹
    }

    /**
     * 获得文件存储路径
     *
     * @return
     */
    private static String getFilePath(Context mContext) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {//如果外部储存可用
            return mContext.getExternalFilesDir(null).getPath();//获得外部存储路径,默认路径为 /storage/emulated/0/Android/data/com.waka.workspace.logtofile/files/Logs/log_2016-03-14_16-15-09.log
        } else {
            return mContext.getFilesDir().getPath();//直接存在/data/data里，非root手机看不到
        }
    }

    private static final char VERBOSE = 'v';

    private static final char DEBUG = 'd';

    private static final char INFO = 'i';

    private static final char WARN = 'w';

    private static final char ERROR = 'e';

    public static void v(String tag, String msg) {
        writeToFile(VERBOSE, tag, msg, null);
    }

    public static void d(String tag, String msg) {
        writeToFile(DEBUG, tag, msg, null);
    }

    public static void i(String tag, String msg) {
        writeToFile(INFO, tag, msg, null);
    }

    public static void w(String tag, String msg) {
        writeToFile(WARN, tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable e) {
        writeToFile(ERROR, tag, msg, e);
    }

    /**
     * 将log信息写入文件中
     *
     * @param type
     * @param tag
     * @param msgObj
     */
    private static void writeToFile(char type, String tag, String msgObj, Throwable tr) {
        if (null == logPath) {
            Log.e(tag, "logPath == null ，未初始化LogToFile");
            return;
        }

        String fileName = logPath + "/" + mFormatter.format(new Date()) + ".log";//log日志名，使用时间命名，保证不重复
        String msg;
        StringBuilder builder = new StringBuilder().append(mFormatter.format(date)).append(getFunctionName());
        if (msgObj == null) {
            msg = "";
        } else {
            msg = msgObj.toString();
        }
        if (!TextUtils.isEmpty(msg)) {
            builder.append(msg).append('\n');
        }
        if (tr != null) {
            builder.append('\n').append(Log.getStackTraceString(tr));
        }

        //如果父路径不存在
        File file = new File(logPath);

        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        String log = mFormatter.format(date) + " " + type + " " + tag + " " + msg + "\n";//log日志内容，可以自行定制
        FileOutputStream fos;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，false为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String getFunctionName() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements == null) {
            return "";
        }
        for (StackTraceElement ste : elements) {
            if (ste.isNativeMethod()) {
                continue;
            }
            if (ste.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (ste.getClassName().equals(LogToFile.class.getName())) {
                continue;
            }
            return "  " + ste.getFileName().substring(0, ste.getFileName().indexOf(".")) + "." + ste.getMethodName()
                    + " (" + ste.getFileName() + ":" + ste.getLineNumber() + ") ";
        }
        return "";
    }
}