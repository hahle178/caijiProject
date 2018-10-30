package com.chrtc.excelTest.excelTest.utils;

public class FileNameUtil {
    /**
     * 五位自增序列号
     */
    private static int sn = 57000;

    public synchronized int getNextSN() {
        return ++sn;
    }

    public int getCurrentSN() {
        return sn;
    }


    /**
     * Bcp文件名生成工具
     * @return
     */
    public String BcpFileName(){
        String  dataSource="910";//数据源
        String  dataAdd="110000";//数据采集地编码
        long currentTimeMillis = System.currentTimeMillis();//数据采集时间戳
        String dataCode="BASIC_0003";//数据集编码(从数据库中获取)
        return dataSource+"-"+dataAdd+"-"+currentTimeMillis+"-"+getNextSN()+"-"+dataCode+"-"+0;
    }

    public  String ZipFileName(){
        String  dataSource="910";//数据源
        String  deptCode="684682590";//组织机构代码
        String  dataAdd="110000";//数据采集地编码
        long currentTimeMillis = System.currentTimeMillis();//数据采集时间戳
        String dataCode="BASIC_0003";//数据集编码(从数据库中获取)
        return dataSource+"-"+deptCode+"-"+dataAdd+"-"+currentTimeMillis+"-"+getNextSN()+"-"+dataCode;
    }

}
