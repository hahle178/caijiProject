package com.chrtc.excelTest.excelTest.web;

import com.chrtc.excelTest.excelTest.service.impl.DataMappingXmlServiceImpl;
import org.apache.http.annotation.Contract;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
@Controller
@RequestMapping("dc")
public class DataMappingController {
    @Autowired
    private DataMappingXmlServiceImpl dataMappingXmlService;
  /*  public static void main(String[] args) throws IOException, DocumentException {
        dataMappingXmlService.readXML2();
    }*/
  @RequestMapping("s")
    public void s() throws Exception{
        dataMappingXmlService.readXML2();
    }
}
