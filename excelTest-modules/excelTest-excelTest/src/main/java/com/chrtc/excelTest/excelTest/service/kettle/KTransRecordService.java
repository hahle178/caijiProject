package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KTransRecord;

/**
 * Created by AUTO on 2018-08-10 10:01:28.
 */
public interface KTransRecordService {
    /**
    * 根据ID查询k_trans_record
    * @param id
    * @return KTransRecord
    */
    public KTransRecord findOneById(String id);
    /**
    * 多参数分页查询k_trans_record
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KTransRecord> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_trans_record全部数据
    * @return List
    */
    public List<KTransRecord> findAll();
    /**
    * 新增k_trans_record数据
    * @param entity
    * @return List
    */
    public KTransRecord add(KTransRecord entity);
    /**
    * 根据id更新k_trans_record数据
    * @param entity
    * @return List
    */
    public KTransRecord update(KTransRecord entity);
    /**
    * 根据id删除k_trans_record
    * @param
    * @return List
    */
    public void delete(String id);
}
