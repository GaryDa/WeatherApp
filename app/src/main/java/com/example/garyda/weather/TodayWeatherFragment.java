package com.example.garyda.weather;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TodayWeatherFragment extends Fragment{
    private TextView TodayTxv;
    //Rss資料的網址
    String trgUrl;
    //新聞資料的物件陣列
    WeatherRss[] Arr_RssWeather;
    //要更新時提示的訊息
    protected static final int REFRESH_DATA = 0x00000001;
    private Button getData_btn;
    //城市下拉式清單用 spinner的 ArrayAdapter、存放城市array的陣列
    private ArrayAdapter<String>citylist;
    private String[]CityNameArray;
    private Spinner Cityspi;
    Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                //更新資料，將rss的標題用迴圈印出
                case REFRESH_DATA:
                    TodayTxv.setText("");
                    for (int i = 0;i<Arr_RssWeather.length;i++)
                    {
                        TodayTxv.append(Arr_RssWeather[i].getTitle()+"\n");
                    }
                break;
            }
        }
    };
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TAG", "TodayWeatherFragment onAttach");
        MainActivity mainActivity = (MainActivity)activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.d("TAG", "TodayWeatherFragment onCreateView");
        return inflater.inflate(R.layout.frag_todayweather,container,false);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "TodayWeatherFragment onActivityCreated");
        //初始化view
        iniviews();
        TodayTxv.setText("Today");
        //取得array.xml裡的CityName陣列
        CityNameArray = getResources().getStringArray(R.array.CityName);
        //將CityName陣列用ArrayAdapter顯示在spinner上
        citylist = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,CityNameArray);
        Cityspi.setAdapter(citylist);
        //設定下拉樣式
        citylist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //建立spinner監聽事件
        Cityspi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.d("TAG","onItemSelected");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void iniviews() {
        //初始化view
        TodayTxv = (TextView)this.getView().findViewById(R.id.TodayWeathertxv);
        Cityspi = (Spinner)this.getView().findViewById(R.id.Cityspinner);

    }
}
