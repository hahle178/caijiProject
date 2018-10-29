package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KJobMonitor;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitor;

/**
 * Created by AUTO on 2018-08-09 14:30:59.
 */
public interface KJobMonitorService {
    /**
    * 根据ID查询k_job_monitor
    * @param id
    * @return KJobMonitor
    */
    public KJobMonitor findOneById(String id);
    /**
    * 多参数分页查询k_job_monitor
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KJobMonitor> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_job_monitor全部数据
    * @return List
    */
    public List<KJobMonitor> findAll();
    /**
    * 新增k_job_monitor数据
    * @param entity
    * @return List
    */
    public KJobMonitor add(KJobMonitor entity);
    /**
    * 根据id更新k_job_monitor数据
    * @param entity
    * @return List
    */
    public KJobMonitor update(KJobMonitor entity);
    /**
    * 根据id删除k_job_monitor
    * @param
    * @return List
    */
    public void delete(String id);

    /**
     *
     * @param
     * @return List
     */
    public KJobMonitor findOneByMonitorJob(String id);
}
