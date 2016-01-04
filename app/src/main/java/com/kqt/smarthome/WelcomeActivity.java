package com.kqt.smarthome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.kqt.smarthome.activity.LoginActivity;
import com.kqt.smarthome.db.DeviceDb;

public class WelcomeActivity extends Activity {

    private Handler hanlder = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            Intent intent = new Intent(WelcomeActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        init();
    }

    /**
     * 初始化一些数据
     */
    public void init() {
        DeviceDb db = new DeviceDb(this);
        hanlder.sendEmptyMessageDelayed(0, 1500);
    }

}
