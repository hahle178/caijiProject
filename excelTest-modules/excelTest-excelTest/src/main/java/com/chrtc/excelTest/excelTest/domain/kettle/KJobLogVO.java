package com.chrtc.excelTest.excelTest.domain.kettle;

public class KJobLogVO {

    private Integer jobAllNum;//总转换任务数
    private Integer jobAllSuccess;//总成功任务数
    private Integer jobAllFail;//总失败任务数
    private String jobNo;//记录编号
    private String jobName;//转换名称
    private Integer jobSuccess;//转换执行成功次数
    private Integer jobFail;//转换执行失败次数

    public Integer getJobAllNum() {
        return jobAllNum;
    }

    public void setJobAllNum(Integer jobAllNum) {
        this.jobAllNum = jobAllNum;
    }

    public Integer getJobAllSuccess() {
        return jobAllSuccess;
    }

    public void setJobAllSuccess(Integer jobAllSuccess) {
        this.jobAllSuccess = jobAllSuccess;
    }

    public Integer getJobAllFail() {
        return jobAllFail;
    }

    public void setJobAllFail(Integer jobAllFail) {
        this.jobAllFail = jobAllFail;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobSuccess() {
        return jobSuccess;
    }

    public void setJobSuccess(Integer jobSuccess) {
        this.jobSuccess = jobSuccess;
    }

    public Integer getJobFail() {
        return jobFail;
    }

    public void setJobFail(Integer jobFail) {
        this.jobFail = jobFail;
    }
}
