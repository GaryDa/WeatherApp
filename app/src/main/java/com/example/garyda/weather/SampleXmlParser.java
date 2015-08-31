package com.example.garyda.weather;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SampleXmlParser {

    protected SampleXmlParsingHandler sampleXmlParsingHandler;

    public SampleXmlParser(SampleXmlParsingHandler parser)
    {
        sampleXmlParsingHandler = parser;
    }
    public Object[]getData(InputStream inputStream)throws SAXException,IOException,ParserConfigurationException
    {
        Object[]data;
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        //
        XMLReader xr = sp.getXMLReader();
        if (sampleXmlParsingHandler ==null)
        {
            throw new NullPointerException("sampleXmlParsingHandler is null");

        }
        else
        {
            xr.setContentHandler(sampleXmlParsingHandler);

            xr.parse(new InputSource(inputStream));
            data = (Objects[])
            sampleXmlParsingHandler.getParsedData();
        }
        inputStream.close();
        return  data;

    }
    //從xml剖析出物件 多載方法
    public Object[]getData(String urlPath)throws SAXException,IOException,ParserConfigurationException
    {
        URL url = new URL(urlPath);
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
        uc.setConnectTimeout(15000);
        uc.setReadTimeout(15000);
        uc.connect();
        int status = uc.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK)
        {
            Object[]data = getData(url.openStream());
            return data;
        }
        return null;
    }

}
