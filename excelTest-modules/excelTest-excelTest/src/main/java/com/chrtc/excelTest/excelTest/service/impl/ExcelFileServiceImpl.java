package com.chrtc.excelTest.excelTest.service.impl;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.ExcelFile;
import com.chrtc.excelTest.excelTest.domain.ExcelFileExample;
import com.chrtc.excelTest.excelTest.mapper.ExcelFileMapper;
import com.chrtc.excelTest.excelTest.service.ExcelFileService;

/**
 * Created by AUTO on 2018-06-12 11:05:30.
 */
@Transactional
@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    @Autowired
    private ExcelFileMapper excelFileMapper;


    public ExcelFile findOneById(String id){
        return excelFileMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        ExcelFileExample example = new ExcelFileExample();
        ExcelFileExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return excelFileMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<ExcelFile> findAll(){
        ExcelFileExample example = new ExcelFileExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return excelFileMapper.selectByExample(example);
    }

    public ExcelFile add(ExcelFile entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        excelFileMapper.insertSelective(entity);
        return entity;
    }

    public ExcelFile update(ExcelFile entity){
        excelFileMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        excelFileMapper.deleteLogicByPrimaryKey(id);
    }
}
