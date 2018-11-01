package com.chrtc.excelTest.excelTest.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface CreateXmlService {
    public void createIndexXml(String xmlPath, LinkedList bcpMessages, List<Map<String, Object>> bankListByExcel, String SDateSet) throws IOException;
}
