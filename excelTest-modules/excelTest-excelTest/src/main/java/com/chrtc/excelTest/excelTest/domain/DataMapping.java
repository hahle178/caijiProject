package com.chrtc.excelTest.excelTest.domain;


import com.chrtc.common.base.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Size;
import java.util.Date;

public class DataMapping extends BaseEntity {
    @Size(max = 32)
    private String id;
    @Size(max = 50)
    private String key1;
    @Size(max = 50)
    private String eng;
    @Size(max = 50)
    private String DDataSet;
    @Size(max = 50)
    private String SDataSet;
    /**创建人名称*/
    @Size(max=50)
    private String createName;

    /**创建人登录名称*/
    @Size(max=50)
    private String createBy;
    /**创建日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**更新日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    /**更新人名称*/
    @Size(max=50)
    private String updateName;

    /**更新人登录名称*/
    @Size(max=50)
    private String updateBy;

    @Override
    public String getCreateName() {
        return createName;
    }

    @Override
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String getUpdateName() {
        return updateName;
    }

    @Override
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key1;
    }

    public void setKey(String key1) {
        this.key1 = key1;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getDDataSet() {
        return DDataSet;
    }

    public void setDDataSet(String DDataSet) {
        this.DDataSet = DDataSet;
    }

    public String getSDataSet() {
        return SDataSet;
    }

    public void setSDataSet(String SDataSet) {
        this.SDataSet = SDataSet;
    }
}
