package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KRepository;
import com.chrtc.excelTest.excelTest.domain.kettle.KTrans;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KTransMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KTransService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-07-11 16:06:50.
 */
@Transactional
@Service
public class KTransServiceImpl implements KTransService {

    @Autowired
    private KTransMapper kTransMapper;


    public KTrans findOneById(String id){
        return kTransMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KTransExample example = new KTransExample();
        KTransExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kTransMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KTrans> findAll(){
        KTransExample example = new KTransExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kTransMapper.selectByExample(example);
    }

    public KTrans add(KTrans entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kTransMapper.insertSelective(entity);
        return entity;
    }

    public KTrans update(KTrans entity){
        kTransMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kTransMapper.deleteLogicByPrimaryKey(id);
    }

    @Override
    public void deleteByTransName(String path) {
        KTransExample example = new KTransExample();
        example.createCriteria().andTransPathEqualTo(path);
         kTransMapper.deleteByExample(example);
    }

    @Override
    public Integer insertTran(KTrans kTrans) {
        String id = UUID.randomUUID().toString().replace("-","");
        kTrans.setId(id);
       /* long l = System.currentTimeMillis();
        kRepository.setRepositoryId(Integer.parseInt(l+""));*/
        //kRepository.setAddUser(uId);
        //kRepository.setEditUser(uId);
        kTrans.setDelFlag("1");
        return kTransMapper.insert(kTrans);
    }

    @Override
    public List findAllList() {
        return kTransMapper.findAllList();
    }

    @Override
    public KTrans findByTransPath(String repositoryName) {
        KTransExample example = new KTransExample();
        example.createCriteria().andTransPathEqualTo(repositoryName);
        return kTransMapper.selectByExample(example).get(0);
    }


    @Override
    public List<KTrans>   findBytransName(String transName){
        KTransExample example = new KTransExample();
        example.createCriteria().andTransNameEqualTo(transName);
        return kTransMapper.selectByExample(example);
    }

}
