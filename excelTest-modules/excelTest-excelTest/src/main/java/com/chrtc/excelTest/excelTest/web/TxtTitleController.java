package com.chrtc.excelTest.excelTest.web;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.attach.common.service.AttachService;
import com.chrtc.common.base.result.Result;
import com.chrtc.common.base.result.ResultFactory;
import com.chrtc.excelTest.excelTest.service.TxtTitleService;
import com.chrtc.excelTest.excelTest.service.impl.DataMappingXmlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exceltest/txtTitle")
public class TxtTitleController {
    @Autowired
    private DataMappingXmlServiceImpl dataMappingXmlService;
    @Autowired
    private TxtTitleService txtTitleService;
    @Autowired
    private AttachService attachService;

    @RequestMapping("upfile")
    @ResponseBody
    public Result upfile(String fileId, HttpServletResponse responese) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (fileId != null && fileId != "") {
            //判断上传的文件是什么格式
            List<FileAttachment> list = attachService.list(fileId);
            List<FileAttachment> listTitle = new ArrayList<>();

            if (list.size()>0) {
               // int num=0;
                for (int i = 0; i < list.size(); i++) {
                    String c = list.get(i).getAttachmentPathStore();
                    //截取字符串，获取本地存储文件名
                    String[] split = c.split("/");
                    //前缀
                    String name=list.get(i).getAttachmentName();
                   // String name = split[0];
                    //后缀
                    String s = split[1];
                    //获取文件后缀名，判断是什么文件
                    String houzui = s.split("\\.")[1];
                    if ((houzui.equals("TXT")||houzui.equals("txt"))&&(name.contains("_title")||name.contains("_TITLE"))) {
                        listTitle.add(list.get(i));
                    }/*else{
                        num++;
                    }
                    if (num==list.size()){
                        jsonObject.put("SUCCESS", false);
                        jsonObject.put("MSG", "上传文件不是_title.txt文件！");
                        return ResultFactory.create(jsonObject);
                    }*/
                }
                if(listTitle.size()>0){
                    txtTitleService.readTxtTitle(listTitle);
                }else {
                    jsonObject.put("SUCCESS", false);
                    jsonObject.put("MSG", "上传文件不是_title.txt文件！");
                    return ResultFactory.create(jsonObject);
                }
            }else {
                jsonObject.put("SUCCESS", false);
                jsonObject.put("MSG", "上传文件为空！");
                return ResultFactory.create(jsonObject);
            }
        }

        jsonObject.put("SUCCESS", true);
        return ResultFactory.create(jsonObject);

    }
}