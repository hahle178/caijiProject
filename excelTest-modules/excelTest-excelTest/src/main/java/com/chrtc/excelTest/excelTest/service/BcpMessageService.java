package com.chrtc.excelTest.excelTest.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.BcpMessage;
import com.chrtc.excelTest.excelTest.domain.ExcelEntity;
import com.chrtc.excelTest.excelTest.domain.FieldVO;
import com.chrtc.excelTest.excelTest.domain.Test;

/**
 * Created by AUTO on 2018-06-08 14:03:31.
 */
public interface BcpMessageService {
    /**
    * 根据ID查询bcpMessage
    * @param id
    * @return BcpMessage
    */
    public BcpMessage findOneById(String id);
    /**
    * 多参数分页查询bcpMessage
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<BcpMessage> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询bcpMessage全部数据
    * @return List
    */
    public List<BcpMessage> findAll();
    /**
    * 新增bcpMessage数据
    * @param entity
    * @return List
    */
    public BcpMessage add(BcpMessage entity);
    /**
    * 根据id更新bcpMessage数据
    * @param entity
    * @return List
    */
    public BcpMessage update(BcpMessage entity);
    /**
    * 根据id删除bcpMessage
    * @param entity
    * @return List
    */
    public void delete(String id);
    /**
     * 读取excel文件且生成bcp文件
     * @param
     * @return List
     */
    public LinkedList readExcelAndOut(String excelId);
    /**
     * 读取csv文件且生成bcp文件
     * @param
     * @return List
     */
    public LinkedList readCSVAndOut(String excelId)  throws IOException;
    /**
     * 生成索引文件
     * @param
     * @return List
     */
    public void createIndexXml(String xmlPath,LinkedList bcpMessages) throws IOException;
    /**
     * 生成压缩文件
     * @param
     * @return List
     */
    public void createZIP(String path) throws Exception;
    /**
     * 生成bcp文件
     * @param
     * @return List
     */
    public BcpMessage getEntityCreateBcp(ExcelEntity excelEntity) throws Exception;
    /**
     * 生成bcp文件
     * @param
     * @return List
     */
    public LinkedList getEntityCreateBcp1(Test test) throws Exception;
    /**
     * 生成bcp文件
     * @param
     * @return List
     */
    public LinkedList getEntityCreateBcp2(FieldVO fieldVO,Integer option) throws Exception;

}
