package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryType;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryTypeExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KRepositoryTypeMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KRepositoryTypeService;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 */
@Transactional
@Service
public class KRepositoryTypeServiceImpl implements KRepositoryTypeService {

    @Autowired
    private KRepositoryTypeMapper kRepositoryTypeMapper;


    public KRepositoryType findOneById(String id){
        return kRepositoryTypeMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KRepositoryTypeExample example = new KRepositoryTypeExample();
        KRepositoryTypeExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kRepositoryTypeMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KRepositoryType> findAll(){
        KRepositoryTypeExample example = new KRepositoryTypeExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kRepositoryTypeMapper.selectByExample(example);
    }

    public KRepositoryType add(KRepositoryType entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kRepositoryTypeMapper.insertSelective(entity);
        return entity;
    }

    public KRepositoryType update(KRepositoryType entity){
        kRepositoryTypeMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kRepositoryTypeMapper.deleteLogicByPrimaryKey(id);
    }

    @Override
    public List<KRepositoryType> getRepositoryTypeList() {
        return kRepositoryTypeMapper.getAllKRepositoryType();
    }
}
