package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitor;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitorExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KTransMonitorMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KTransMonitorService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-08-09 14:31:00.
 */
@Transactional
@Service
public class KTransMonitorServiceImpl implements KTransMonitorService {

    @Autowired
    private KTransMonitorMapper kTransMonitorMapper;


    public KTransMonitor findOneById(String id){
        return kTransMonitorMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KTransMonitorExample example = new KTransMonitorExample();
        KTransMonitorExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kTransMonitorMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KTransMonitor> findAll(){
        KTransMonitorExample example = new KTransMonitorExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kTransMonitorMapper.selectByExample(example);
    }

    public KTransMonitor add(KTransMonitor entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kTransMonitorMapper.insertSelective(entity);
        return entity;
    }

    public KTransMonitor update(KTransMonitor entity){
        kTransMonitorMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kTransMonitorMapper.deleteLogicByPrimaryKey(id);
    }

    public KTransMonitor findOneByMonitorTrans(String id) {
        KTransMonitorExample example = new KTransMonitorExample();
        example.createCriteria().andMonitorTransEqualTo(id);
        List<KTransMonitor> kTransMonitors = kTransMonitorMapper.selectByExample(example);
        if (kTransMonitorMapper.selectByExample(example).size() != 0){
            return kTransMonitorMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }
}
