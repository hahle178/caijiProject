package com.chrtc.excelTest.excelTest.web;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.attach.common.service.AttachService;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
    @Autowired
    private AttachService attachService;
  @RequestMapping("upfile")
    public Result upfile(String fileId, HttpServletResponse responese) throws Exception{
      JSONObject jsonObject = new JSONObject();
      if (fileId != null && fileId != "" ) {
          //判断上传的文件是什么格式
          List<FileAttachment> list = attachService.list(fileId);
          String c = list.get(0).getAttachmentPathStore();
          //截取字符串，获取本地存储文件名
          String[] split = c.split("/");
          String s = split[1];
          //获取文件后缀名，判断是什么文件
          String houzui = s.split("\\.")[1];
          if (!houzui.equals("xml")) {
              jsonObject.put("SUCCESS",false);
              jsonObject.put("MSG","上传文件不是xml文件！");
              return ResultFactory.create(jsonObject);
          }
      }else{
          jsonObject.put("SUCCESS",false);
          jsonObject.put("MSG","上传文件为空！");
          return ResultFactory.create(jsonObject);
      }
      dataMappingXmlService.readXML(fileId);
      jsonObject.put("SUCCESS",true);
      return ResultFactory.create(jsonObject);
    }
}
