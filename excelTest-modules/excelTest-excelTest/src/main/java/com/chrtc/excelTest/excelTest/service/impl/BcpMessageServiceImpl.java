package com.chrtc.excelTest.excelTest.service.impl;

import java.io.*;
import java.util.*;

import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.attach.common.service.AttachService;
import com.chrtc.excelTest.excelTest.domain.*;
import com.chrtc.excelTest.excelTest.service.*;
import com.chrtc.excelTest.excelTest.utils.*;
import com.csvreader.CsvReader;
import org.apache.log4j.Logger;
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
import com.chrtc.excelTest.excelTest.mapper.BcpMessageMapper;

/**
 * Created by AUTO on 2018-06-08 14:03:31.
 */
@Transactional
@Service
public class BcpMessageServiceImpl implements BcpMessageService {

    @Autowired
    private BcpMessageMapper bcpMessageMapper;
    @Autowired
    private XmlFILEService xmlFILEService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private FieldBcpMessageService fieldBcpMessageService;
    @Autowired
    private CreateXmlService createXmlService;
    @Autowired
    private TxtTitleService txtTitleService;

    @Value("${ezdev.attach.config.local.dir}")
    private String dir;
    private List<Map<String, Object>> bankListByExcel = new LinkedList<>();
    private List<Map<String, Object>> bankListByExcel1 = new LinkedList<>();
    private FileNameUtil fileNameUtil = new FileNameUtil();
    private List<Map<String, Object>> ListByFile = new LinkedList<>();
    private Logger log =Logger.getLogger("CSVfile");
    /**
     * 五位自增序列号
     */
    private static int sn = 10001;

    public synchronized int getNextSN() {
        return ++sn;
    }

    public int getCurrentSN() {
        return sn;
    }

    public BcpMessage findOneById(String id) {
        return bcpMessageMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort) {
        BcpMessageExample example = new BcpMessageExample();
        BcpMessageExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return bcpMessageMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<BcpMessage> findAll() {
        BcpMessageExample example = new BcpMessageExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return bcpMessageMapper.selectByExample(example);
    }

    public BcpMessage add(BcpMessage entity) {
        String id = UUID.randomUUID().toString().replace("-", "");
        entity.setId(id);
        entity.setVersionNum(0);
        bcpMessageMapper.insertSelective(entity);
        return entity;
    }

    public BcpMessage update(BcpMessage entity) {
        bcpMessageMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id) {
        bcpMessageMapper.deleteLogicByPrimaryKey(id);


    }

    public BcpMessage readExcelAndOut(FileAttachment fileAttachment) {
        LinkedList bcpMessages = new LinkedList<BcpMessage>();
        BcpMessage bcpMessage = new BcpMessage();
        FileMessage fileMessage = new FileMessage();
        String xmlPath = "E:" + File.separator + "EXCEL"+File.separator+ "AQ_ZIP_INDEX.xml";
        bankListByExcel1.clear();
        fileNameUtil.mkDirs("E:" + File.separator + "EXCEL"+File.separator);
        String filename = fileAttachment.getAttachmentName();
        filename = filename.substring(0,filename.indexOf("."));
        try {
//            List<FileAttachment> list = attachService.list(excelId);
//            for (FileAttachment fileAttachment : list) {
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

                fileMessage = ExcelUtil.getBankListByExcel(in, extString, attachmentName);
                bankListByExcel = fileMessage.getDataList();

                //生成bcp文件
                String sysCode = "910";//数据源代码910
                String depCode = "684682590";//组织机构代码
                String dataSource = "110000"; //数据采集地编码
                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
                int nextSN = getNextSN();//五位自增序列号
                String dataCode = "BASIC_0003";//数据集代码
                String dataType = "0";//结构化非结构化标识

                String name = fileNameUtil.BcpFileName();
                String path = "E:" + File.separator + "EXCEL"+File.separator;
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

                Map<String, Object> stringObjectMap = new LinkedHashMap<>();

                List<List<Object>> titleList = fileMessage.getTitleList();
                for (List<Object> list2 : titleList) {
                    for (Object title : list2) {
                        stringObjectMap.put(title.toString(), title.toString());
                    }
                    for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                        objectObjectHashMap.put(entry.getKey(), entry.getValue());
                    }
                    bankListByExcel1.add(objectObjectHashMap);
                }
//            }
            List<List<Object>> titlelist = fileMessage.getTitleList();
            createXmlService.createIndexXml(xmlPath, bcpMessages, titlelist, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bcpMessage;
    }

    public BcpMessage readCSVAndOut(FileAttachment fileAttachment) throws IOException {
        LinkedList bcpMessages = new LinkedList<>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<List<Object>> titlelist = new LinkedList<>();
        RepeatUtil repeatUtil = new RepeatUtil();
        BcpMessage bcpMessage = new BcpMessage();
        String xmlPath = "E:" + File.separator + "CSV" + File.separator + "AQ_ZIP_INDEX.xml";
        fileNameUtil.mkDirs("E:" + File.separator + "CSV" + File.separator );
        bankListByExcel1.clear();
        String filename = fileAttachment.getAttachmentName();
        filename = filename.substring(0,filename.indexOf("."));
        try {
//            List<FileAttachment> list = attachService.list(excelId);
//            for (FileAttachment fileAttachment : list) {

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

                List<Object> title = new LinkedList<Object>();
                while (creader.readRecord()) {

                    Map<String, Object> dataMap = new LinkedHashMap<String, Object>();

                    if (counter == 0) {
                        header = creader.getRawRecord();//读取标题行
                        String[] row = header.split(",");
                        for (int i = 0; i < row.length; i++) {
                            title.add(row[i]);
                        }
                        titlelist.add(title);
                    } else {
                        inString = creader.getRawRecord();//读取一行数据
                        if (!repeatUtil.fileRepeat(inString)) {
                            String[] row = inString.split(",");
                            int n = 0;
                            String[] headerRow = header.split("`,");
                            String value = "";
                            if(title.size()==row.length) {
                                while (n < title.size()) {
                                    //这里把列循环到Map
                                    if (title.get(n) != null) {
                                        value = title.get(n).toString();
                                        dataMap.put(value, row[n].trim());
                                    }
                                    n++;
                                }
                                value = "";
                                dataList.add(dataMap);
                            }else{
                                log.fatal("出现错位或者缺失数据,数据内容为"+inString);
                            }
                        }else{
                           log.fatal("出现重复,重复内容为:"+inString);
                        }

                    }
                    counter++;
                }

//                //生成bcp文件
//                String sysCode = "101";//数据发送方系统标识
//                String depCode = "202";//数据发送方机构标识
//                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
//                int nextSN = getNextSN();//五位自增序列号
//                String dataCode = "303";//数据集代码
//                String dataType = "0";//结构化非结构化标识

//              String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
                String name = fileNameUtil.BcpFileName();
                String path = "E:" + File.separator + "CSV"+File.separator;
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
                    objectObjectHashMap.put(entry.getKey(), entry.getValue());
                }
                bankListByExcel1.add(objectObjectHashMap);
//            }


            createXmlService.createIndexXml(xmlPath, bcpMessages, titlelist, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bcpMessage;
    }

    public void createIndexXml(String xmlPath, LinkedList bcpMessages) throws IOException {
        File file = new File(xmlPath);//写入文件
        if (!file.exists()) {
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
        Element root = document.addElement("MESSAGE");
        Element DATASET = root.addElement("DATASET").addAttribute("name", bcp_index.get(0).getName1()).addAttribute("rmk", bcp_index.get(0).getRmk());
        Element data = DATASET.addElement("DATA");

        Element DATASET1 = data.addElement("DATASET");
        DATASET1.addAttribute("name", BCP_DESCRIBE.get(0).getName1()).addAttribute("rmk", BCP_DESCRIBE.get(0).getRmk());
        Element DATA1 = DATASET1.addElement("DATA");

        for (int i = 0; i < bcpMessages.size(); i++) {
            BcpMessage bcpMessage1 = (BcpMessage) bcpMessages.get(i);
            for (XmlFILE xmlFILE : BCP_DESCRIBE_INFO) {
                DATA1.addElement("ITEM").addAttribute("key", xmlFILE.getKey1()).addAttribute("val", xmlFILE.getVal()).addAttribute("rmk", xmlFILE.getRmk());
            }

            Element DATASET2 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA.get(0).getName1()).addAttribute("rmk", BCP_DATA.get(0).getRmk());
            //生成BCP数据文件信息
            Element DATA2 = DATASET2.addElement("DATA");
            DATA2.addElement("ITEM").addAttribute("key", "H040003").addAttribute("val", bcpMessage1.getPath()).addAttribute("rmk", "文件路径");
            DATA2.addElement("ITEM").addAttribute("key", "H010020").addAttribute("val", bcpMessage1.getName()).addAttribute("rmk", "文件名");
            DATA2.addElement("ITEM").addAttribute("key", "I010034").addAttribute("val", bcpMessage1.getCount() + "").addAttribute("rmk", "记录行数");

            //生成BCP格式文件数据结构
            Element DATASET3 = DATA1.addElement("DATASET").addAttribute("name", BCP_DATA_STRUCTURE.get(0).getName1()).addAttribute("rmk", BCP_DATA_STRUCTURE.get(0).getRmk());
            Element DATA3 = DATASET3.addElement("DATA");
            Map<String, Object> stringObjectMap = bankListByExcel1.get(i);
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                DATA3.addElement("ITEM").addAttribute("key", "H040003").addAttribute("eng", entry.getKey()).addAttribute("chn", "ss").addAttribute("rmk", "rr");
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();  //转换成字符串
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        writer.write(document);
        writer.close();
    }

    public void createZIP(String path) throws Exception {
        String dataSendSysIden = "101";//数据发送方系统标识
        String dataSendDevIden = "102";//数据发送方机构标识
        String dataReceSysIden = "103";//数据接收方系统标识
        String dataReceDevIden = "104";//数据接收方机构标识
        long currentTimeMillisZIP = System.currentTimeMillis();//绝对秒数

        int nextSNZIP = getNextSN();//五位自增序列号,避重序列号
        FileNameUtil fileNameUtil= new FileNameUtil();
        String zipName =fileNameUtil.ZipFileName();
        CompressedFileUtil.compressedFile("E:\\" + path + "\\", "E:\\zip\\", zipName);

    }

    public BcpMessage getEntityCreateBcp(ExcelEntity excelEntity) throws Exception {
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
        for (String s1 : split) {
            stringBuilder.append(s1 + "\t");
        }
        //生成bcp记录
        Integer counter = BcpUtil.writeTxtFile(stringBuilder.toString());
        //共通属性赋值

        HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
        objectObjectHashMap.put(ExcelEntity.CAPTURETIME1, excelEntity.getCapturetime());
        objectObjectHashMap.put(ExcelEntity.URL1, excelEntity.getUrl());
        objectObjectHashMap.put(ExcelEntity.CONTENT1, excelEntity.getContent());
        objectObjectHashMap.put(ExcelEntity.DATATYPE1, excelEntity.getDatatype());

        bcpMessage.setCount(counter);
        bcpMessage.setName(name);
        bcpMessage.setPath(path);

        bankListByExcel1.add(objectObjectHashMap);

        return bcpMessage;
    }

    public LinkedList getEntityCreateBcp1(Test test) throws Exception {
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
        for (String s1 : split) {
            stringBuilder.append(s1 + "\t");
        }
        //生成bcp记录
        Integer counter = BcpUtil.writeTxtFile(stringBuilder.toString());
        //共通属性赋值

        HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();

        for (int j = 0; j < test.getFieldName().length; j++) {
            objectObjectHashMap.put(test.getFieldName()[j], test.getFieldValue()[j]);
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

    public LinkedList getEntityCreateBcp2(FieldVO fieldVO, Integer option) throws Exception {
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
        if (option == 1) {
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
        for (String s1 : split) {

            count++;

            stringBuilder.append(s1 + "\t");
            if (count >= Integer.parseInt(fieldVO.getFieldSize()[0])) {
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
            objectObjectHashMap.put(fieldVO.getFieldName()[j], fieldVO.getFieldValue()[j]);
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
        fieldBcpMessage.setBcpcount(integer + "");
        fieldBcpMessage.setFieldname(fieldVO.getFieldHeadName()[0]);

        fieldBcpMessageService.deleteByFieldName(fieldVO.getFieldHeadName()[0]);
        fieldBcpMessageService.add(fieldBcpMessage);

        bcpMessages.add(bcpMessage);
        bankListByExcel1.add(objectObjectHashMap);
        return bcpMessages;
    }

    /**
     * 读取txt文件写出bcp文件
     *
     * @param
     * @return
     */
    @Override
    public BcpMessage readTXTAndOut(FileAttachment fileAttachment) {
        LinkedList bcpMessages = new LinkedList<>();
        List<List<Object>> titlelist = new LinkedList<>();
        String xmlPath = "E:" + File.separator + "TXT" + File.separator + "AQ_ZIP_INDEX.xml";
        int Column = 0;
        BcpMessage bcpMessage = new BcpMessage();
        ListByFile.clear();
        String filename = fileAttachment.getAttachmentName();
        filename = filename.substring(0,filename.indexOf("."));
        try {
//            List<FileAttachment> list = attachService.list(excelId);
            TxtUtil txtUtil = new TxtUtil();
//            for (FileAttachment fileAttachment : list) {
            String c = fileAttachment.getAttachmentPathStore();
            String attachmentName = fileAttachment.getAttachmentName();
            HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
            //截取字符串，获取本地存储文件名
            String[] split = c.split("/");
            String s = split[1];

            File file = new File(dir + "/" + s);
            List<String> titleList = new LinkedList<>();

                titleList=txtTitleService.selectByFileName(filename+"_title");
                //进行title文件判断
                if (titleList.size()>0) {
                    List<Map<String, Object>> maps = txtUtil.readFile(file, titleList.size());

                    //生成bcp文件
                    String path = "E:" + File.separator + "TXT"+File.separator;
//                    String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;
                    String name = fileNameUtil.BcpFileName();
                    //String path = "E:" + File.separator + "FILE\\";
                    bcpMessage.setCount(maps.size());
                    bcpMessage.setName(name);
                    bcpMessage.setPath(path);
                    bcpMessages.add(bcpMessage);
                    //生成文件
                    BcpUtil.creatBCPFile(name, path);

                    for (Map<String, Object> dataMap : maps) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Object key : titleList) {
                            //for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                            Object o = dataMap.get(key.toString());
                            if (o != null && o != "") {
                                stringBuilder.append(o + "\t");
                            } else {
                                stringBuilder.append("\t");
                            }
                            //}
                            //
                        }
                        BcpUtil.writeTxtFile(stringBuilder.toString());
                    }
                }else{
                    return null;
                }
//            }
            //暂时使用空数据来代替SDate
            createXmlService.createIndexXml(xmlPath, bcpMessages, titlelist, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bcpMessage;
    }

    /**
     * 专写txt文件的XMl
     *
     * @param xmlPath
     * @param bcpMessages
     * @throws IOException
     */
    public void createIndexXml1(String xmlPath, LinkedList bcpMessages) throws IOException {
        File file = new File(xmlPath);//写入文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("MESSAGE");
        Element DATASET = root.addElement("DATASET").addAttribute("name", "COMMON_010017").addAttribute("rmk", "数据文件索引信息");
        Element data = DATASET.addElement("DATA");

        Element DATASET1 = data.addElement("DATASET");
        DATASET1.addAttribute("name", "COMMON_010013").addAttribute("rmk", "BCP格式文件描述信息");


        for (int i = 0; i < bcpMessages.size(); i++) {
            BcpMessage bcpMessage1 = (BcpMessage) bcpMessages.get(i);
            Element DATA1 = DATASET1.addElement("DATA");

            DATA1.addElement("ITEM").addAttribute("key", "10A0024").addAttribute("val", "\\t").addAttribute("rmk", "列分隔符(缺少值时默认为制表符\\t)");
            DATA1.addElement("ITEM").addAttribute("key", "10A0025").addAttribute("val", "\\n").addAttribute("rmk", "行分隔符(缺少值时默认为制表符\\n)");
            DATA1.addElement("ITEM").addAttribute("key", "01A0004").addAttribute("val", "SOURCE_0002").addAttribute("rmk", "数据集代码");
            DATA1.addElement("ITEM").addAttribute("key", "02E0016").addAttribute("val", "").addAttribute("rmk", "数据来源");
            DATA1.addElement("ITEM").addAttribute("key", "07B0013").addAttribute("val", "").addAttribute("rmk", "安全专用产品厂家组织机构代码");
            DATA1.addElement("ITEM").addAttribute("key", "06A0008").addAttribute("val", "").addAttribute("rmk", "数据采集地");
            DATA1.addElement("ITEM").addAttribute("key", "10A0027").addAttribute("val", "1").addAttribute("rmk", "数据起始行，可选项。不填写默认为第一行");
            DATA1.addElement("ITEM").addAttribute("key", "10A0028").addAttribute("val", "UTF-8").addAttribute("rmk", "可选项,默认为UTF-8。BCP格式文件编码格式（采用不带格式的编码方式。如：UTF-8无BOM）");

            Element DATASET2 = DATA1.addElement("DATASET").addAttribute("name", "COMMON_010014").addAttribute("rmk", "BCP数据文件信息");

            Element DATA2 = DATASET2.addElement("DATA");
            DATA2.addElement("ITEM").addAttribute("key", "H040003").addAttribute("val", bcpMessage1.getPath()).addAttribute("rmk", "文件路径");
            DATA2.addElement("ITEM").addAttribute("key", "H010020").addAttribute("val", bcpMessage1.getName()).addAttribute("rmk", "文件名");
            DATA2.addElement("ITEM").addAttribute("key", "I010034").addAttribute("val", bcpMessage1.getCount() + "").addAttribute("rmk", "记录行数");

            Element DATASET3 = DATA1.addElement("DATASET").addAttribute("name", "COMMON_010015").addAttribute("rmk", "BCP格式文件数据结构");


            Element DATA3 = DATASET3.addElement("DATA");
            Map<String, Object> stringObjectMap = ListByFile.get(i);
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                DATA3.addElement("ITEM").addAttribute("key", "H040003").addAttribute("eng", entry.getKey()).addAttribute("chn", (String) entry.getValue()).addAttribute("rmk", "rr");
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();  //转换成字符串
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        writer.write(document);
        writer.close();

    }

    /**
     * 专写txt的zip
     *
     * @param bcpPath
     * @throws Exception
     */
    public void createZIP1(String bcpPath) throws Exception {

//        String dataSendSysIden = "101";//数据发送方系统标识
//        String dataSendDevIden = "102";//数据发送方机构标识
//        String dataReceSysIden = "103";//数据接收方系统标识
//        String dataReceDevIden = "104";//数据接收方机构标识
//        long currentTimeMillisZIP = System.currentTimeMillis();//绝对秒数
//
//        int nextSNZIP = getNextSN();//五位自增序列号,避重序列号
        //文件名生成工具引入
        String zipName = fileNameUtil.ZipFileName();

        CompressedFileUtil.compressedFile("E:\\" + bcpPath + "\\", "F:\\ZIP\\", zipName);

    }

    /**
     * 读取XML文件
     *
     * @param
     * @return
     */
    @Override
    public BcpMessage readXMLAndOut(FileAttachment fileAttachment) {
        LinkedList bcpMessages = new LinkedList<>();
        XmlUtil xmlUtil = new XmlUtil();
        FileMessage fileMessage = new FileMessage();
        String xmlPath = "E:" + File.separator + "XML" + File.separator + "AQ_ZIP_INDEX.xml";
        fileNameUtil.mkDirs("E:" + File.separator + "XML" + File.separator );
        bankListByExcel1.clear();
        String filename = fileAttachment.getAttachmentName();
        filename = filename.substring(0,filename.indexOf("."));
        BcpMessage bcpMessage = new BcpMessage();
        List<List<Object>> titlelist = new LinkedList<>();
        try {
//            List<FileAttachment> list = attachService.list(excelId);
//            for (FileAttachment fileAttachment : list)
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
                fileMessage = xmlUtil.ReadFile(file);
                titlelist.addAll(fileMessage.getTitleList());
                //生成bcp文件
//                String sysCode = "101";//数据发送方系统标识
//                String depCode = "202";//数据发送方机构标识
//                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
//                int nextSN = getNextSN();//五位自增序列号
//                String dataCode = "303";//数据集代码
//                String dataType = "0";//结构化非结构化标识

//                String name = sysCode + "_" + depCode + "_" + currentTimeMillis + "_" + nextSN + "_" + dataCode + "_" + dataType;);
                //生成bcp文件
//                String sysCode = "910";//数据源代码910
//                String depCode = "684682590";//组织机构代码
//                String dataSource = "110000"; //数据采集地编码
//                long currentTimeMillis = System.currentTimeMillis();//绝对秒数
//                int nextSN = getNextSN();//五位自增序列号
//                String dataCode = "BASIC_0003";//数据集代码
//                String dataType = "0";//结构化非结构化标识
                String name = fileNameUtil.BcpFileName();
                String path = "E:" + File.separator + "XML"+File.separator;
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
                    objectObjectHashMap.put(entry.getKey(), entry.getValue());
                }
                bankListByExcel1.add(objectObjectHashMap);
            createXmlService.createIndexXml(xmlPath, bcpMessages, titlelist, filename);
//                Map<String, Object> stringObjectMap = new LinkedHashMap<>();
//                List<List<Object>> titleList = fileMessage.getTitleList();
//                for (List<Object> list2 : titleList) {
//                    for (Object title : list2) {
//                        stringObjectMap.put(title.toString(), title.toString());
//                    }
//                    for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
//                        objectObjectHashMap.put(entry.getKey(), entry.getValue());
//                    }
//                    bankListByExcel1.add(objectObjectHashMap);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bcpMessage;
    }

}
