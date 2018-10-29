package com.chrtc.excelTest.excelTest.utils;
import org.pentaho.di.trans.steps.systemdata.SystemData;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    /**
     * 日志写出测试demo
     * @param fileName
     */
    public void ExcelLogWriting(String fileName,int row,String key,int cod) {
            try {
                //Whatever the file path is.
                String path="E://ExcelLogs/"+fileName+".log";
                File statText = new File(path);
                FileOutputStream is = new FileOutputStream(statText,true);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                Writer w = new BufferedWriter(osw);
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=new Date();
                switch (cod){
                    case 0:{
                        w.write(dateFormat.format(date.getTime())+":文件名为"+fileName+"的第"+row+"行数据出现重复"+key);
                        break;
                    }
                    case 1:{
                        w.write(dateFormat.format(date.getTime())+":文件名为"+fileName+"的第"+row+"行数据出现错位"+key);
                        break;
                    }
                }
               ((BufferedWriter) w).newLine();
                w.close();
            } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
            }
        }

        public void CountLog(String fileName,String key){
        try {
            String path = "E://CountLogs/" + fileName + ".log";
            File statText = new File(path);
            FileOutputStream is = new FileOutputStream(statText, true);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();


            w.write(dateFormat.format(date.getTime())+"文件名为"+fileName+"的文件总共有"+key+"列");
            ((BufferedWriter) w).newLine();
            w.close();
        }catch (IOException e){
            System.err.println("Problem writing to the file statsTest.txt");
        }
        }
    }






