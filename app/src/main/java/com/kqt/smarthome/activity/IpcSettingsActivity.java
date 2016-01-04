package com.kqt.smarthome.activity;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.kqt.smarthome.R;
import com.kqt.smarthome.adpter.IpcSettingAdpter;
import com.kqt.smarthome.entity.IpcDevice;
import com.kqt.smarthome.util.Util;

public class IpcSettingsActivity extends BaseActivity implements
		OnClickListener {
	private ListView listview;
	private TextView title_left;
	private List<HashMap<String, String>> list = null;
	private IpcSettingAdpter adpter;
	private IpcDevice device;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
		setNaView(R.drawable.left_back, "", 0, "", 0, "", 0, "");
		setTitle("设置页面");
		initData();
		initView();

	}

	private void initData() {
		list = Util.SettingData();
		device = (IpcDevice) getIntent().getSerializableExtra("device");
	}

	private void initView() {
		listview = (ListView) findViewById(R.id.setting_listview);
		adpter = new IpcSettingAdpter(this, list, device);
		listview.setAdapter(adpter);
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void viewEvent(TitleBar titleBar, View v) {
		finish();
	}

}
