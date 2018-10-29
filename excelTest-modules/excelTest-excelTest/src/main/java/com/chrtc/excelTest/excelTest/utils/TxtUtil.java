package com.chrtc.excelTest.excelTest.utils;

import com.chrtc.excelTest.excelTest.domain.BcpMessage;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.*;

public class TxtUtil {
    private static Logger log = Logger.getLogger("txtfile");
    private List<Map<String, Object>> ListByFile = new LinkedList<>();

    //读取文件
    public List<Map<String, Object>> readFile(File file, int count) {
        BufferedReader reader = null;
        List<Map<String, Object>> dataList = new LinkedList<>();
        RepeatUtil repeatUtil = new RepeatUtil();
        try {
            if (file.isDirectory()) {
                readFile(file, count);
            } else {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                //读取file中的内容
                while ((tempString = reader.readLine()) != null) {
                    //保存每一行
                    String allContent = "";
                    JSONObject jsonObject = JSONObject.fromObject(tempString);
                    Iterator it = jsonObject.keys();
                    Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        dataMap.put(key, jsonObject.getString(key));
                        allContent = allContent + "/t" + jsonObject.getString(key);
                    }
                    line++;
                    if (repeatUtil.fileRepeat(allContent)) {
                        log.fatal("文件名为" + file.getName() + "的第" + line + "行数据出现重复,数据内容为:" + allContent);
                    } else {
                        dataList.add(dataMap);
                    }
                    if (dataMap.size() != count) {
                        log.fatal("文件名为" + file.getName() + "的第" + line + "行数据出现缺失,数据内容为:" + allContent);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return dataList;
    }

    //读取title文件
    public Map<String, Object> readTitle(File file) {
        BufferedReader reader = null;
        List<Map<String, Object>> titleList = new LinkedList<>();
        Map<String, Object> titleMap = new LinkedHashMap<String, Object>();
        try {
            if (file.isDirectory()) {
                readTitle(file);
            } else {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                //读取file中的内容

                while ((tempString = reader.readLine()) != null) {

                    //保存每一行
                    JSONArray titleArray = JSONArray.fromObject(tempString);
                    if (titleArray.size() > 0) {
                        for (int i = 0; i < titleArray.size(); i++) {
                            JSONObject job = titleArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            titleMap.put((String) job.get("dataCode"), job.get("dataName"));
                        }
                    }
                    //titleList.add(titleMap);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return titleMap;
    }


}
