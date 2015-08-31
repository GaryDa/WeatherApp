package com.example.garyda.weather;


import android.util.Log;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.Stack;



public abstract class SampleXmlParsingHandler extends DefaultHandler {
    protected static final String TAG = "ParsingHandler";
    //xml解析node的堆疊
    private Stack<String>in_node;
    public Stack<String>getInNode()
    {
        return in_node;
    }
    //除錯模式，手動設定成true會顯示log
    private boolean debugMode = false;
    //將轉換的資料回傳
    public abstract Object getParsedData();
    //xml開始解析時呼叫此method,這裡要加上new出自訂的物件的程式
    public void startDocument()throws SAXException
    {
        in_node = new Stack<String>();
    }
    //xml解析結束時call此method
    public void endDocument()throws SAXException
    {

    }
    //解析到Element的開頭時call此method
    public void startElement(String namespaceURI,String localName,String qName,Attributes atts)throws SAXException
    {
        if (isDebugMode())
        {
            Log.d(TAG,"startElement qName"+qName);
            for (int i = 0;i<atts.getLength();i++)
            {
                Log.v(TAG,"");

            }
        }
        in_node.push(qName);
    }
    //解析到Element的結尾時call此method
    public void endElement(String namespaceURI,String localName,String qName)throws SAXException
    {
        if (isDebugMode())
            Log.d(TAG,"endElement: qName="+qName);
        in_node.pop();
    }
    //取得Element的開頭結尾中間夾的字串
    public void characters(char ch[],int start,int length)
    {
        String fetchStr = new String(ch).substring(start,start+length);

        if (isDebugMode())
            Log.d(TAG,"\tcharacters: ch="+fetchStr);
        characters(fetchStr);
    }
    //取得Element的開頭結尾中間夾的字串這裡需要做:新增node上所有資料
    public void characters(String fetchStr)
    {

    }
    //印出現在node的位置，除錯用。例:
    public String printNodePos()
    {
        StringBuffer sb = new StringBuffer();
        //印出現在node的位置
        for (int i = 0;i<in_node.size();i++)
        {
            if (i>0)
                sb.append("->");
            sb.append(in_node.get(i));
        }
        sb.append("\n");
        return sb.toString();
    }
    //在參數堆中找到需要的參數
    public static String findAttr(Attributes atts,String findStr)
    {
        int i;
        for (i=0;i<atts.getLength();i++)
        {
            if(atts.getQName(i).compareToIgnoreCase(findStr)==0)
            {
                break;
            }
        }
        return atts.getValue(i);
    }
    public void setDebugMode(boolean debugMode)
    {
        this.debugMode = debugMode;
    }
    public boolean isDebugMode()
    {
        return debugMode;
    }
}
