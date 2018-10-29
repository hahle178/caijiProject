package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KJobMonitor;
import com.chrtc.excelTest.excelTest.domain.kettle.KJobMonitorExample;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitor;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitorExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KJobMonitorMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KJobMonitorService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-08-09 14:30:59.
 */
@Transactional
@Service
public class KJobMonitorServiceImpl implements KJobMonitorService {

    @Autowired
    private KJobMonitorMapper kJobMonitorMapper;


    public KJobMonitor findOneById(String id){
        return kJobMonitorMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KJobMonitorExample example = new KJobMonitorExample();
        KJobMonitorExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kJobMonitorMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KJobMonitor> findAll(){
        KJobMonitorExample example = new KJobMonitorExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kJobMonitorMapper.selectByExample(example);
    }

    public KJobMonitor add(KJobMonitor entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kJobMonitorMapper.insertSelective(entity);
        return entity;
    }

    public KJobMonitor update(KJobMonitor entity){
        kJobMonitorMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kJobMonitorMapper.deleteLogicByPrimaryKey(id);
    }
    public KJobMonitor findOneByMonitorJob(String id) {
        KJobMonitorExample example = new KJobMonitorExample();
        example.createCriteria().andMonitorJobEqualTo(id);
        List<KJobMonitor> kJobMonitors = kJobMonitorMapper.selectByExample(example);
        if (kJobMonitorMapper.selectByExample(example).size() != 0){
            return kJobMonitorMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }
}
