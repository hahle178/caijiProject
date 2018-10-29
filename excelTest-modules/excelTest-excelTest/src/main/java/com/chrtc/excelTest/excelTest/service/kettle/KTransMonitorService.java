package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransMonitor;

/**
 * Created by AUTO on 2018-08-09 14:31:00.
 */
public interface KTransMonitorService {
    /**
    * 根据ID查询k_trans_monitor
    * @param id
    * @return KTransMonitor
    */
    public KTransMonitor findOneById(String id);
    /**
    * 多参数分页查询k_trans_monitor
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KTransMonitor> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_trans_monitor全部数据
    * @return List
    */
    public List<KTransMonitor> findAll();
    /**
    * 新增k_trans_monitor数据
    * @param entity
    * @return List
    */
    public KTransMonitor add(KTransMonitor entity);
    /**
    * 根据id更新k_trans_monitor数据
    * @param entity
    * @return List
    */
    public KTransMonitor update(KTransMonitor entity);
    /**
    * 根据id删除k_trans_monitor
    * @param
    * @return List
    */
    public void delete(String id);
    /**
    *
    * @param
    * @return List
    */
    public KTransMonitor findOneByMonitorTrans(String id);
}
