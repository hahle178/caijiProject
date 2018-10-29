package com.chrtc.excelTest.excelTest.mapper.kettle;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepository;
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.chrtc.common.mybatis.plugin.optimisticlocker.VersionLocker;

import java.util.List;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 **<p>KRepositoryExample使用说明（下面以查询为例，更新、删除时的用法相同）：
 **KRepositoryExample example = new KRepositoryExample();
 **KRepositoryExample.Criteria c = example.createCriteria();
 **c.anDelflagEqualTo("0");//设置and条件delflag = '0'
 **c.andUpdateByLike("%"+"更新人账号"+"%");//设置and条件update_by like %value%
 **c.andCreateByEqualTo("创建人账号");//设置and条件create_by = ?
 **Date value1 = xxxx;//开始时间
 **Date value2 = xxxx;//结束时间
 **c.andCreateDateBetween(value1, value2);//设置and条件between value1 and value2
 **List<KRepository> list = selectByExample(example);
 **</p>
 */
@Mapper
public interface KRepositoryMapper {

    /**
    * 获取符合example条件的k_repository信息的数量
    * @param example
    * @return
    */
    int countByExample(KRepositoryExample example);

    /**
    * 删除获取符合example条件的k_repository信息
    * @param example
    * @return
    */
    int deleteByExample(KRepositoryExample example);

    /**
    * 根据Id删除k_repository信息
    * @param id
    * @return
    */
    int deleteByPrimaryKey(String id);

    /**
    * 插入k_repository信息，参数属性值是不是不为空都插入
    * @param record
    * @return
    */
    int insert(KRepository record);

    /**
    * 插入k_repository信息，参数属性值不为空的插入
    * @param record
    * @return
    */
    int insertSelective(KRepository record);

    /**
    * 获取符合example条件的k_repository信息
    * @param example
    * @return
    */
    List<KRepository> selectByExample(KRepositoryExample example);

    /**
    * 根据Id获取k_repository信息
    * @param id
    * @return
    */
    KRepository selectByPrimaryKey(String id);

    /**
    * 根据example条件更新k_repository信息，仅仅更新参数属性值不为空的
    * @param record
    * @param example
    * @return
    */
    int updateByExampleSelective(@Param("record") KRepository record, @Param("example") KRepositoryExample example);

    /**
    * 根据example条件更新k_repository信息
    * @param record
    * @param example
    * @return
    */
    int updateByExample(@Param("record") KRepository record, @Param("example") KRepositoryExample example);

    /**
    * 根据主键更新k_repository信息，仅仅更新参数属性值不为空的
    * @param record
    * @return
    */
    int updateByPrimaryKeySelective(KRepository record);

    /**
    * 根据主键更新k_repository信息
    * @param record
    * @return
    */
    int updateByPrimaryKey(KRepository record);



    /**
    * 用分页方式获取k_repository信息
    * @param searchParams 查询参数
    * @return 列表
    */
    Paging findAllByPage(@Param("example") KRepositoryExample searchParams, @Param("pageNum") int pageNumber, @Param("pageSize") int pageSize, @Param("sort") String sort);

    /**
    * 根据Id删除k_repository信息
    * @param id
    * @return
    */
    @VersionLocker(value = false)
    int deleteLogicByPrimaryKey(String id);

    /**
     * 查询所有资源库
     * @return
     */
     List<KRepository> findAllList();
}
