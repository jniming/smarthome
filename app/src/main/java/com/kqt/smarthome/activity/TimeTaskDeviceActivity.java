package com.kqt.smarthome.activity;

import com.kqt.smarthome.R;
import com.kqt.smarthome.db.DeviceDb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TimeTaskDeviceActivity extends BaseActivity implements
		OnClickListener {

	private FrameLayout timetask_layout, type, num, aciton;
	private TextView way, type_t, num_t, action_t;
	public int TYPECODE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_task_acitivity);
		setTitle("定时任务");
		setNaView(R.drawable.left_back, "", 0, "", 0, "", 0, "");
		timetask_layout = (FrameLayout) findViewById(R.id.timetask_layout);
		type = (FrameLayout) findViewById(R.id.time_type_layout);
		aciton = (FrameLayout) findViewById(R.id.time_action_layout);
		num = (FrameLayout) findViewById(R.id.time_num_layout);
		way = (TextView) findViewById(R.id.time_way);
		type_t = (TextView) findViewById(R.id.time_type_text);
		num_t = (TextView) findViewById(R.id.time_num_text);
		action_t = (TextView) findViewById(R.id.time_action_text);
	}

	@Override
	public void viewEvent(TitleBar titleBar, View v) {
		back();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == type.getId()) {
			Intent intent = new Intent();
			intent.setClass(this, TimeTaskSettingTypeActivity.class);
			startActivityForResult(intent, TYPECODE);
		} else if (v.getId() == type.getId()) {
		} else if (v.getId() == type.getId()) {
		} else if (v.getId() == type.getId()) {
		} else if (v.getId() == type.getId()) {
		}

	}
}
