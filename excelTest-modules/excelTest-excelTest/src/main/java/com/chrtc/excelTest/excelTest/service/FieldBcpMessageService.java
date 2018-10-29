package com.chrtc.excelTest.excelTest.service;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.FieldBcpMessage;
/**
 * Created by AUTO on 2018-06-28 13:29:59.
 */
public interface FieldBcpMessageService {
    /**
    * 根据ID查询fieldBcpMessage
    * @param id
    * @return FieldBcpMessage
    */
    public FieldBcpMessage findOneById(String id);
    /**
    * 多参数分页查询fieldBcpMessage
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<FieldBcpMessage> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询fieldBcpMessage全部数据
    * @return List
    */
    public List<FieldBcpMessage> findAll();
    /**
    * 新增fieldBcpMessage数据
    * @param entity
    * @return List
    */
    public FieldBcpMessage add(FieldBcpMessage entity);
    /**
    * 根据id更新fieldBcpMessage数据
    * @param entity
    * @return List
    */
    public FieldBcpMessage update(FieldBcpMessage entity);
    /**
    * 根据id删除fieldBcpMessage
    * @param entity
    * @return List
    */
    public void delete(String id);


    public String findByFieldName(String fieldName);
    public void deleteByFieldName(String fieldName);
}
