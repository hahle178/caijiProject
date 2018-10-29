package com.chrtc.excelTest.excelTest.service.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.InputValue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.FieldRowMessage;
import com.chrtc.excelTest.excelTest.domain.FieldRowMessageExample;
import com.chrtc.excelTest.excelTest.mapper.FieldRowMessageMapper;
import com.chrtc.excelTest.excelTest.service.FieldRowMessageService;

/**
 * Created by AUTO on 2018-06-27 11:22:03.
 */
@Transactional
@Service
public class FieldRowMessageServiceImpl implements FieldRowMessageService {

    @Autowired
    private FieldRowMessageMapper fieldRowMessageMapper;
    @Autowired
    private FieldRowMessageService fieldRowMessageService;


    public FieldRowMessage findOneById(String id){
        return fieldRowMessageMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        FieldRowMessageExample example = new FieldRowMessageExample();
        FieldRowMessageExample.Criteria c = example.createCriteria();
        c.andFieldheadnameEqualTo((String)searchParams.get("fieldHeadName"));


        return fieldRowMessageMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<FieldRowMessage> findAll(){
        FieldRowMessageExample example = new FieldRowMessageExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return fieldRowMessageMapper.selectByExample(example);
    }

    public FieldRowMessage add(FieldRowMessage entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        fieldRowMessageMapper.insertSelective(entity);
        return entity;
    }

    public FieldRowMessage update(FieldRowMessage entity){
        fieldRowMessageMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        fieldRowMessageMapper.deleteLogicByPrimaryKey(id);
    }

    public List<FieldRowMessage> findByGroup(){
        List<FieldRowMessage> list  = fieldRowMessageMapper.findByGroup();
        return list;
    }
    public List<FieldRowMessage> findByHead(String selectHead){
        FieldRowMessageExample example = new FieldRowMessageExample();
        example.createCriteria().andFieldheadnameEqualTo(selectHead);
        return fieldRowMessageMapper.selectByExample(example);
    }
    public void deleteByFieldName(String headName){
        FieldRowMessageExample example = new FieldRowMessageExample();
        example.createCriteria().andFieldheadnameEqualTo(headName);
         fieldRowMessageMapper.deleteByExample(example);
    }

}
