package com.chrtc.excelTest.excelTest.domain;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public class FileMessage {
    private List<List<Object>> titleList;
    private List<Map<String,Object>> dataList;

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public List<List<Object>> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<List<Object>> titleList) {
        this.titleList = titleList;
    }
}
