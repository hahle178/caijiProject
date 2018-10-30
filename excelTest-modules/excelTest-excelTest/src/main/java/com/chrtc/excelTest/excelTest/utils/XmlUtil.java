package com.chrtc.excelTest.excelTest.utils;

import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class XmlUtil {
    private static List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();
    private RepeatUtil repeatUtil = new RepeatUtil();
    private Logger log=Logger.getLogger("xmlfile");

    public List<Map<String, Object>> ReadFile(File file) throws DocumentException {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("C:/Users/Administrator/Desktop/测试数据/123456.xml"));
        Element root = document.getRootElement();
        listNodes(root);
        return lists;
    }

    static String str = "";
    private static Map<String, Object> map = new HashMap<>();

    public XmlUtil() {
        List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();
    }

    public void listNodes(Element node) {


        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
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
                    }else{
                        log.fatal("");
                    }
                }
                listNodes(e);
            }

        }
    }

}
