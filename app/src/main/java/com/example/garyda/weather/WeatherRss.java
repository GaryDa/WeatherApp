package com.example.garyda.weather;

//用SAX解析Rss的Xml
public class WeatherRss {
    private String title,item,description,pubDate;

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
    /被todayweatherfragment call
    public void getTitle()
    {
        this.title = title;
    }
}
