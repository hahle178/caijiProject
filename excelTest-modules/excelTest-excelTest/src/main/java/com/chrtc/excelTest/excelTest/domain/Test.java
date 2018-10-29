package com.chrtc.excelTest.excelTest.domain;

import java.util.Arrays;

public class Test {
    private String[]  fieldName;
    private String[]  fieldValue;
    private String[]  fieldRemark;


    public String[] getFieldName() {
        return fieldName;
    }

    public void setFieldName(String[] fieldName) {
        this.fieldName = fieldName;
    }

    public String[] getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String[] fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String[] getFieldRemark() {
        return fieldRemark;
    }

    public void setFieldRemark(String[] fieldRemark) {
        this.fieldRemark = fieldRemark;
    }

    @Override
    public String toString() {
        return "Test{" +
                "fieldName=" + Arrays.toString(fieldName) +
                ", fieldValue=" + Arrays.toString(fieldValue) +
                ", fieldRemark=" + Arrays.toString(fieldRemark) +
                '}';
    }
}
