package com.pengxh.app.androidlib;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.pengxh.app.multilib.widget.EasyToast;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EasyToast.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
