package com.chrtc.excelTest.excelTest.service;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.XmlFILE;
/**
 * Created by AUTO on 2018-06-14 15:58:05.
 */
public interface XmlFILEService {
    /**
    * 根据ID查询xmlFILE
    * @param id
    * @return XmlFILE
    */
    public XmlFILE findOneById(String id);
    /**
    * 多参数分页查询xmlFILE
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<XmlFILE> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询xmlFILE全部数据
    * @return List
    */
    public List<XmlFILE> findAll();
    /**
    * 新增xmlFILE数据
    * @param entity
    * @return List
    */
    public XmlFILE add(XmlFILE entity);
    /**
    * 根据id更新xmlFILE数据
    * @param entity
    * @return List
    */
    public XmlFILE update(XmlFILE entity);
    /**
    * 根据id删除xmlFILE
    * @param entity
    * @return List
    */
    public void delete(String id);
    /**
     * 根据no查询xmlFILE
     * @param id
     * @return XmlFILE
     */
    public List<XmlFILE> findByNo(String no);
}
