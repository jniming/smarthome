package com.kqt.smarthome.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accloud.cloudservice.PayloadCallback;
import com.accloud.service.ACException;
import com.accloud.service.ACMsg;
import com.accloud.service.ACObject;
import com.kqt.smarthome.R;
import com.kqt.smarthome.entity.BoxManager;

import java.util.ArrayList;
import java.util.List;

public class TimeTaskSettingTypeActivity extends BaseActivity implements
        OnClickListener {

    private FrameLayout lb, fl;
    private LinearLayout lc, fc;
    private boolean lb_sel = true, fl_sel = false;
    private CustomAdpter adpter;
    private List<String> list = new ArrayList<String>();
    private Handler hanlder = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            lc.setVisibility(lb_sel ? View.VISIBLE : View.GONE);
            fl.setVisibility(fl_sel ? View.VISIBLE : View.GONE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetask_setting_type);
        setTitle("设备选择");
        setNaView(R.drawable.left_back, "", 0, "", 0, "", 0, "");
        lb = (FrameLayout) findViewById(R.id.timetask_lb_layout);
        fl = (FrameLayout) findViewById(R.id.timetask_fl_layout);
        lc = (LinearLayout) findViewById(R.id.timetask_lb_ischose);
        fc = (LinearLayout) findViewById(R.id.timetask_fl_ischose);

        lb.setOnClickListener(this);
        fl.setOnClickListener(this);
        hanlder.sendEmptyMessage(0);
    }

    @Override
    public void viewEvent(TitleBar titleBar, View v) {
        back();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == lb.getId()) {
            lb_sel = true;
            fl_sel = false;
            hanlder.sendEmptyMessage(0);
        } else if (v.getId() == fl.getId()) {
            lb_sel = false;
            fl_sel = true;
            hanlder.sendEmptyMessage(0);
        }
    }

    class CustomAdpter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (list == null || list.size() == 0) {
                return 0;
            }
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(TimeTaskSettingTypeActivity.this)
                    .inflate(R.layout.timetask_type_item, null);
            TextView view = (TextView) convertView.findViewById(R.id.timetask_item_text);

            return convertView;
        }

    }

    private void queryPower() {
        BoxManager.getintence().queryChannelDivideStateInfo(BoxSettingActivity.device.getDeviceid(), 0,
                new PayloadCallback<ACMsg>() {
                    @Override
                    public void error(ACException arg0) {
                        Log.d("error", arg0.getErrorCode() + "");
                    }
                    @Override
                    public void success(ACMsg arg0) {
                        ACObject acObject = arg0.get("stateInfo");
                        long deviceId = acObject.getLong("deviceId");
                        List<ACObject> list_ac = acObject.getList("value");
                        for (int i = 0; i < list_ac.size(); i++) {
                            ACObject li = list_ac.get(i);
                            String lineName = li.getString("lineName");
                            list.add(lineName);
                        }

                    }
                });
    }


}
