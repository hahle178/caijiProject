package com.chrtc.excelTest.excelTest.mapper;

import com.chrtc.excelTest.excelTest.domain.DataMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DataMappingMapper {

    /**
    * 插入bcpMessage信息，参数属性值是不是不为空都插入
    * @param
    * @return
    */
    int insert(DataMapping dataMapping);

    List<DataMapping> selectBySDateSet(@Param("sDateSet") String sDateSet);
}
