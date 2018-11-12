package com.chrtc.excelTest.excelTest.utils;

import com.chrtc.excelTest.excelTest.domain.FileMessage;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class XmlUtil {
    private static List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();
    private RepeatUtil repeatUtil = new RepeatUtil();
    private Logger log = Logger.getLogger("xmlfile");
    //存储单个Normalizing节点的对象
    private   Map<String, String> mapL = new HashMap<>();
    //存储param对应的数据
    private   Map<String, Map> listMap = new HashMap<>();
    //存储sdata和ddata
    private   Map<String, Object> valMap = new HashMap<>();
    //用来存储sdata
    private   String sdata;
    //ddata
    private   String ddata;
    //存储ListMap和valMap
    private static Map<String, Object> mapCount = new HashMap<>();

    public FileMessage ReadFile(File file) throws DocumentException {
        FileMessage fileMessage = new FileMessage();
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        listNodes(root);
        fileMessage.setDataList(lists);
        return fileMessage;
    }


    static String str = "";
    private static Map<String, Object> map = new HashMap<>();

    public XmlUtil() {
        List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();
        map.clear();
    }

    public void listNodes(Element node) {


        Iterator<Element> iterator = node.elementIterator();
        while (iterator.  hasNext()) {
            Element e = iterator.next();
            if (e.elements().size() == 0) {
                map.put(e.getName(), e.getText());
                str += e.getTextTrim();
            } else {
                if (!map.isEmpty()) {
                    System.out.println("===========" + str);
                    System.out.println("++++++++做查重处理");
                    if (!repeatUtil.fileRepeat(str)) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.putAll(map);
                        lists.add(map1);
                        str = "";
                    } else {
                        log.fatal("");
                    }
                }
                listNodes(e);
            }

        }
    }

    public Map<String, Object> ReadDateMappingXml(File file1) throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File("C:\\Users\\Administrator\\Desktop\\数据处理\\格转配置文件1023(1)\\格转配置文件1023\\DataMapping.xml");
        try {
            Document document = reader.read(file);
            Element element = document.getRootElement();
            listNodes2(element);

            mapCount.put("listMap", listMap);
            mapCount.put("valMap", valMap);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapCount;
    }

    //循环读取xml文件将符合的Normalizing获取
    public  void listNodes2(Element e) {
        Iterator<Element> it = e.elementIterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.getName().equals("Normalizing")) {
                List<Map<String, String>> list = new ArrayList<>();
                //  System.out.println( element.getParent().getName() + "-------" + element.getName());
                listNodes3(element);
            }
        }
    }

    //递归调用子节点，将所有符合的条件的父标签下的子标签中的值获取出来
    public   void listNodes3(Element element) {
        Iterator<Element> childNode = element.elementIterator();
        while (childNode.hasNext()) {
            Element child = childNode.next();
            //判断是否是Param节点
            if (child.getName().equals("Param")) {
                //System.out.println(child.getName());
                List<Attribute> paramAttribute = child.attributes();
                Map<String, String> map = new HashMap<>();
                String key = "";
                String value = "";
                for (Attribute attribute : paramAttribute) {
                    if (attribute.getName().equals("Value")) {
                        key = attribute.getValue();
                    }
                }
                List<Attribute> normalizedFieldAttribute = child.getParent().getParent().attributes();
                for (Attribute attribute : normalizedFieldAttribute) {
                    if (attribute.getName().equals("Element")) {
                        value = attribute.getValue();
                    }
                }
                map.put(key, value);
                mapL.putAll(map);
                Iterator<Element> pNode=child.getParent().getParent().getParent().elementIterator();
                Element e=null;
                while(pNode.hasNext()) {
                    if (pNode.hasNext()){
                        e=pNode.next();
                    }
                }
                //判断当前的param节点的父级节点的父级节点是否还有下一个
                if (child.getParent().getParent().equals(e)) {
                    Map<String, String> objectHashMap=new HashMap<>() ;
                    //如果没有就代表该Normalizing已经遍历完成
                    List<Attribute> normalizingFieldAttribute = child.getParent().getParent().getParent().attributes();
                    objectHashMap.putAll(mapL);
                    for (Attribute attribute : normalizingFieldAttribute) {
                        if (attribute.getName().equals("DDataSet")) {
                            ddata = attribute.getValue();
                        } else if (attribute.getName().equals("SDataSet")) {
                            sdata = attribute.getValue();
                        }
                    }
                    listMap.put(sdata, objectHashMap);
                    valMap.put(sdata, ddata);
                    mapL.clear();
                }
            }
            listNodes3(child);
        }
    }
}

