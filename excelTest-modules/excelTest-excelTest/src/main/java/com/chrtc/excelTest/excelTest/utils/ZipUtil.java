package com.chrtc.excelTest.excelTest.utils;

import java.io.File;

public class ZipUtil {
    private FileNameUtil fileNameUtil = new FileNameUtil();

    public void createZIP(String path) throws Exception {
        String zipName =fileNameUtil.ZipFileName();
        CompressedFileUtil.compressedFile(File.separator+"home"+File.separator + path + File.separator, File.separator+"E:"+File.separator+"zip"+File.separator, zipName);

    }
}
