package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepository;
import com.chrtc.excelTest.excelTest.domain.kettle.KTrans;

/**
 * Created by AUTO on 2018-07-11 16:06:50.
 */
public interface KTransService {
    /**
    * 根据ID查询k_trans
    * @param id
    * @return KTrans
    */
    public KTrans findOneById(String id);
    /**
    * 多参数分页查询k_trans
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KTrans> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_trans全部数据
    * @return List
    */
    public List<KTrans> findAll();
    /**
    * 新增k_trans数据
    * @param entity
    * @return List
    */
    public KTrans add(KTrans entity);
    /**
    * 根据id更新k_trans数据
    * @param entity
    * @return List
    */
    public KTrans update(KTrans entity);
    /**
    * 根据id删除k_trans
    * @param
    * @return List
    */
    public void delete(String id);

    public void deleteByTransName(String path);
    public Integer  insertTran(KTrans kTrans);
    public List findAllList();
    public KTrans   findByTransPath(String repositoryName);
    public List<KTrans>   findBytransName(String transName);

}
