package com.pengxh.app.multilib.widget.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pengxh.app.multilib.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/2/18 16:03
 */
public class PermissionAdapter extends RecyclerView.Adapter {

    private static final String TAG = "PermissionAdapter";
    private static final String[] DANGEROUS_PERMISSIONS = {"android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS", "android.permission.READ_CONTACTS",
            "android.permission.READ_CALL_LOG", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.WRITE_CALL_LOG", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "com.android.voicemail.permission.ADD_VOICEMAIL",
            "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR",
            "android.permission.CAMERA",
            "android.permission.BODY_SENSORS",
            "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.RECORD_AUDIO",
            "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS", "android.permission.RECEIVE_SMS", "android.permission.SEND_SMS", "android.permission.READ_CELL_BROADCASTS"};
    private String[] mPermissionArrays;
    private LayoutInflater inflater;

    public PermissionAdapter(Context mContext, String[] arrays) {
        Set<String> dangerousPermissions = new HashSet<>();//set数据结构特性去重
        for (String permission : arrays) {
            boolean hasContain = Arrays.asList(DANGEROUS_PERMISSIONS).contains(permission);
            if (hasContain) {
                //剔除同组权限
                switch (permission) {
                    case "android.permission.WRITE_CONTACTS":
                    case "android.permission.GET_ACCOUNTS":
                    case "android.permission.READ_CONTACTS":
                        permission = "android.permission.WRITE_CONTACTS";
                        break;
                    case "android.permission.READ_CALL_LOG":
                    case "android.permission.READ_PHONE_STATE":
                    case "android.permission.CALL_PHONE":
                    case "android.permission.WRITE_CALL_LOG":
                    case "android.permission.USE_SIP":
                    case "android.permission.PROCESS_OUTGOING_CALLS":
                    case "com.android.voicemail.permission.ADD_VOICEMAIL":
                        permission = "android.permission.READ_PHONE_STATE";
                        break;
                    case "android.permission.READ_CALENDAR":
                    case "android.permission.WRITE_CALENDAR":
                        permission = "android.permission.READ_CALENDAR";
                        break;
                    case "android.permission.ACCESS_FINE_LOCATION":
                    case "android.permission.ACCESS_COARSE_LOCATION":
                        permission = "android.permission.ACCESS_FINE_LOCATION";
                        break;
                    case "android.permission.READ_EXTERNAL_STORAGE":
                    case "android.permission.WRITE_EXTERNAL_STORAGE":
                        permission = "android.permission.READ_EXTERNAL_STORAGE";
                        break;
                    case "android.permission.READ_SMS":
                    case "android.permission.RECEIVE_WAP_PUSH":
                    case "android.permission.RECEIVE_MMS":
                    case "android.permission.RECEIVE_SMS":
                    case "android.permission.SEND_SMS":
                    case "android.permission.READ_CELL_BROADCASTS":
                        permission = "android.permission.READ_SMS";
                        break;
                }
                dangerousPermissions.add(permission);
            } else {
                Log.d(TAG, "PermissionAdapter: 不是危险权限，无需关注");
            }
        }
        //new String[0]只是为了指定函数的形参数，最终返回的String[]的长度是由你的list存储内容的长度决定的
        this.mPermissionArrays = dangerousPermissions.toArray(new String[0]);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mPermissionArrays == null ? 0 : mPermissionArrays.length;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PermissionViewHolder(inflater.inflate(R.layout.item_permission_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PermissionViewHolder itemHolder = (PermissionViewHolder) holder;

        String permission = mPermissionArrays[position];
        if (permission.equals("android.permission.WRITE_CONTACTS")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_contacts);
            itemHolder.itemTitle.setText("通讯录");
            itemHolder.itemInfo.setText("允许应用访问联系人通讯录信息");
        }
        if (permission.equals("android.permission.READ_PHONE_STATE")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_phone);
            itemHolder.itemTitle.setText("电话状态");
            itemHolder.itemInfo.setText("允许应用访问电话状态");
        }
        if (permission.equals("android.permission.READ_CALENDAR")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_calendar);
            itemHolder.itemTitle.setText("日程信息");
            itemHolder.itemInfo.setText("允许应用读取用户的日程信息");
        }
        if (permission.equals("android.permission.CAMERA")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_camera);
            itemHolder.itemTitle.setText("相机相册");
            itemHolder.itemInfo.setText("允许应用访问摄像头进行拍照");
        }
        if (permission.equals("android.permission.BODY_SENSORS")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_sensor);
            itemHolder.itemTitle.setText("传感器");
            itemHolder.itemInfo.setText("允许应用获取传感器信息");
        }
        if (permission.equals("android.permission.ACCESS_FINE_LOCATION")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_location);
            itemHolder.itemTitle.setText("位置信息");
            itemHolder.itemInfo.setText("允许应用获取定位信息");
        }
        if (permission.equals("android.permission.READ_EXTERNAL_STORAGE")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_storage);
            itemHolder.itemTitle.setText("文件存储");
            itemHolder.itemInfo.setText("允许应用读取、写入外部存储");
        }
        if (permission.equals("android.permission.RECORD_AUDIO")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_microphone);
            itemHolder.itemTitle.setText("录制声音");
            itemHolder.itemInfo.setText("允许应用通过手机麦克录制声音");
        }
        if (permission.equals("android.permission.READ_SMS")) {
            itemHolder.itemImage.setImageResource(R.drawable.per_sms);
            itemHolder.itemTitle.setText("短信信息");
            itemHolder.itemInfo.setText("允许应用获取短信信息");
        }
    }

    private static class PermissionViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemInfo;

        private PermissionViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemInfo = itemView.findViewById(R.id.itemInfo);
        }
    }
}