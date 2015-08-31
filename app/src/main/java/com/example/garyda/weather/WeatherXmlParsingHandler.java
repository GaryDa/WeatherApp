package com.example.garyda.weather;

import android.util.Log;

import com.example.garyda.weather.SampleXmlParsingHandler;
import com.example.garyda.weather.SampleXmlParser;
import com.example.garyda.weather.WeatherRss;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.Stack;


public class WeatherXmlParsingHandler extends SampleXmlParsingHandler{

    private static final String TAG = "WeatherParsingHandler";
    //用来儲存Rss天氣的物件
    private WeatherRss weatherItem;
    //用来儲存Rss天氣的物件Stack
    private Stack<WeatherRss> mWeatherItem_list;
    //建構子
    public WeatherXmlParsingHandler()
    {

    }
    //return回傳WeatherRss[] 會將讀到的物件{WeatherRSS[]}包成Objcet[]然後開始回傳
    @Override
    public Object[]getParsedData()
    {
        WeatherRss[] Arrys_WeatherRSS = (WeatherRss[])mWeatherItem_list.toArray(new WeatherRss[mWeatherItem_list.size()]);
        //解析結果回報
        return new Object[]{Arrys_WeatherRSS};

    }
    //xml文件開始解析時呼叫此method
    public void startDocument()throws SAXException
    {
        super.startDocument();
        //在文件開始的時候，宣告出該WeatherRSS形態的Stack
        mWeatherItem_list = new Stack<WeatherRss>();
    }
    //xml文件結束時呼叫此method
    public void endDocument()throws SAXException
    {
        super.endDocument();
    }
    //解析到Element的開頭時呼叫此method
    public void startElement(String namespaceURI,String localName,String qName, Attributes atts)throws SAXException
    {
        super.startElement(namespaceURI, localName, qName, atts);
        printNodePos();

    }
    //解析到Element的結尾時呼叫此method
    public void endElement(String namespaceURI,String localName,String qName)throws SAXException
    {
        super.endElement(namespaceURI,localName,qName);
        printNodePos();
    }
    //取得Element的開頭結尾中間夾的字串
    public void characters(String fetchStr)
    {

    }
}
