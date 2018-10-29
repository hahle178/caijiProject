package com.chrtc.excelTest.excelTest.domain;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.validation.constraints.Size;

/**
 * Created by AUTO on 2018-06-07 13:58:23.
 * excel where
 */
public class ExcelEntity {
    /**截获时间*/
    @Size(max=50)
    private String capturetime;
    public static String  CAPTURETIME1  = "capturetime";
    /**访问url*/
    @Size(max=50)
    private String url;
    public static String  URL1  = "url";

    /**通信内容*/
    @Size(max=50)
    private String content;
    public static String  CONTENT1  = "content";
    /**数据类型*/
    @Size(max=50)
    private String datatype;
    public static String  DATATYPE1  = "datatype";

    public String getCapturetime() {
        return capturetime;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setCapturetime(String capturetime) {
        this.capturetime = capturetime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    @Override
    public String toString() {
        return  capturetime + "," +url+","+content+","+datatype;
    }
}