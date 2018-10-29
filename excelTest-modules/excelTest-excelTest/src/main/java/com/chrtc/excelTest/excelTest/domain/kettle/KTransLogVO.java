package com.chrtc.excelTest.excelTest.domain.kettle;

public class KTransLogVO {

    private Integer transAllNum;//总转换任务数
    private Integer transAllSuccess;//总成功任务数
    private Integer transAllFail;//总失败任务数
    private String transNo;//记录编号
    private String transName;//转换名称
    private Integer transSuccess;//转换执行成功次数
    private Integer transFail;//转换执行失败次数

    public Integer getTransAllNum() {
        return transAllNum;
    }

    public void setTransAllNum(Integer transAllNum) {
        this.transAllNum = transAllNum;
    }

    public Integer getTransAllSuccess() {
        return transAllSuccess;
    }

    public void setTransAllSuccess(Integer transAllSuccess) {
        this.transAllSuccess = transAllSuccess;
    }

    public Integer getTransAllFail() {
        return transAllFail;
    }

    public void setTransAllFail(Integer transAllFail) {
        this.transAllFail = transAllFail;
    }

    public Integer getTransSuccess() {
        return transSuccess;
    }

    public void setTransSuccess(Integer transSuccess) {
        this.transSuccess = transSuccess;
    }

    public Integer getTransFail() {
        return transFail;
    }

    public void setTransFail(Integer transFail) {
        this.transFail = transFail;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }
}
