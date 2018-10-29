package com.chrtc.excelTest.excelTest.service.impl;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.FieldBcpMessage;
import com.chrtc.excelTest.excelTest.domain.FieldBcpMessageExample;
import com.chrtc.excelTest.excelTest.mapper.FieldBcpMessageMapper;
import com.chrtc.excelTest.excelTest.service.FieldBcpMessageService;

/**
 * Created by AUTO on 2018-06-28 13:29:59.
 */
@Transactional
@Service
public class FieldBcpMessageServiceImpl implements FieldBcpMessageService {

    @Autowired
    private FieldBcpMessageMapper fieldBcpMessageMapper;


    public FieldBcpMessage findOneById(String id){
        return fieldBcpMessageMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        FieldBcpMessageExample example = new FieldBcpMessageExample();
        FieldBcpMessageExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return fieldBcpMessageMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<FieldBcpMessage> findAll(){
        FieldBcpMessageExample example = new FieldBcpMessageExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return fieldBcpMessageMapper.selectByExample(example);
    }

    public FieldBcpMessage add(FieldBcpMessage entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        fieldBcpMessageMapper.insertSelective(entity);
        return entity;
    }

    public FieldBcpMessage update(FieldBcpMessage entity){
        fieldBcpMessageMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        fieldBcpMessageMapper.deleteLogicByPrimaryKey(id);
    }


    public String findByFieldName(String fieldName){
        FieldBcpMessageExample example = new FieldBcpMessageExample();
        example.createCriteria().andFieldnameEqualTo(fieldName);
        List<FieldBcpMessage> fieldBcpMessages = fieldBcpMessageMapper.selectByExample(example);
        return  fieldBcpMessages.get(0).getBcpname();
    }
    public void deleteByFieldName(String fieldName){
        FieldBcpMessageExample example = new FieldBcpMessageExample();
        example.createCriteria().andFieldnameEqualTo(fieldName);
        fieldBcpMessageMapper.deleteByExample(example);
    }
}
