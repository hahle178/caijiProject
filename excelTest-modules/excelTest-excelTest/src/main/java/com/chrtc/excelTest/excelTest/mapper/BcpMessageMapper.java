package com.chrtc.excelTest.excelTest.mapper;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.BcpMessage;
import com.chrtc.excelTest.excelTest.domain.BcpMessageExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.chrtc.common.mybatis.plugin.optimisticlocker.VersionLocker;

import java.util.List;
import java.util.Map;

/**
 * Created by AUTO on 2018-06-08 14:03:31.
 **<p>BcpMessageExample使用说明（下面以查询为例，更新、删除时的用法相同）：
 **BcpMessageExample example = new BcpMessageExample();
 **BcpMessageExample.Criteria c = example.createCriteria();
 **c.anDelflagEqualTo("0");//设置and条件delflag = '0'
 **c.andUpdateByLike("%"+"更新人账号"+"%");//设置and条件update_by like %value%
 **c.andCreateByEqualTo("创建人账号");//设置and条件create_by = ?
 **Date value1 = xxxx;//开始时间
 **Date value2 = xxxx;//结束时间
 **c.andCreateDateBetween(value1, value2);//设置and条件between value1 and value2
 **List<BcpMessage> list = selectByExample(example);
 **</p>
 */
@Mapper
public interface BcpMessageMapper {

    /**
    * 获取符合example条件的bcpMessage信息的数量
    * @param example
    * @return
    */
    int countByExample(BcpMessageExample example);

    /**
    * 删除获取符合example条件的bcpMessage信息
    * @param example
    * @return
    */
    int deleteByExample(BcpMessageExample example);

    /**
    * 根据Id删除bcpMessage信息
    * @param id
    * @return
    */
    int deleteByPrimaryKey(String id);

    /**
    * 插入bcpMessage信息，参数属性值是不是不为空都插入
    * @param record
    * @return
    */
    int insert(BcpMessage record);

    /**
    * 插入bcpMessage信息，参数属性值不为空的插入
    * @param record
    * @return
    */
    int insertSelective(BcpMessage record);

    /**
    * 获取符合example条件的bcpMessage信息
    * @param example
    * @return
    */
    List<BcpMessage> selectByExample(BcpMessageExample example);

    /**
    * 根据Id获取bcpMessage信息
    * @param id
    * @return
    */
    BcpMessage selectByPrimaryKey(String id);

    /**
    * 根据example条件更新bcpMessage信息，仅仅更新参数属性值不为空的
    * @param record
    * @param example
    * @return
    */
    int updateByExampleSelective(@Param("record") BcpMessage record, @Param("example") BcpMessageExample example);

    /**
    * 根据example条件更新bcpMessage信息
    * @param record
    * @param example
    * @return
    */
    int updateByExample(@Param("record") BcpMessage record, @Param("example") BcpMessageExample example);

    /**
    * 根据主键更新bcpMessage信息，仅仅更新参数属性值不为空的
    * @param record
    * @return
    */
    int updateByPrimaryKeySelective(BcpMessage record);

    /**
    * 根据主键更新bcpMessage信息
    * @param record
    * @return
    */
    int updateByPrimaryKey(BcpMessage record);



    /**
    * 用分页方式获取bcpMessage信息
    * @param searchParams 查询参数
    * @return 列表
    */
    Paging findAllByPage(@Param("example") BcpMessageExample searchParams, @Param("pageNum")int pageNumber, @Param("pageSize")int pageSize, @Param("sort")String sort);

    /**
    * 根据Id删除bcpMessage信息
    * @param id
    * @return
    */
    @VersionLocker(value = false)
    int deleteLogicByPrimaryKey(String id);

}
