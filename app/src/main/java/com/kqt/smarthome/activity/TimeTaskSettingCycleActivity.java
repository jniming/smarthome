package com.kqt.smarthome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kqt.smarthome.R;


public class TimeTaskSettingCycleActivity extends BaseActivity implements
        OnClickListener {

    private boolean[] ischose = new boolean[]{false, false, false, false, false, false};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeTaskCycleOnceLt.setVisibility(ischose[0] ? View.VISIBLE : View.GONE);
            timeTaskCycleHoursLt.setVisibility(ischose[1] ? View.VISIBLE : View.GONE);
            timeTaskCycleDayLt.setVisibility(ischose[2] ? View.VISIBLE : View.GONE);
            timeTaskCycleMouthLt.setVisibility(ischose[3] ? View.VISIBLE : View.GONE);
            timeTaskCycleYearLt.setVisibility(ischose[4] ? View.VISIBLE : View.GONE);
            timeTaskCycleWeekLt.setVisibility(ischose[5] ? View.VISIBLE : View.GONE);

        }
    };
    private FrameLayout timeTaskCycleOnce;
    private LinearLayout timeTaskCycleOnceLt;
    private FrameLayout timeTaskCycleHours;
    private LinearLayout timeTaskCycleHoursLt;
    private FrameLayout timeTaskCycleDay;
    private LinearLayout timeTaskCycleDayLt;
    private FrameLayout timeTaskCycleMouth;
    private LinearLayout timeTaskCycleMouthLt;
    private FrameLayout timeTaskCycleYear;
    private LinearLayout timeTaskCycleYearLt;
    private FrameLayout timeTaskCycleWeek;
    private LinearLayout timeTaskCycleWeekLt;

    private void assignViews() {
        timeTaskCycleOnce = (FrameLayout) findViewById(R.id.time_task_cycle_once);
        timeTaskCycleOnceLt = (LinearLayout) findViewById(R.id.time_task_cycle_once_lt);
        timeTaskCycleHours = (FrameLayout) findViewById(R.id.time_task_cycle_hours);
        timeTaskCycleHoursLt = (LinearLayout) findViewById(R.id.time_task_cycle_hours_lt);
        timeTaskCycleDay = (FrameLayout) findViewById(R.id.time_task_cycle_day);
        timeTaskCycleDayLt = (LinearLayout) findViewById(R.id.time_task_cycle_day_lt);
        timeTaskCycleMouth = (FrameLayout) findViewById(R.id.time_task_cycle_mouth);
        timeTaskCycleMouthLt = (LinearLayout) findViewById(R.id.time_task_cycle_mouth_lt);
        timeTaskCycleYear = (FrameLayout) findViewById(R.id.time_task_cycle_year);
        timeTaskCycleYearLt = (LinearLayout) findViewById(R.id.time_task_cycle_year_lt);
        timeTaskCycleWeek = (FrameLayout) findViewById(R.id.time_task_cycle_week);
        timeTaskCycleWeekLt = (LinearLayout) findViewById(R.id.time_task_cycle_week_lt);
        timeTaskCycleOnce.setOnClickListener(this);
        timeTaskCycleHours.setOnClickListener(this);
        timeTaskCycleDay.setOnClickListener(this);
        timeTaskCycleMouth.setOnClickListener(this);
        timeTaskCycleYear.setOnClickListener(this);
        timeTaskCycleWeek.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_task_setting_cycle);
        setTitle("定时任务");
        setNaView(R.drawable.left_back, "", 0, "", 0, "", 0, "完成");
        assignViews();
        handler.sendEmptyMessage(0);
    }

    @Override
    public void viewEvent(TitleBar titleBar, View v) {
        if (titleBar == TitleBar.RIGHT) {

        } else {

            back();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
    }
}
