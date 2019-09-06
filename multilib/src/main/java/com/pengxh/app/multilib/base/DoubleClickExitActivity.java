package com.pengxh.app.multilib.base;

import android.view.KeyEvent;
import android.widget.Toast;

public abstract class DoubleClickExitActivity extends BaseNormalActivity {

    private long i_time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - i_time > 2000) {
                String msg = "再按一次退出程序";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                i_time = System.currentTimeMillis();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
