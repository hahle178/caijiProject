package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KJobRecord;
import com.chrtc.excelTest.excelTest.domain.kettle.KJobRecordExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KJobRecordMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KJobRecordService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-08-22 11:02:22.
 */
@Transactional
@Service
public class KJobRecordServiceImpl implements KJobRecordService {

    @Autowired
    private KJobRecordMapper kJobRecordMapper;


    public KJobRecord findOneById(String id){
        return kJobRecordMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KJobRecordExample example = new KJobRecordExample();
        KJobRecordExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kJobRecordMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KJobRecord> findAll(){
        KJobRecordExample example = new KJobRecordExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kJobRecordMapper.selectByExample(example);
    }

    public KJobRecord add(KJobRecord entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kJobRecordMapper.insertSelective(entity);
        return entity;
    }

    public KJobRecord update(KJobRecord entity){
        kJobRecordMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kJobRecordMapper.deleteLogicByPrimaryKey(id);
    }
}
