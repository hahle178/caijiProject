package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.kettle.KSchedulerType;
import com.chrtc.excelTest.excelTest.domain.kettle.KSchedulerTypeExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KSchedulerTypeMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KSchedulerTypeService;

/**
 * Created by AUTO on 2018-10-31 22:38:03.
 */
@Transactional
@Service
public class KSchedulerTypeServiceImpl implements KSchedulerTypeService {

    @Autowired
    private KSchedulerTypeMapper kSchedulerTypeMapper;


    public KSchedulerType findOneById(String id){
        return kSchedulerTypeMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KSchedulerTypeExample example = new KSchedulerTypeExample();
        KSchedulerTypeExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kSchedulerTypeMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KSchedulerType> findAll(){
        KSchedulerTypeExample example = new KSchedulerTypeExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kSchedulerTypeMapper.selectByExample(example);
    }

    public KSchedulerType add(KSchedulerType entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kSchedulerTypeMapper.insertSelective(entity);
        return entity;
    }

    public KSchedulerType update(KSchedulerType entity){
        kSchedulerTypeMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kSchedulerTypeMapper.deleteLogicByPrimaryKey(id);
    }
}
