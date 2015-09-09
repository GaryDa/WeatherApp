package com.example.garyda.weather;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.xml.parsers.ParserConfigurationException;

public class TodayWeatherFragment extends Fragment implements OnClickListener{
    private static TextView ResultTxv;
    private Button CitySubmit;
    //Rss資料的網址
    String trgUrl;
    //新聞資料的物件陣列
    static WeatherRss[] Arr_RssWeather;
    //要更新時提示的訊息
    protected static final int REFRESH_DATA = 0x00000001;
    //城市下拉式清單用 spinner的 ArrayAdapter、存放城市array的陣列
    private ArrayAdapter<String>citylist;
    private String[]CityNameArray;
    private Spinner Cityspi;

    static class MsgHandler extends Handler
    {
        private WeakReference<Fragment>mFragment;
        public MsgHandler(Fragment mFragment)
        {
            this.mFragment = new WeakReference<Fragment>(mFragment);
        }
        public void handleMessage(Message msg)
        {
            Fragment fra = mFragment.get();


                switch (msg.what)
                {
                //更新資料，將rss的標題用迴圈印出
                case REFRESH_DATA:
                ResultTxv.setText("");
                for (int i = 0; i < Arr_RssWeather.length; i++)
                {
                    ResultTxv.append(Arr_RssWeather[i].getTitle() + "\n");
                }
                break;

                }


        }
    }
    private MsgHandler mHandler = new MsgHandler(this);

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

        ResultTxv = (TextView)this.getView().findViewById(R.id.resultTxv);
        Cityspi = (Spinner)this.getView().findViewById(R.id.Cityspinner);
        CitySubmit = (Button)this.getView().findViewById(R.id.CitySubmit);
        CitySubmit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        if (v == CitySubmit)
        {
            trgUrl ="www.cwb.gov.tw/rss/forecast/36_01.xml";
            new Thread()
            {
                public void run(){
                Arr_RssWeather = getWeather();
                if (Arr_RssWeather != null)
                {
                    mHandler.sendEmptyMessage(REFRESH_DATA);
                }}
            }.start();
        }
    }

    public WeatherRss[]getWeather()
    {
        if (trgUrl==null)
            return null;
        try
        {
            SampleXmlParser dataXmlParser = new SampleXmlParser(
                    new WeatherXmlParsingHandler());
            Object[]data = (Object[])dataXmlParser.getData(trgUrl);
            if (data != null)
            {
                if (data[0]instanceof WeatherRss[])
                {
                    return (WeatherRss[])data[0];
                }
            }
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
