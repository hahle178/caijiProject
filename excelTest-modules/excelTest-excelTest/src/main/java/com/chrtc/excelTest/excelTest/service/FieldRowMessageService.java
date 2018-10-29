package com.chrtc.excelTest.excelTest.service;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.FieldRowMessage;
/**
 * Created by AUTO on 2018-06-27 11:22:03.
 */
public interface FieldRowMessageService {
    /**
    * 根据ID查询fieldRowMessage
    * @param id
    * @return FieldRowMessage
    */
    public FieldRowMessage findOneById(String id);
    /**
    * 多参数分页查询fieldRowMessage
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<FieldRowMessage> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询fieldRowMessage全部数据
    * @return List
    */
    public List<FieldRowMessage> findAll();
    /**
    * 新增fieldRowMessage数据
    * @param entity
    * @return List
    */
    public FieldRowMessage add(FieldRowMessage entity);
    /**
    * 根据id更新fieldRowMessage数据
    * @param entity
    * @return List
    */
    public FieldRowMessage update(FieldRowMessage entity);
    /**
    * 根据id删除fieldRowMessage
    * @param entity
    * @return List
    */
    public void delete(String id);

    public List<FieldRowMessage> findByGroup();
    public List<FieldRowMessage> findByHead(String selectHead);
    public void deleteByFieldName(String headName);

}
