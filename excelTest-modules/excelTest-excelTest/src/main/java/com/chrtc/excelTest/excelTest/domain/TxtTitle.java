package com.chrtc.excelTest.excelTest.domain;


import com.chrtc.common.base.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Size;
import java.util.Date;

public class TxtTitle extends BaseEntity {
    @Size(max = 11)
    private String id;
    @Size(max = 50)
    private String dataCode;
    @Size(max = 50)
    private String fileName;
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
    @Size(max=1)
    private String delFlag;
    @Size(max=11)
    private Integer versionNum;

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public Integer getVersionNum() {
        return versionNum;
    }

    @Override
    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

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

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
