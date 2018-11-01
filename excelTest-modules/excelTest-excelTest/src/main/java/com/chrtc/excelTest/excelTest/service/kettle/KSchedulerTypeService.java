package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KSchedulerType;
/**
 * Created by AUTO on 2018-10-31 22:38:03.
 */
public interface KSchedulerTypeService {
    /**
    * 根据ID查询k_scheduler_type
    * @param id
    * @return KSchedulerType
    */
    public KSchedulerType findOneById(String id);
    /**
    * 多参数分页查询k_scheduler_type
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KSchedulerType> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_scheduler_type全部数据
    * @return List
    */
    public List<KSchedulerType> findAll();
    /**
    * 新增k_scheduler_type数据
    * @param entity
    * @return List
    */
    public KSchedulerType add(KSchedulerType entity);
    /**
    * 根据id更新k_scheduler_type数据
    * @param entity
    * @return List
    */
    public KSchedulerType update(KSchedulerType entity);
    /**
    * 根据id删除k_scheduler_type
    * @param entity
    * @return List
    */
    public void delete(String id);
}
