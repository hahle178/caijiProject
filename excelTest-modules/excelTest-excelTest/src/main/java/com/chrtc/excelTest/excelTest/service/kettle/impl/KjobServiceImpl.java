package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KTrans;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransExample;
import com.chrtc.excelTest.excelTest.domain.kettle.Kjob;
import com.chrtc.excelTest.excelTest.domain.kettle.KjobExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KjobMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KjobService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-08-02 10:22:05.
 */
@Transactional
@Service
public class KjobServiceImpl implements KjobService {

    @Autowired
    private KjobMapper kjobMapper;


    public Kjob findOneById(String id){
        return kjobMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KjobExample example = new KjobExample();
        KjobExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kjobMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<Kjob> findAll(){
        KjobExample example = new KjobExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kjobMapper.selectByExample(example);
    }

    public Kjob add(Kjob entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kjobMapper.insertSelective(entity);
        return entity;
    }

    public Kjob update(Kjob entity){
        kjobMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kjobMapper.deleteLogicByPrimaryKey(id);
    }

    @Override
    public List findAllList() {
        return kjobMapper.findAllList();
    }

    public void deleteByJobName(String path){
        KjobExample example = new KjobExample();
        example.createCriteria().andJobPathEqualTo(path);
        kjobMapper.deleteByExample(example);
    }
    public Integer  insertJob(Kjob kjob){
        String id = UUID.randomUUID().toString().replace("-","");
        kjob.setId(id);
       /* long l = System.currentTimeMillis();
        kRepository.setRepositoryId(Integer.parseInt(l+""));*/
        //kRepository.setAddUser(uId);
        //kRepository.setEditUser(uId);
        kjob.setDelFlag("1");
        return kjobMapper.insert(kjob);
    }

    @Override
    public Kjob findByJobPath(String repositoryName) {
        KjobExample example = new KjobExample();
        example.createCriteria().andJobPathEqualTo(repositoryName);
        return kjobMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Kjob> findByJobName(String findByJobName) {
        KjobExample example = new KjobExample();
        example.createCriteria().andJobNameEqualTo(findByJobName);
        return kjobMapper.selectByExample(example);
    }
}
