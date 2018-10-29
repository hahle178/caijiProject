package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KTrans;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransExample;
import com.chrtc.excelTest.excelTest.domain.kettle.Kjob;

/**
 * Created by AUTO on 2018-08-02 10:22:05.
 */
public interface KjobService {
    /**
    * 根据ID查询k_job
    * @param id
    * @return Kjob
    */
    public Kjob findOneById(String id);
    /**
    * 多参数分页查询k_job
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<Kjob> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_job全部数据
    * @return List
    */
    public List<Kjob> findAll();
    /**
    * 新增k_job数据
    * @param entity
    * @return List
    */
    public Kjob add(Kjob entity);
    /**
    * 根据id更新k_job数据
    * @param entity
    * @return List
    */
    public Kjob update(Kjob entity);
    /**
    * 根据id删除k_job
    * @param
    * @return List
    */
    public void delete(String id);
    public List findAllList();

    public void deleteByJobName(String path);
    public Integer  insertJob(Kjob kjob);
    public Kjob findByJobPath(String repositoryName);
    public List<Kjob> findByJobName(String findByJobName);

}
