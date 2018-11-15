package com.chrtc.excelTest.excelTest.service;

import com.chrtc.attach.common.domain.FileAttachment;

import java.util.List;

public interface TxtTitleService {
    public void readTxtTitle(List<FileAttachment> list);
    public List<String> selectByFileName(String fileName);
}
