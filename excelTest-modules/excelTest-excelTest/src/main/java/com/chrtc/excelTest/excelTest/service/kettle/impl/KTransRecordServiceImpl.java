package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.KTransRecord;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransRecordExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KTransRecordMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KTransRecordService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;

/**
 * Created by AUTO on 2018-08-10 10:01:28.
 */
@Transactional
@Service
public class KTransRecordServiceImpl implements KTransRecordService {

    @Autowired
    private KTransRecordMapper kTransRecordMapper;


    public KTransRecord findOneById(String id){
        return kTransRecordMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KTransRecordExample example = new KTransRecordExample();
        KTransRecordExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andRecordTransEqualTo(searchParams.get("record_trans")+"");


        return kTransRecordMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KTransRecord> findAll(){
        KTransRecordExample example = new KTransRecordExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kTransRecordMapper.selectByExample(example);
    }

    public KTransRecord add(KTransRecord entity){
    	/*String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);*/
    	entity.setVersionNum(0);
        kTransRecordMapper.insertSelective(entity);
        return entity;
    }

    public KTransRecord update(KTransRecord entity){
        kTransRecordMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kTransRecordMapper.deleteLogicByPrimaryKey(id);
    }
}
