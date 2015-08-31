package com.example.garyda.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeekReportFragment extends Fragment{
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TAG", "WeekReportFragment onAttach");
        MainActivity mainActivity = (MainActivity)activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.d("TAG", "WeekReportFragment onCreateView");
        return inflater.inflate(R.layout.frag_weekreport,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "WeekReportFragment onActivityCreated");
        TextView weektxv = (TextView)this.getView().findViewById(R.id.weekweathertxv);
        weektxv.setText("frag_weekreport");
    }
}
