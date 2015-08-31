package com.example.garyda.weather;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTabHost tabhost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        //今日天氣
        tabhost.addTab(tabhost.newTabSpec("TodayWeatherFragment").setIndicator("TodayWeatherFragment"),TodayWeatherFragment.class,null);
        //一週天氣
        tabhost.addTab(tabhost.newTabSpec("WeekReportFragment").setIndicator("WeekReportFragment"),WeekReportFragment.class,null);
        //特報
        tabhost.addTab(tabhost.newTabSpec("SpecialReportFragment").setIndicator("SpecialReportFragment"),SpecialReportFragment.class,null);
        //設定
        tabhost.addTab(tabhost.newTabSpec("SettingFragment").setIndicator("SettingFragment"),SettingFragment.class,null);

    }




}
