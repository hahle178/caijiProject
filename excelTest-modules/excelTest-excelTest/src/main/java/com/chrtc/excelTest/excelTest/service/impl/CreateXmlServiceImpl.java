package com.chrtc.excelTest.excelTest.service.impl;

import com.chrtc.excelTest.excelTest.domain.BcpMessage;
import com.chrtc.excelTest.excelTest.domain.DataMapping;
import com.chrtc.excelTest.excelTest.domain.XmlFILE;
import com.chrtc.excelTest.excelTest.service.CreateXmlService;
import com.chrtc.excelTest.excelTest.service.DataMappingXmlService;
import com.chrtc.excelTest.excelTest.service.XmlFILEService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CreateXmlServiceImpl implements CreateXmlService {
    @Autowired
    private XmlFILEService xmlFILEService;
    @Autowired
    private DataMappingXmlService dataMappingXmlService;

    //创建xml索引文件
    public void createIndexXml(String xmlPath, LinkedList bcpMessages, List<List<Object>> bankListByExcel, String SDateSet) throws IOException {
        File file = new File(xmlPath);//写入文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<XmlFILE> bcp_index = xmlFILEService.findByNo("BCP_INDEX");
        List<XmlFILE> BCP_DESCRIBE = xmlFILEService.findByNo("BCP_DESCRIBE");
        List<XmlFILE> BCP_DESCRIBE_INFO = xmlFILEService.findByNo("BCP_DESCRIBE_INFO");
        List<XmlFILE> BCP_DATA = xmlFILEService.findByNo("BCP_DATA");
        List<XmlFILE> BCP_DATA_STRUCTURE = xmlFILEService.findByNo("BCP_DATA_STRUCTURE");

        if (SDateSet.equals("")) {
            SDateSet = "TENDER_ILLEGAL_PUNISH_INFO";
        }
        //查询需要遍历的节点属性值
        final List<DataMapping> dataMappings = dataMappingXmlService.selectBySDateSet(SDateSet);

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("MESSAGE");
        Element DATASET = root.addElement("DATASET").addAttribute("name", bcp_index.get(0).getName1()).addAttribute("rmk", bcp_index.get(0).getRmk());
        Element data = DATASET.addElement("DATA");

        Element DATASET1 = data.addElement("DATASET");
        DATASET1.addAttribute("name", BCP_DESCRIBE.get(0).getName1()).addAttribute("rmk", BCP_DESCRIBE.get(0).getRmk());
        Element DATA1 = DATASET1.addElement("DATA");

        for (Object bcpMessage : bcpMessages) {
            for (int i = 0; i < bcpMessages.size(); i++) {
                BcpMessage bcpMessage1 = (BcpMessage) bcpMessages.get(i);
                for (XmlFILE xmlFILE : BCP_DESCRIBE_INFO) {
                    DATA1.addElement("ITEM").addAttribute("key", xmlFILE.getKey1()).addAttribute("val", xmlFILE.getVal()).addAttribute("rmk", xmlFILE.getRmk());
                }

                Element DATASET2 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA.get(0).getName1()).addAttribute("rmk", BCP_DATA.get(0).getRmk());
                // 生成BCP数据文件信息
                Element DATA2 = DATASET2.addElement("DATA");
                DATA2.addElement("ITEM").addAttribute("key", "H040003").addAttribute("val", bcpMessage1.getPath()).addAttribute("rmk", "文件路径");
                DATA2.addElement("ITEM").addAttribute("key", "H010020").addAttribute("val", bcpMessage1.getName()).addAttribute("rmk", "文件名");
                DATA2.addElement("ITEM").addAttribute("key", "I010034").addAttribute("val", bcpMessage1.getCount() + "").addAttribute("rmk", "记录行数");

                //生成BCP格式文件数据结构
                Element DATASET3 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA_STRUCTURE.get(0).getName1()).addAttribute("rmk", BCP_DATA_STRUCTURE.get(0).getRmk());

                Element DATA3 = DATASET3.addElement("DATA");
                for (Object o : bankListByExcel.get(0)) {
                    if (dataMappings.size() > 0) {
                        for (DataMapping dataMapping : dataMappings) {
                            if (dataMapping.getEng().equals(o.toString())) {
                                DATA3.addElement("ITEM").addAttribute("key", dataMapping.getKey()).addAttribute("eng", dataMapping.getEng()).addAttribute("chn", "ss").addAttribute("rmk", "rr");
                            }
                        }
                    }
                }
            }

            OutputFormat format = OutputFormat.createPrettyPrint();  //转换成字符串
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
            writer.close();
        }
    }
}
