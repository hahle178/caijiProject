package com.chrtc.excelTest.excelTest.service.impl;

import java.io.*;
import java.util.*;

import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.attach.common.service.AttachService;
import com.chrtc.excelTest.excelTest.domain.*;
import com.chrtc.excelTest.excelTest.mapper.XmlFILEMapper;
import com.chrtc.excelTest.excelTest.service.FieldBcpMessageService;
import com.chrtc.excelTest.excelTest.service.XmlFILEService;
import com.chrtc.excelTest.excelTest.utils.BcpUtil;
import com.chrtc.excelTest.excelTest.utils.CompressedFileUtil;
import com.chrtc.excelTest.excelTest.utils.ExcelUtil;
import com.csvreader.CsvReader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.mapper.BcpMessageMapper;
import com.chrtc.excelTest.excelTest.service.BcpMessageService;

/**
 * Created by AUTO on 2018-06-08 14:03:31.
 */
@Transactional
@Service
public class BcpMessageServiceImpl implements BcpMessageService {

    @Autowired
    private  BcpMessageMapper bcpMessageMapper;
    @Autowired
    private XmlFILEService xmlFILEService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private FieldBcpMessageService fieldBcpMessageService;


    @Value("${ezdev.attach.config.local.dir}")
    private  String dir;
    private List<Map<String, Object>> bankListByExcel = new LinkedList<>();
    private List<Map<String, Object>> bankListByExcel1 = new LinkedList<>();


    /**
     *五位自增序列号
     */
    private static int sn = 10001;
    public synchronized int getNextSN() {
        return ++sn;
    }
    public int getCurrentSN() {
        return sn;
    }

    public BcpMessage findOneById(String id){
        return bcpMessageMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        BcpMessageExample example = new BcpMessageExample();
        BcpMessageExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return bcpMessageMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<BcpMessage> findAll(){
        BcpMessageExample example = new BcpMessageExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return bcpMessageMapper.selectByExample(example);
    }

    public BcpMessage add(BcpMessage entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        bcpMessageMapper.insertSelective(entity);
        return entity;
    }

    public BcpMessage update(BcpMessage entity){
        bcpMessageMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        bcpMessageMapper.deleteLogicByPrimaryKey(id);


    }
    public LinkedList readExcelAndOut(String excelId) {
        LinkedList bcpMessages = new LinkedList<>();
        bankListByExcel1.clear();
        try {
            List<FileAttachment> list = attachService.list(excelId);
            for (FileAttachment fileAttachment:list) {
                BcpMessage bcpMessage = new BcpMessage();
                String c = fileAttachment.getAttachmentPathStore();
                String attachmentName = fileAttachment.getAttachmentName();
                HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
                //截取字符串，获取本地存储文件名
                String[] split = c.split("/");
                String s = split[1];

                File file = new File(dir + "/" + s);

                InputStream in = new FileInputStream(file);

                //读取excel中的内容
                String extString = attachmentName.substring(attachmentName.lastIndexOf("."));
                bankListByExcel = ExcelUtil.getBankListByExcel(in,extString);

                //生成bcp文件
                String sysCode = "101";//数据发送方系统标识
                String depCode = "202";//数据发送方机构标识
                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
                int nextSN = getNextSN();//五位自增序列号
                String dataCode = "303";//数据集代码
                String dataType = "0";//结构化非结构化标识

                String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
                String path = "E:" + File.separator + "EXCEL\\";
                bcpMessage.setCount(bankListByExcel.size());
                bcpMessage.setName(name);
                bcpMessage.setPath(path);
                bcpMessages.add(bcpMessage);
                BcpUtil.creatBCPFile(name, path);

                //生成bcp记录
                for (Map<String, Object> dataMap : bankListByExcel) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                        stringBuilder.append(entry.getValue() + "\t");
                    }
                    BcpUtil.writeTxtFile(stringBuilder.toString());
                }
                Map<String, Object> stringObjectMap = bankListByExcel.get(0);
                for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                    objectObjectHashMap.put(entry.getKey(),entry.getValue());
                }
                bankListByExcel1.add(objectObjectHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bcpMessages;
    }
    public LinkedList readCSVAndOut(String excelId) throws IOException {
        LinkedList bcpMessages = new LinkedList<>();
        List<Map<String,Object>> dataList= new ArrayList<>();
        bankListByExcel1.clear();
        try {
            List<FileAttachment> list = attachService.list(excelId);
            for (FileAttachment fileAttachment:list) {
                BcpMessage bcpMessage = new BcpMessage();
                String c = fileAttachment.getAttachmentPathStore();
                String attachmentName = fileAttachment.getAttachmentName();
                HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
                //截取字符串，获取本地存储文件名
                String[] split = c.split("/");
                String s = split[1];
                String inString = "";
                String tmpString = "";
                File file = new File(dir + "/" + s);

                BufferedReader in = new BufferedReader(new FileReader(file));

                //读取csv中的内容
                CsvReader creader = new CsvReader(in, ',');
                int counter = 0;
                String header = null;


                while (creader.readRecord()) {

                    Map<String,Object> dataMap = new LinkedHashMap<String,Object>();

                    if(counter == 0){
                        header = creader.getRawRecord();//读取标题行

                    }else{
                        inString = creader.getRawRecord();//读取一行数据
                        String[] row = inString.split(",");

                        int n = 0;
                        String[] headerRow = header.split(",");
                        String  value  =  "";
                        while (n < headerRow.length) {
                            //这里把列循环到Map
                            if(headerRow[n] != null){
                                value = headerRow[n].trim();
                                dataMap.put(value,row[n].trim());
                            }
                            n++;
                        }
                        value = "";
                        dataList.add(dataMap);
                    }
                    counter++;
                }

                //生成bcp文件
                String sysCode = "101";//数据发送方系统标识
                String depCode = "202";//数据发送方机构标识
                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
                int nextSN = getNextSN();//五位自增序列号
                String dataCode = "303";//数据集代码
                String dataType = "0";//结构化非结构化标识

                String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
                String path = "E:" + File.separator + "CSV\\";
                bcpMessage.setCount(dataList.size());
                bcpMessage.setName(name);
                bcpMessage.setPath(path);
                bcpMessages.add(bcpMessage);
                BcpUtil.creatBCPFile(name, path);

                //生成bcp记录
                for (Map<String, Object> dataMap : dataList) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                        stringBuilder.append(entry.getValue() + "\t");
                    }
                    BcpUtil.writeTxtFile(stringBuilder.toString());
                }
                Map<String, Object> stringObjectMap = dataList.get(0);
                for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                    objectObjectHashMap.put(entry.getKey(),entry.getValue());
                }
                bankListByExcel1.add(objectObjectHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bcpMessages;
    }
    public void createIndexXml(String xmlPath,LinkedList bcpMessages) throws IOException {
        File file = new File(xmlPath);//写入文件
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        List<XmlFILE> bcp_index = xmlFILEService.findByNo("BCP_INDEX");
        List<XmlFILE> BCP_DESCRIBE = xmlFILEService.findByNo("BCP_DESCRIBE");
        List<XmlFILE> BCP_DESCRIBE_INFO = xmlFILEService.findByNo("BCP_DESCRIBE_INFO");
        List<XmlFILE> BCP_DATA = xmlFILEService.findByNo("BCP_DATA");
        List<XmlFILE> BCP_DATA_STRUCTURE = xmlFILEService.findByNo("BCP_DATA_STRUCTURE");


        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "MESSAGE" );
        Element DATASET = root.addElement("DATASET").addAttribute( "name", bcp_index.get(0).getName1() ).addAttribute( "rmk", bcp_index.get(0).getRmk() );
        Element data = DATASET.addElement("DATA");

        Element DATASET1 = data.addElement( "DATASET" );
        DATASET1.addAttribute( "name", BCP_DESCRIBE.get(0).getName1() ).addAttribute( "rmk", BCP_DESCRIBE.get(0).getRmk() );
        Element DATA1 = DATASET1.addElement("DATA");

        //for (Object bcpMessage:bcpMessages) {
        for(int i = 0;i<bcpMessages.size();i++){
            BcpMessage bcpMessage1 = (BcpMessage) bcpMessages.get(i);
            for (XmlFILE xmlFILE : BCP_DESCRIBE_INFO) {
                DATA1.addElement("ITEM").addAttribute("key", xmlFILE.getKey1()).addAttribute("val", xmlFILE.getVal()).addAttribute("rmk", xmlFILE.getRmk());
            }

            Element DATASET2 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA.get(0).getName1()).addAttribute("rmk", BCP_DATA.get(0).getRmk());
            //生成BCP数据文件信息
            //for (Object bcpMessage : bcpMessages) {
               // BcpMessage bcpMessage1 = (BcpMessage) bcpMessage;
                Element DATA2 = DATASET2.addElement("DATA");
                DATA2.addElement("ITEM").addAttribute("key", "H040003").addAttribute("val", bcpMessage1.getPath()).addAttribute("rmk", "文件路径");
                DATA2.addElement("ITEM").addAttribute("key", "H010020").addAttribute("val", bcpMessage1.getName()).addAttribute("rmk", "文件名");
                DATA2.addElement("ITEM").addAttribute("key", "I010034").addAttribute("val", bcpMessage1.getCount() + "").addAttribute("rmk", "记录行数");
           // }

            //生成BCP格式文件数据结构
            Element DATASET3 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA_STRUCTURE.get(0).getName1()).addAttribute("rmk", BCP_DATA_STRUCTURE.get(0).getRmk());

            /*for (Map<String, Object> stringObjectMap : bankListByExcel1) {
                Element DATA3 = DATASET3.addElement("DATA");
                for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                    DATA3.addElement("ITEM").addAttribute("key", "H040003").addAttribute("eng", entry.getKey()).addAttribute("chn", "ss").addAttribute("rmk", "rr");
                }
            }*/
            Element DATA3 = DATASET3.addElement("DATA");
            Map<String, Object> stringObjectMap = bankListByExcel1.get(i);
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                DATA3.addElement("ITEM").addAttribute("key", "H040003").addAttribute("eng", entry.getKey()).addAttribute("chn", "ss").addAttribute("rmk", "rr");
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();  //转换成字符串
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter( new FileOutputStream(file), format );
        writer.write( document );
        writer.close();
    }
    public void createZIP(String path) throws Exception {
        String dataSendSysIden = "101";//数据发送方系统标识
        String dataSendDevIden = "102";//数据发送方机构标识
        String dataReceSysIden = "103";//数据接收方系统标识
        String dataReceDevIden = "104";//数据接收方机构标识
        long currentTimeMillisZIP = System.currentTimeMillis();//绝对秒数

        int nextSNZIP = getNextSN();//五位自增序列号,避重序列号

        String zipName = dataSendSysIden+"_"+dataSendDevIden+"_"+dataReceSysIden+"_"+dataReceDevIden+"_"+currentTimeMillisZIP+"_"+nextSNZIP+".zip";
        CompressedFileUtil.compressedFile("E:\\"+path+"\\", "F:\\zip\\",zipName);

    }

    public BcpMessage getEntityCreateBcp(ExcelEntity excelEntity) throws Exception{
        BcpMessage bcpMessage = new BcpMessage();
        bankListByExcel1.clear();
        //生成bcp文件
        String sysCode = "101";//数据发送方系统标识
        String depCode = "202";//数据发送方机构标识
        long currentTimeMillis = System.currentTimeMillis();//绝对秒数
        int nextSN = getNextSN();//五位自增序列号
        String dataCode = "303";//数据集代码
        String dataType = "0";//结构化非结构化标识

        String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
        String path = "E:" + File.separator + "FIELD\\";
        BcpUtil.creatBCPFile(name, path);


        String s = excelEntity.toString();
        String[] split = s.split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s1:split) {
            stringBuilder.append(s1+"\t");
        }
        //生成bcp记录
        Integer counter = BcpUtil.writeTxtFile(stringBuilder.toString());
        //共通属性赋值

        HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
        objectObjectHashMap.put(ExcelEntity.CAPTURETIME1,excelEntity.getCapturetime());
        objectObjectHashMap.put(ExcelEntity.URL1,excelEntity.getUrl());
        objectObjectHashMap.put(ExcelEntity.CONTENT1,excelEntity.getContent());
        objectObjectHashMap.put(ExcelEntity.DATATYPE1,excelEntity.getDatatype());

        bcpMessage.setCount(counter);
        bcpMessage.setName(name);
        bcpMessage.setPath(path);

        bankListByExcel1.add(objectObjectHashMap);
        
        return bcpMessage;
    }
    public LinkedList getEntityCreateBcp1(Test test) throws Exception{
        BcpMessage bcpMessage = new BcpMessage();
        LinkedList bcpMessages = new LinkedList<>();
        bankListByExcel1.clear();
        //生成bcp文件
        String sysCode = "101";//数据发送方系统标识
        String depCode = "202";//数据发送方机构标识
        long currentTimeMillis = System.currentTimeMillis();//绝对秒数
        int nextSN = getNextSN();//五位自增序列号
        String dataCode = "303";//数据集代码
        String dataType = "0";//结构化非结构化标识

        String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
        String path = "E:" + File.separator + "FIELD\\";
        BcpUtil.creatBCPFile(name, path);


        String[] split = test.getFieldValue();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s1:split) {
            stringBuilder.append(s1+"\t");
        }
        //生成bcp记录
        Integer counter = BcpUtil.writeTxtFile(stringBuilder.toString());
        //共通属性赋值

        HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();

        for (int j = 0; j < test.getFieldName().length; j++) {
            objectObjectHashMap.put(test.getFieldName()[j],test.getFieldValue()[j]);
        }


       /* objectObjectHashMap.put(test.getFieldName(),excelEntity.getCapturetime());
        objectObjectHashMap.put(ExcelEntity.URL1,excelEntity.getUrl());
        objectObjectHashMap.put(ExcelEntity.CONTENT1,excelEntity.getContent());
        objectObjectHashMap.put(ExcelEntity.DATATYPE1,excelEntity.getDatatype());*/

        bcpMessage.setCount(counter);
        bcpMessage.setName(name);
        bcpMessage.setPath(path);
        bcpMessages.add(bcpMessage);
        bankListByExcel1.add(objectObjectHashMap);
        return bcpMessages;
    }
    public LinkedList getEntityCreateBcp2(FieldVO fieldVO,Integer option) throws Exception{
        BcpMessage bcpMessage = new BcpMessage();
        LinkedList bcpMessages = new LinkedList<>();
        bankListByExcel1.clear();
        //生成bcp文件
        String sysCode = "101";//数据发送方系统标识
        String depCode = "202";//数据发送方机构标识
        long currentTimeMillis = System.currentTimeMillis();//绝对秒数
        int nextSN = getNextSN();//五位自增序列号
        String dataCode = "303";//数据集代码
        String dataType = "0";//结构化非结构化标识


        String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
        if (option == 1){
            name = fieldBcpMessageService.findByFieldName(fieldVO.getFieldHeadName()[0]);
        }
        String path = "E:" + File.separator + "FIELD\\";
        BcpUtil.creatBCPFile(name, path);

        String[] split = fieldVO.getFieldValue();
        StringBuilder stringBuilder = new StringBuilder();
        Integer count = 0;
        Integer counter = 0;
        Integer integer = 0;
        System.out.println(fieldVO.getFieldSize()[0]);
        for (String s1:split) {

            count++;

            stringBuilder.append(s1+"\t");
            if(count >= Integer.parseInt(fieldVO.getFieldSize()[0])){
                //stringBuilder.append("\r\n");
                count = 0;
                //生成bcp记录
                counter++;
                integer = BcpUtil.writeTxtFile(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }

        //共通属性赋值
        HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
        for (int j = 0; j < Integer.parseInt(fieldVO.getFieldSize()[0]); j++) {
            objectObjectHashMap.put(fieldVO.getFieldName()[j],fieldVO.getFieldValue()[j]);
        }

       /* objectObjectHashMap.put(test.getFieldName(),excelEntity.getCapturetime());
        objectObjectHashMap.put(ExcelEntity.URL1,excelEntity.getUrl());
        objectObjectHashMap.put(ExcelEntity.CONTENT1,excelEntity.getContent());
        objectObjectHashMap.put(ExcelEntity.DATATYPE1,excelEntity.getDatatype());*/

        bcpMessage.setCount(integer);
        bcpMessage.setName(name);
        bcpMessage.setPath(path);
        //bcp信息存入数据库
        FieldBcpMessage fieldBcpMessage = new FieldBcpMessage();
        fieldBcpMessage.setBcppath(path);
        fieldBcpMessage.setBcpname(name);
        fieldBcpMessage.setBcpcount(integer+"");
        fieldBcpMessage.setFieldname(fieldVO.getFieldHeadName()[0]);

        fieldBcpMessageService.deleteByFieldName(fieldVO.getFieldHeadName()[0]);
        fieldBcpMessageService.add(fieldBcpMessage);

        bcpMessages.add(bcpMessage);
        bankListByExcel1.add(objectObjectHashMap);
        return bcpMessages;
    }
}
