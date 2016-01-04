package com.kqt.smarthome.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kqt.smarthome.R;


public class TimeTaskDeviceActivity extends BaseActivity implements
        OnClickListener {

    private FrameLayout timetask_layout, type, aciton;
    private TextView way, type_t, action_t;
    public int TYPECODE = 20;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String info = (String) msg.obj;
                type_t.setText(info);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_task_acitivity);
        setTitle("定时任务");
        setNaView(R.drawable.left_back, "", 0, "", 0, "", 0, "完成");
        timetask_layout = (FrameLayout) findViewById(R.id.timetask_layout);
        type = (FrameLayout) findViewById(R.id.time_type_layout);
        aciton = (FrameLayout) findViewById(R.id.time_action_layout);
        way = (TextView) findViewById(R.id.time_way);
        type_t = (TextView) findViewById(R.id.timetask_type_info);
        action_t = (TextView) findViewById(R.id.time_action_text);

        timetask_layout.setOnClickListener(this);
        type.setOnClickListener(this);
        aciton.setOnClickListener(this);


    }

    @Override
    public void viewEvent(TitleBar titleBar, View v) {


        back();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TYPECODE && resultCode == Activity.RESULT_OK) {
            String info = data.getStringExtra("infomation");
            Message msg = handler.obtainMessage();
            msg.what = 0;
            msg.obj = info;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == type.getId()) {
            Intent intent = new Intent();
            intent.setClass(this, TimeTaskSettingTypeActivity.class);
            startActivityForResult(intent, TYPECODE);
        } else if (v.getId() == timetask_layout.getId()) {


        } else if (v.getId() == aciton.getId()) {

        }
    }
}
