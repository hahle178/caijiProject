package com.chrtc.excelTest.excelTest.service.impl;

import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.excelTest.excelTest.domain.TxtTitle;
import com.chrtc.excelTest.excelTest.mapper.TxtTitleMapper;
import com.chrtc.excelTest.excelTest.service.TxtTitleService;
import com.chrtc.excelTest.excelTest.utils.TxtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class TxtTitleServiceImpl implements TxtTitleService {
    @Value("${ezdev.attach.config.local.dir}")
    private String dir;

    @Autowired
    private TxtTitleMapper txtTitleMapper;
    @Override
    public void readTxtTitle(List<FileAttachment> list) {
        TxtUtil txtUtil = new TxtUtil();
        for (FileAttachment fileAttachment : list) {
            String c = fileAttachment.getAttachmentPathStore();
            //文件名
            String fileName = fileAttachment.getAttachmentName();
            fileName = fileName.substring(0,fileName.indexOf("."));
            String[] split = c.split("/");
            String s = split[1];

            File file = new File(dir + "/" + s);
            List<List<Object>> lists = txtUtil.readTitle(file);
            List<String> dataCodeList=txtTitleMapper.selectByFileName(fileName);
            for(String l:dataCodeList){
                System.out.println(l);
            }
            txtTitleMapper.deleteByFileName(fileName);
            if (lists.size() > 0) {
                for (Object dataCode : lists.get(0)) {
                    TxtTitle txtTitle = new TxtTitle();
                    txtTitle.setFileName(fileName);
                    txtTitle.setVersionNum(0);
                    txtTitle.setDataCode(dataCode.toString());
                    int i=txtTitleMapper.insert(txtTitle);
                }
            }

        }


    }
}
