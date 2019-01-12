package com.pengxh.app.androidlib;

import android.app.usage.NetworkStatsManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.NetworkStatsHelper;
import com.pengxh.app.multilib.utils.TextUtil;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends BaseNormalActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        mButton = (Button) findViewById(R.id.mButton);
    }

    @Override
    public void initEvent() {
        NetworkStatsHelper.hasPermissionToReadNetworkStatus(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkStatsManager statsManager = (NetworkStatsManager) getSystemService(NETWORK_STATS_SERVICE);
                //获取每天的流量统计
                if (statsManager == null) {
                    Log.e(TAG, "statsManager is null, please check it");
                } else {
//                    long dayBytesWifi = NetworkStatsHelper.getDayBytesWifi(statsManager);
                    long dayBytesMobile = NetworkStatsHelper.getDayBytesMobile(MainActivity.this, statsManager);
                    String Mobiledata = NetworkStatsHelper.transformDataFlow(dayBytesMobile);
                    TextUtil.showShortToast(MainActivity.this, "Total: " + Mobiledata + "MB");
                }
            }
        });
    }
}