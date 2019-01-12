package com.pengxh.app.multilib.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

/**
 * NetworkStatsManager是Android 6.0（API 23）中新增加的类，提供网络使用历史统计信息
 * 不管是接收还是发出的都会计入流量，所以RxByte（接收字节数 ）和TxByte（发送字节数 ）都算流量
 */
@TargetApi(Build.VERSION_CODES.M)
public class NetworkStatsHelper {

    private static final String TAG = "NetworkStatsHelper";
    private static final int requestCode = 999;

    /**
     * 引导用户授予应用获取系统权限
     */
    public static void hasPermissionToReadNetworkStatus(Activity mActivity) {
        int permissionCheck = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, requestCode);
        }
        final AppOpsManager appOps = (AppOpsManager) mActivity.getSystemService(Context.APP_OPS_SERVICE);
        if (appOps == null) {
            Log.e(TAG, "appOps is null, please check it");
        } else {
            int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), mActivity.getPackageName());
            if (mode == AppOpsManager.MODE_ALLOWED) {
                return;
            }
        }
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        mActivity.startActivity(intent);
    }

    /**
     * 本机使用的 WIFI 总流量
     */
    public static long getAllBytesWifi(NetworkStatsManager statsManager) {
        NetworkStats.Bucket bucket;
        try {
            bucket = statsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI,
                    "",
                    0,
                    System.currentTimeMillis());
        } catch (RemoteException e) {
            return -1;
        }
        //这里可以区分发送和接收
        return bucket.getTxBytes() + bucket.getRxBytes();
    }

    /**
     * 本机使用的 WIFI 当天流量
     */
    public static long getDayBytesWifi(NetworkStatsManager statsManager) {
        NetworkStats.Bucket bucket;
        try {
            bucket = statsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI,
                    "",
                    getMorningTime(),
                    System.currentTimeMillis());
        } catch (RemoteException e) {
            return -1;
        }
        //这里可以区分发送和接收
        return bucket.getTxBytes() + bucket.getRxBytes();
    }

    /**
     * 本机使用的 mobile 总流量
     */
    public static long getAllBytesMobile(Context context, NetworkStatsManager statsManager) {
        NetworkStats.Bucket bucket;
        try {
            bucket = statsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE,
                    getSubscriberId(context, ConnectivityManager.TYPE_MOBILE),
                    0,
                    System.currentTimeMillis());
        } catch (RemoteException e) {
            return -1;
        }
        //这里可以区分发送和接收
        return bucket.getTxBytes() + bucket.getRxBytes();
    }

    /**
     * 本机使用的 mobile 当天流量
     */
    public static long getDayBytesMobile(Context context, NetworkStatsManager statsManager) {
        NetworkStats.Bucket bucket;
        try {
            bucket = statsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE,
                    getSubscriberId(context, ConnectivityManager.TYPE_MOBILE),
                    getMorningTime(),
                    System.currentTimeMillis());
        } catch (RemoteException e) {
            return -1;
        }
        //这里可以区分发送和接收
        return bucket.getTxBytes() + bucket.getRxBytes();
    }

    @SuppressLint("MissingPermission")
    private static String getSubscriberId(Context context, int networkType) {
        if (ConnectivityManager.TYPE_MOBILE == networkType) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSubscriberId();
        }
        return "";
    }

    /**
     * 获取当天的零点时间
     *
     * @return
     */
    public static long getMorningTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis());
    }

    /**
     * @param totalNetData
     * @return 精确计算
     */
    public static String transformDataFlow(long totalNetData) {
        BigDecimal bigDecimal, divisor;
        if (totalNetData == 0) {
            Log.w(TAG, "totalNetData is 0 ,may is wrong");
        }
        Log.d(TAG, "totalNetData is " + totalNetData);

        divisor = new BigDecimal(1024 * 1024);
        bigDecimal = new BigDecimal(totalNetData);
        //保留两位有效数字
        BigDecimal divide = bigDecimal.divide(divisor, 2, RoundingMode.HALF_UP);
        return String.valueOf(divide.doubleValue());
    }
}