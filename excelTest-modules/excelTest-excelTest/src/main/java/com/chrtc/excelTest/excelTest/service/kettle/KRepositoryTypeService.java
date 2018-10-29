package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryType;
/**
 * Created by AUTO on 2018-07-10 11:19:12.
 */
public interface KRepositoryTypeService {
    /**
    * 根据ID查询k_repository_type
    * @param id
    * @return KRepositoryType
    */
    public KRepositoryType findOneById(String id);
    /**
    * 多参数分页查询k_repository_type
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KRepositoryType> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_repository_type全部数据
    * @return List
    */
    public List<KRepositoryType> findAll();
    /**
    * 新增k_repository_type数据
    * @param entity
    * @return List
    */
    public KRepositoryType add(KRepositoryType entity);
    /**
    * 根据id更新k_repository_type数据
    * @param entity
    * @return List
    */
    public KRepositoryType update(KRepositoryType entity);
    /**
    * 根据id删除k_repository_type
    * @param entity
    * @return List
    */
    public void delete(String id);
    /**
    * 动态获取数据库资源库类型
    * @param entity
    * @return List
    */
    public List<KRepositoryType> getRepositoryTypeList();
}
