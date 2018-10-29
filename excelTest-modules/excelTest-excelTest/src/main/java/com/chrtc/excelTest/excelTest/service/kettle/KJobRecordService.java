package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KJobRecord;

/**
 * Created by AUTO on 2018-08-22 11:02:22.
 */
public interface KJobRecordService {
    /**
    * 根据ID查询k_job_record
    * @param id
    * @return KJobRecord
    */
    public KJobRecord findOneById(String id);
    /**
    * 多参数分页查询k_job_record
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KJobRecord> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_job_record全部数据
    * @return List
    */
    public List<KJobRecord> findAll();
    /**
    * 新增k_job_record数据
    * @param entity
    * @return List
    */
    public KJobRecord add(KJobRecord entity);
    /**
    * 根据id更新k_job_record数据
    * @param entity
    * @return List
    */
    public KJobRecord update(KJobRecord entity);
    /**
    * 根据id删除k_job_record
    * @param
    * @return List
    */
    public void delete(String id);
}
