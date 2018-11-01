package com.chrtc.excelTest.excelTest.utils;

public class ZipUtil {
    private FileNameUtil fileNameUtil = new FileNameUtil();

    public void createZIP(String path) throws Exception {
        String zipName =fileNameUtil.ZipFileName();
        CompressedFileUtil.compressedFile("E:\\" + path + "\\", "E:\\zip\\", zipName);

    }
}
