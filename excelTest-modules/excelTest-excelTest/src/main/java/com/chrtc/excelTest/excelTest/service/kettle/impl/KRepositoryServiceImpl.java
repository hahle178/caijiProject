package com.chrtc.excelTest.excelTest.service.kettle.impl;

import java.util.*;

import com.chrtc.excelTest.excelTest.domain.kettle.RepositoryTree;
import com.chrtc.excelTest.excelTest.utils.kettle.RepositoryUtil;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.utils.UtilDate;
import com.chrtc.common.base.utils.UtilStr;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepository;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryExample;
import com.chrtc.excelTest.excelTest.mapper.kettle.KRepositoryMapper;
import com.chrtc.excelTest.excelTest.service.kettle.KRepositoryService;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 */
@Transactional
@Service
public class KRepositoryServiceImpl implements KRepositoryService {

    @Autowired
    private KRepositoryMapper kRepositoryMapper;


    public KRepository findOneById(String id){
        return kRepositoryMapper.selectByPrimaryKey(id);
    }

    public Paging findAllByPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sort){
        KRepositoryExample example = new KRepositoryExample();
        KRepositoryExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");


        return kRepositoryMapper.findAllByPage(example, pageNumber, pageSize, sort);
    }

    public List<KRepository> findAll(){
        KRepositoryExample example = new KRepositoryExample();
        example.createCriteria().andDelFlagEqualTo("0");
        return kRepositoryMapper.selectByExample(example);
    }

    public KRepository add(KRepository entity){
    	String id = UUID.randomUUID().toString().replace("-","");
    	entity.setId(id);
    	entity.setVersionNum(0);
        kRepositoryMapper.insertSelective(entity);
        return entity;
    }

    public KRepository update(KRepository entity){
        kRepositoryMapper.updateByPrimaryKeySelective(entity);
        return entity;
    }


    public void delete(String id){
        kRepositoryMapper.deleteLogicByPrimaryKey(id);
    }
    /**
     * @Title ckeck
     * @Description 判断是否可以连接上资源库
     * @param kRepository
     * @return
     * @throws KettleException
     * @return boolean
     */
    public boolean ckeck(KRepository kRepository) throws KettleException {
        KettleDatabaseRepository kettleDatabaseRepository = RepositoryUtil.connectionRepository(kRepository);
        if (kettleDatabaseRepository != null){
            if (kettleDatabaseRepository.isConnected()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 保存资源库连接信息
     * @param kRepository
     * @param
     */
    public int insertRepo(KRepository kRepository){
        String id = UUID.randomUUID().toString().replace("-","");
        kRepository.setId(id);
       /* long l = System.currentTimeMillis();
        kRepository.setRepositoryId(Integer.parseInt(l+""));*/
        kRepository.setAddTime(new Date());
        //kRepository.setAddUser(uId);
        kRepository.setEditTime(new Date());
        //kRepository.setEditUser(uId);
        kRepository.setDelFlag("1");
        return kRepositoryMapper.insert(kRepository);
    }
    /**
     * 查找资源库列表
     * @return
     */
    public List<KRepository> findAllList(){
        return kRepositoryMapper.findAllList();
    }

    /**
     * 根据资源库名称删除资源库
     * @param name
     */
    public void deleteByReposityName(String name) {
        KRepositoryExample example = new KRepositoryExample();
        example.createCriteria().andRepositoryNameEqualTo(name);
         kRepositoryMapper.deleteByExample(example);
    }
    /**
     * @Title getRepositoryTreeList
     * @Description 获取数据库资源库的树形菜单
     * @param repositoryId
     * @return
     * @throws KettleException
     * @return List<RepositoryTree>
     */
    public List<RepositoryTree> getTreeList(String repositoryId) throws KettleException {
        KettleDatabaseRepository kettleDatabaseRepository = null;
        List<RepositoryTree> allRepositoryTreeList = new ArrayList<RepositoryTree>();
        if (RepositoryUtil.KettleDatabaseRepositoryCatch.containsKey(repositoryId)){
            kettleDatabaseRepository = RepositoryUtil.KettleDatabaseRepositoryCatch.get(repositoryId);
        }else {
            KRepository kRepository = kRepositoryMapper.selectByPrimaryKey(repositoryId);
            kettleDatabaseRepository = RepositoryUtil.connectionRepository(kRepository);
        }
        if (null != kettleDatabaseRepository){
            List<RepositoryTree> repositoryTreeList = new ArrayList<RepositoryTree>();
            allRepositoryTreeList = RepositoryUtil.getAllDirectoryTreeList(kettleDatabaseRepository, "/", repositoryTreeList);
        }
        return allRepositoryTreeList;
    }
}
