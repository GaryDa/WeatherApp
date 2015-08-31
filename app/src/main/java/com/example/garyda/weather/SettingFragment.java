package com.example.garyda.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingFragment extends Fragment{
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TAG", "SettingFragment onAttach");
        MainActivity mainActivity = (MainActivity)activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.d("TAG", "SettingFragment onCreateView");
        return inflater.inflate(R.layout.frag_setting,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "SettingFragment onActivityCreated");
        TextView Settingtxv = (TextView)this.getView().findViewById(R.id.Settingtxv);
        Settingtxv.setText("SettingFragment");
    }
}
