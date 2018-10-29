package com.chrtc.excelTest.excelTest.service;

import com.chrtc.excelTest.excelTest.domain.DataMapping;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

public interface DataMappingXmlService {
    public void readXML(String fileId) throws IOException,DocumentException;
    public List<DataMapping> selectBySDateSet(String sDateSet);
}
