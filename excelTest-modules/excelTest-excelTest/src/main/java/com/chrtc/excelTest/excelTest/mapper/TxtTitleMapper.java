package com.chrtc.excelTest.excelTest.mapper;

import com.chrtc.excelTest.excelTest.domain.TxtTitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TxtTitleMapper {
    int insert(TxtTitle txtTitle);

    void deleteByFileName(@Param("fileName") String fileName);

    List<String> selectByFileName(@Param("fileName") String fileName);
}
