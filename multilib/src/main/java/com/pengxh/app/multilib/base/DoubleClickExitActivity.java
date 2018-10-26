package com.pengxh.app.multilib.base;

import android.view.KeyEvent;
import android.widget.Toast;

public abstract class DoubleClickExitActivity extends NormalActivity {

    private long i_time = 0;
    private int TIMESPACE = 2000;
    private String msg = "再按一次退出程序";

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - i_time > TIMESPACE) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                i_time = System.currentTimeMillis();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public long getI_time() {
        return i_time;
    }

    public void setI_time(long i_time) {
        this.i_time = i_time;
    }

    public int getTIMESPACE() {
        return TIMESPACE;
    }

    public void setTIMESPACE(int TIMESPACE) {
        this.TIMESPACE = TIMESPACE;
    }
}
