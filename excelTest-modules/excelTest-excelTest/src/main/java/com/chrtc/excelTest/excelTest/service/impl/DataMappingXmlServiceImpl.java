package com.chrtc.excelTest.excelTest.service.impl;

import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.attach.common.service.AttachService;
import com.chrtc.excelTest.excelTest.domain.BcpMessage;
import com.chrtc.excelTest.excelTest.domain.DataMapping;
import com.chrtc.excelTest.excelTest.mapper.DataMappingMapper;
import com.chrtc.excelTest.excelTest.service.DataMappingXmlService;
import com.chrtc.excelTest.excelTest.utils.XmlUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Transactional
@Service
public class DataMappingXmlServiceImpl implements DataMappingXmlService {
    @Autowired
    private DataMappingMapper dataMappingMapper;
    @Autowired
    private AttachService attachService;


    @Value("${ezdev.attach.config.local.dir}")
    private String dir;
    private List<Map<String, Object>> bankListByExcel = new LinkedList<>();
    private List<Map<String, Object>> bankListByExcel1 = new LinkedList<>();

    @Override
    public void readXML(String fileId) throws IOException, DocumentException {
        XmlUtil xmlUtil = new XmlUtil();
        bankListByExcel1.clear();
        try {
            List<FileAttachment> list = attachService.list(fileId);
            for (FileAttachment fileAttachment : list) {
                BcpMessage bcpMessage = new BcpMessage();
                String c = fileAttachment.getAttachmentPathStore();
                String attachmentName = fileAttachment.getAttachmentName();
                HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
                //截取字符串，获取本地存储文件名
                String[] split = c.split("/");
                String s = split[1];

                File file = new File(dir + "/" + s);

                InputStream in = new FileInputStream(file);
                Map<String, Object> map = xmlUtil.ReadDateMappingXml(file);
                Map<String, Object> listMap = (Map<String, Object>) map.get("listMap");
                Map<String, Object> valMap = (Map<String, Object>) map.get("valMap");
               /* for (String name : listMap.keySet()) {
                    System.out.println(name);
                }
                System.out.println("------------------------------------");
                for (String name : valMap.keySet()) {
                    System.out.println(name);
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void readXML2() throws DocumentException {
        XmlUtil xmlUtil = new XmlUtil();
        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\数据处理\\格转配置文件1023(1)\\格转配置文件1023\\DataMapping.xml");
            Map<String, Object> map = xmlUtil.ReadDateMappingXml(file);
            Map<String, Object> listMap = (Map<String, Object>) map.get("listMap");
            Map<String, Object> valMap = (Map<String, Object>) map.get("valMap");
            //存放map的key
            String key = null;
            for (String name : listMap.keySet()) {
                key = name;
                Map<String, String> map1 = (Map<String, String>) listMap.get(key);
                for (String name2 : map1.keySet()) {
                    DataMapping dataMapping = new DataMapping();
                    String id = UUID.randomUUID().toString().replace("-", "");
                    dataMapping.setId(id);
                    dataMapping.setSDataSet(key);
                    dataMapping.setDDataSet(valMap.get(key).toString());
                    dataMapping.setEng(name2);
                    dataMapping.setKey(map1.get(name2).toString());
                    dataMappingMapper.insert(dataMapping);
                }

            }
          /*  System.out.println("------------------------------------");
            for (String name : valMap.keySet()) {
                System.out.println(name);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //通过sdateset查询   TENDER_ILLEGAL_PUNISH_INFO
    @Override
    public List<DataMapping> selectBySDateSet(String sDateSet) {
        return dataMappingMapper.selectBySDateSet(sDateSet);
    }

}
