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

import com.accloud.cloudservice.AC;
import com.accloud.cloudservice.VoidCallback;
import com.accloud.service.ACDeviceMsg;
import com.accloud.service.ACException;
import com.kqt.smarthome.R;
import com.kqt.smarthome.entity.BoxMainSwitch;
import com.kqt.smarthome.entity.BoxShuntSwitch;
import com.kqt.smarthome.util.Config;
import com.kqt.smarthome.util.Ttoast;
import com.kqt.smarthome.util.Util;


public class TimeTaskDeviceActivity extends BaseActivity implements
        OnClickListener {
    private FrameLayout timetask_layout, type, aciton;
    private TextView way, type_t, action_t;
    public int TYPECODE = 20;
    private int typeId = 0;
    private int CYCLE = 30;

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
        if (titleBar == TitleBar.RIGHT) {
            String name = Util.GetUser(this).getName();
            String timePoint = "";
            String timeCycle = "";
            if (timePoint.isEmpty()) {
                Ttoast.show(this, "时间未选定");
                return;
            } else if (timeCycle.isEmpty()) {
                Ttoast.show(this, "循环方式未指定");
                return;
            }
            ACDeviceMsg msg = new ACDeviceMsg();
            msg.setCode(100);
            if (typeId == Config.leakageProtection) {  //漏保
                BoxMainSwitch boxMainSwitch = new BoxMainSwitch(this);
                msg.setContent(boxMainSwitch);
            } else if (typeId == Config.splitter) {    //分路
                BoxShuntSwitch boxShuntSwitch = new BoxShuntSwitch(this);
                msg.setContent(boxShuntSwitch);

            }
            createTimeTask(name, timePoint, timeCycle, msg);

        } else {

            back();
        }
    }

    /**
     * 创建定时任务
     */
    private void createTimeTask(String name, String timePoint, String timeCycle, ACDeviceMsg msg) {
        String deviceId = BoxSettingActivity.device.getDeviceid() + "";
        long id = 0;
        //注:这里与文档有些许不同
        AC.timerMgr().addTask(id, deviceId, name, timePoint, timeCycle, msg, new VoidCallback() {
            @Override
            public void success() {
                Ttoast.show(TimeTaskDeviceActivity.this, "添加成功");
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            public void error(ACException e) {
                Ttoast.show(TimeTaskDeviceActivity.this, "添加失败");
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TYPECODE && resultCode == Activity.RESULT_OK) {
            String info = data.getStringExtra("infomation");
            typeId = data.getIntExtra("typei", 0);
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
            Intent intent = new Intent();
            intent.setClass(this, TimeTaskSettingCycleActivity.class);
            startActivityForResult(intent, CYCLE);

        } else if (v.getId() == aciton.getId()) {

        }
    }
}
