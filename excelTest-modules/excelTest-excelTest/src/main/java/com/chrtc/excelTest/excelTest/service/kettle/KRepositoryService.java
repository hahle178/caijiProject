package com.chrtc.excelTest.excelTest.service.kettle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepository;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryType;
import com.chrtc.excelTest.excelTest.domain.kettle.RepositoryTree;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 */
public interface KRepositoryService {
    /**
    * 根据ID查询k_repository
    * @param id
    * @return KRepository
    */
    public KRepository findOneById(String id);
    /**
    * 多参数分页查询k_repository
    * @param searchParams
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Paging
    */
    public Paging<KRepository> findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort);
    /**
    * 查询k_repository全部数据
    * @return List
    */
    public List<KRepository> findAll();
    /**
    * 新增k_repository数据
    * @param entity
    * @return List
    */
    public KRepository add(KRepository entity);
    /**
    * 根据id更新k_repository数据
    * @param entity
    * @return List
    */
    public KRepository update(KRepository entity);
    /**
    * 根据id删除k_repository
    * @param entity
    * @return List
    */
    public void delete(String id);
    /**
     * @Title ckeck
     * @Description 判断是否可以连接上资源库
     * @param kRepository
     * @return
     * @throws KettleException
     * @return boolean
     */
    public boolean ckeck(KRepository kRepository) throws KettleException;

    /**
     * 保存资源库连接信息
     * @param kRepository
     * @param uId
     */
    public int insertRepo(KRepository kRepository);

    /**
     * 查找资源库列表
     * @return
     */
    public List<KRepository> findAllList();

    /**
     * 根据资源库名称删除资源库
     * @param name
     */
    public  void  deleteByReposityName(String name);
    /**
     * @Title getRepositoryTreeList
     * @Description 获取数据库资源库的树形菜单
     * @param repositoryId
     * @return
     * @throws KettleException
     * @return List<RepositoryTree>
     */
    public List<RepositoryTree> getTreeList(String repositoryId) throws KettleException;

}
