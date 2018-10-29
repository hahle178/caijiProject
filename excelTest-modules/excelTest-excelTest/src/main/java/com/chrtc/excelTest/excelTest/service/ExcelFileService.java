package com.chrtc.excelTest.excelTest.service;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.ExcelFile;
/**
 * Created by AUTO on 2018-06-12 11:05:30.
 */
public interface ExcelFileService {
    /**
    * 根据ID查询excelFile
    * @param id
    * @return ExcelFile
    */
    public ExcelFile findOneById(String id);
    /**
    * 多参数分页查询excelFile
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<ExcelFile> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询excelFile全部数据
    * @return List
    */
    public List<ExcelFile> findAll();
    /**
    * 新增excelFile数据
    * @param entity
    * @return List
    */
    public ExcelFile add(ExcelFile entity);
    /**
    * 根据id更新excelFile数据
    * @param entity
    * @return List
    */
    public ExcelFile update(ExcelFile entity);
    /**
    * 根据id删除excelFile
    * @param entity
    * @return List
    */
    public void delete(String id);


}
