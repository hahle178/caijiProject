package com.chrtc.excelTest.excelTest.web;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.common.base.result.Result;
import com.chrtc.common.base.result.ResultFactory;
import com.chrtc.excelTest.excelTest.domain.ExcelEntity;
import com.chrtc.excelTest.excelTest.service.impl.DataMappingXmlServiceImpl;
import org.apache.http.annotation.Contract;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
@Controller
@RequestMapping("/exceltest/dataMapping")
public class DataMappingController {
    @Autowired
    private DataMappingXmlServiceImpl dataMappingXmlService;
  /*  public static void main(String[] args) throws IOException, DocumentException {
        dataMappingXmlService.readXML2();
    }*/
  @RequestMapping("upfile")
    public Result upfile(String fileId, HttpServletResponse responese) throws Exception{
      JSONObject jsonObject = new JSONObject();
      dataMappingXmlService.readXML(fileId);
      jsonObject.put("SUCCESS",true);
      return ResultFactory.create(jsonObject);
    }
}
