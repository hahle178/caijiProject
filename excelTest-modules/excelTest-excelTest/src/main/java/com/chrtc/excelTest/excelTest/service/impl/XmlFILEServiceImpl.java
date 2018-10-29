package com.chrtc.excelTest.excelTest.service.impl;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.XmlFILE;
import com.chrtc.excelTest.excelTest.domain.XmlFILEExample;
import com.chrtc.excelTest.excelTest.mapper.XmlFILEMapper;
import com.chrtc.excelTest.excelTest.service.XmlFILEService;

/**
 * Created by AUTO on 2018-06-14 15:58:05.
 */
@Transactional
@Service
public class XmlFILEServiceImpl implements XmlFILEService {

    @Autowired
    private XmlFILEMapper xmlFILEMapper;


    public XmlFILE findOneById(String id){
        return xmlFILEMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        XmlFILEExample example = new XmlFILEExample();
        XmlFILEExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return xmlFILEMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<XmlFILE> findAll(){
        XmlFILEExample example = new XmlFILEExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return xmlFILEMapper.selectByExample(example);
    }

    public XmlFILE add(XmlFILE entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        xmlFILEMapper.insertSelective(entity);
        return entity;
    }

    public XmlFILE update(XmlFILE entity){
        xmlFILEMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        xmlFILEMapper.deleteLogicByPrimaryKey(id);
    }
    /**
     * 根据no查询xmlFILE
     * @param
     * @return XmlFILE
     */
    public List<XmlFILE> findByNo(String no){
        XmlFILEExample example = new XmlFILEExample();
        example.createCriteria().andNo1EqualTo(no);
        return xmlFILEMapper.selectByExample(example);
    }
}
