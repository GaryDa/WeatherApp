package com.example.garyda.weather;

//用SAX解析Rss的Xml
public class WeatherRss {
    private String title="hahaha";
    private String item = null;
    private String description = null;
    private String pubDate = null ;
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setItem(String item)
    {
        this.item = item;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }
    //被todayweatherfragment call
    public String getTitle()
    {
        return title;
    }
}
