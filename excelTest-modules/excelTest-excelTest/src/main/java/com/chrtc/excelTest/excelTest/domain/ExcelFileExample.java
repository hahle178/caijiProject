package com.chrtc.excelTest.excelTest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by AUTO on 2018-06-12 11:05:30.
 * excelFile where
 */
public class ExcelFileExample{
protected String orderByClause;

protected boolean distinct;

protected List<Criteria> oredCriteria;

    public ExcelFileExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

     public String getOrderByClause() {
        return orderByClause;
     }

     public void setDistinct(boolean distinct) {
        this.distinct = distinct;
     }

     public boolean isDistinct() {
        return distinct;
     }

     public List<Criteria> getOredCriteria() {
        return oredCriteria;
     }

     public void or(Criteria criteria) {
        oredCriteria.add(criteria);
     }

     public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
     }

     public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
     }

     protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
     }

     public void clear() {
         oredCriteria.clear();
         orderByClause = null;
         distinct = false;
     }


     protected abstract static class GeneratedCriteria {
            protected List<Criterion> criteria;

            protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            }

            public boolean isValid() {
                return criteria.size() > 0;
            }

            public List<Criterion> getAllCriteria() {
                return criteria;
            }

                        public List<Criterion> getCriteria() {
                            return criteria;
                            }

                            protected void addCriterion(String condition) {
                            if (condition == null) {
                            throw new RuntimeException("Value for condition cannot be null");
                            }
                            criteria.add(new Criterion(condition));
                            }

                            protected void addCriterion(String condition, Object value, String property) {
                            if (value == null) {
                            throw new RuntimeException("Value for " + property + " cannot be null");
                            }
                            criteria.add(new Criterion(condition, value));
                            }

            protected void addCriterion(String condition, Object value1, Object value2, String property) {
                if (value1 == null || value2 == null) {
                  throw new RuntimeException("Between values for " + property + " cannot be null");
                }
                   criteria.add(new Criterion(condition, value1, value2));
            }
                            public Criteria andIdIsNull() {
                            addCriterion("id is null");
                            return (Criteria) this;
                            }

                            public Criteria andIdIsNotNull() {
                            addCriterion("id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andIdEqualTo(String value) {
                            addCriterion("id =", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdNotEqualTo(String value) {
                            addCriterion("id <>", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdGreaterThan(String value) {
                            addCriterion("id >", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdGreaterThanOrEqualTo(String value) {
                            addCriterion("id >=", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdLessThan(String value) {
                            addCriterion("id <", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdLessThanOrEqualTo(String value) {
                            addCriterion("id <=", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdLike(String value) {
                            addCriterion("id like", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdNotLike(String value) {
                            addCriterion("id not like", value, "id");
                            return (Criteria) this;
                            }

                            public Criteria andIdIn(List<String> values) {
                                addCriterion("id in", values, "id");
                                return (Criteria) this;
                            }

                            public Criteria andIdNotIn(List<String> values) {
                                addCriterion("id not in", values, "id");
                                return (Criteria) this;
                            }

                            public Criteria andIdBetween(String value1, String value2) {
                                addCriterion("id between", value1, value2, "id");
                                return (Criteria) this;
                             }

                             public Criteria andIdNotBetween(String value1, String value2) {
                                addCriterion("id not between", value1, value2, "id");
                                return (Criteria) this;
                             }
                            public Criteria andCreateNameIsNull() {
                            addCriterion("create_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameIsNotNull() {
                            addCriterion("create_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameEqualTo(String value) {
                            addCriterion("create_name =", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameNotEqualTo(String value) {
                            addCriterion("create_name <>", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameGreaterThan(String value) {
                            addCriterion("create_name >", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
                            addCriterion("create_name >=", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameLessThan(String value) {
                            addCriterion("create_name <", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameLessThanOrEqualTo(String value) {
                            addCriterion("create_name <=", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameLike(String value) {
                            addCriterion("create_name like", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameNotLike(String value) {
                            addCriterion("create_name not like", value, "createName");
                            return (Criteria) this;
                            }

                            public Criteria andCreateNameIn(List<String> values) {
                                addCriterion("create_name in", values, "createName");
                                return (Criteria) this;
                            }

                            public Criteria andCreateNameNotIn(List<String> values) {
                                addCriterion("create_name not in", values, "createName");
                                return (Criteria) this;
                            }

                            public Criteria andCreateNameBetween(String value1, String value2) {
                                addCriterion("create_name between", value1, value2, "createName");
                                return (Criteria) this;
                             }

                             public Criteria andCreateNameNotBetween(String value1, String value2) {
                                addCriterion("create_name not between", value1, value2, "createName");
                                return (Criteria) this;
                             }
                            public Criteria andCreateByIsNull() {
                            addCriterion("create_by is null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByIsNotNull() {
                            addCriterion("create_by is not null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByEqualTo(String value) {
                            addCriterion("create_by =", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByNotEqualTo(String value) {
                            addCriterion("create_by <>", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByGreaterThan(String value) {
                            addCriterion("create_by >", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByGreaterThanOrEqualTo(String value) {
                            addCriterion("create_by >=", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByLessThan(String value) {
                            addCriterion("create_by <", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByLessThanOrEqualTo(String value) {
                            addCriterion("create_by <=", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByLike(String value) {
                            addCriterion("create_by like", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByNotLike(String value) {
                            addCriterion("create_by not like", value, "createBy");
                            return (Criteria) this;
                            }

                            public Criteria andCreateByIn(List<String> values) {
                                addCriterion("create_by in", values, "createBy");
                                return (Criteria) this;
                            }

                            public Criteria andCreateByNotIn(List<String> values) {
                                addCriterion("create_by not in", values, "createBy");
                                return (Criteria) this;
                            }

                            public Criteria andCreateByBetween(String value1, String value2) {
                                addCriterion("create_by between", value1, value2, "createBy");
                                return (Criteria) this;
                             }

                             public Criteria andCreateByNotBetween(String value1, String value2) {
                                addCriterion("create_by not between", value1, value2, "createBy");
                                return (Criteria) this;
                             }
                            public Criteria andCreateDateIsNull() {
                            addCriterion("create_date is null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateIsNotNull() {
                            addCriterion("create_date is not null");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateEqualTo(Date value) {
                            addCriterion("create_date =", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateNotEqualTo(Date value) {
                            addCriterion("create_date <>", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateGreaterThan(Date value) {
                            addCriterion("create_date >", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
                            addCriterion("create_date >=", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateLessThan(Date value) {
                            addCriterion("create_date <", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateLessThanOrEqualTo(Date value) {
                            addCriterion("create_date <=", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateLike(Date value) {
                            addCriterion("create_date like", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateNotLike(Date value) {
                            addCriterion("create_date not like", value, "createDate");
                            return (Criteria) this;
                            }

                            public Criteria andCreateDateIn(List<Date> values) {
                                addCriterion("create_date in", values, "createDate");
                                return (Criteria) this;
                            }

                            public Criteria andCreateDateNotIn(List<Date> values) {
                                addCriterion("create_date not in", values, "createDate");
                                return (Criteria) this;
                            }

                            public Criteria andCreateDateBetween(Date value1, Date value2) {
                                addCriterion("create_date between", value1, value2, "createDate");
                                return (Criteria) this;
                             }

                             public Criteria andCreateDateNotBetween(Date value1, Date value2) {
                                addCriterion("create_date not between", value1, value2, "createDate");
                                return (Criteria) this;
                             }
                            public Criteria andUpdateNameIsNull() {
                            addCriterion("update_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameIsNotNull() {
                            addCriterion("update_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameEqualTo(String value) {
                            addCriterion("update_name =", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameNotEqualTo(String value) {
                            addCriterion("update_name <>", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameGreaterThan(String value) {
                            addCriterion("update_name >", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameGreaterThanOrEqualTo(String value) {
                            addCriterion("update_name >=", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameLessThan(String value) {
                            addCriterion("update_name <", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameLessThanOrEqualTo(String value) {
                            addCriterion("update_name <=", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameLike(String value) {
                            addCriterion("update_name like", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameNotLike(String value) {
                            addCriterion("update_name not like", value, "updateName");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateNameIn(List<String> values) {
                                addCriterion("update_name in", values, "updateName");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateNameNotIn(List<String> values) {
                                addCriterion("update_name not in", values, "updateName");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateNameBetween(String value1, String value2) {
                                addCriterion("update_name between", value1, value2, "updateName");
                                return (Criteria) this;
                             }

                             public Criteria andUpdateNameNotBetween(String value1, String value2) {
                                addCriterion("update_name not between", value1, value2, "updateName");
                                return (Criteria) this;
                             }
                            public Criteria andUpdateByIsNull() {
                            addCriterion("update_by is null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByIsNotNull() {
                            addCriterion("update_by is not null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByEqualTo(String value) {
                            addCriterion("update_by =", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByNotEqualTo(String value) {
                            addCriterion("update_by <>", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByGreaterThan(String value) {
                            addCriterion("update_by >", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
                            addCriterion("update_by >=", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByLessThan(String value) {
                            addCriterion("update_by <", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByLessThanOrEqualTo(String value) {
                            addCriterion("update_by <=", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByLike(String value) {
                            addCriterion("update_by like", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByNotLike(String value) {
                            addCriterion("update_by not like", value, "updateBy");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateByIn(List<String> values) {
                                addCriterion("update_by in", values, "updateBy");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateByNotIn(List<String> values) {
                                addCriterion("update_by not in", values, "updateBy");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateByBetween(String value1, String value2) {
                                addCriterion("update_by between", value1, value2, "updateBy");
                                return (Criteria) this;
                             }

                             public Criteria andUpdateByNotBetween(String value1, String value2) {
                                addCriterion("update_by not between", value1, value2, "updateBy");
                                return (Criteria) this;
                             }
                            public Criteria andUpdateDateIsNull() {
                            addCriterion("update_date is null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateIsNotNull() {
                            addCriterion("update_date is not null");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateEqualTo(Date value) {
                            addCriterion("update_date =", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateNotEqualTo(Date value) {
                            addCriterion("update_date <>", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateGreaterThan(Date value) {
                            addCriterion("update_date >", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
                            addCriterion("update_date >=", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateLessThan(Date value) {
                            addCriterion("update_date <", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
                            addCriterion("update_date <=", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateLike(Date value) {
                            addCriterion("update_date like", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateNotLike(Date value) {
                            addCriterion("update_date not like", value, "updateDate");
                            return (Criteria) this;
                            }

                            public Criteria andUpdateDateIn(List<Date> values) {
                                addCriterion("update_date in", values, "updateDate");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateDateNotIn(List<Date> values) {
                                addCriterion("update_date not in", values, "updateDate");
                                return (Criteria) this;
                            }

                            public Criteria andUpdateDateBetween(Date value1, Date value2) {
                                addCriterion("update_date between", value1, value2, "updateDate");
                                return (Criteria) this;
                             }

                             public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
                                addCriterion("update_date not between", value1, value2, "updateDate");
                                return (Criteria) this;
                             }
                            public Criteria andVersionNumIsNull() {
                            addCriterion("version_num is null");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumIsNotNull() {
                            addCriterion("version_num is not null");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumEqualTo(Integer value) {
                            addCriterion("version_num =", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumNotEqualTo(Integer value) {
                            addCriterion("version_num <>", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumGreaterThan(Integer value) {
                            addCriterion("version_num >", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumGreaterThanOrEqualTo(Integer value) {
                            addCriterion("version_num >=", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumLessThan(Integer value) {
                            addCriterion("version_num <", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumLessThanOrEqualTo(Integer value) {
                            addCriterion("version_num <=", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumLike(Integer value) {
                            addCriterion("version_num like", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumNotLike(Integer value) {
                            addCriterion("version_num not like", value, "versionNum");
                            return (Criteria) this;
                            }

                            public Criteria andVersionNumIn(List<Integer> values) {
                                addCriterion("version_num in", values, "versionNum");
                                return (Criteria) this;
                            }

                            public Criteria andVersionNumNotIn(List<Integer> values) {
                                addCriterion("version_num not in", values, "versionNum");
                                return (Criteria) this;
                            }

                            public Criteria andVersionNumBetween(Integer value1, Integer value2) {
                                addCriterion("version_num between", value1, value2, "versionNum");
                                return (Criteria) this;
                             }

                             public Criteria andVersionNumNotBetween(Integer value1, Integer value2) {
                                addCriterion("version_num not between", value1, value2, "versionNum");
                                return (Criteria) this;
                             }
                            public Criteria andSysOrgCodeIsNull() {
                            addCriterion("sys_org_code is null");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeIsNotNull() {
                            addCriterion("sys_org_code is not null");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeEqualTo(String value) {
                            addCriterion("sys_org_code =", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeNotEqualTo(String value) {
                            addCriterion("sys_org_code <>", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeGreaterThan(String value) {
                            addCriterion("sys_org_code >", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeGreaterThanOrEqualTo(String value) {
                            addCriterion("sys_org_code >=", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeLessThan(String value) {
                            addCriterion("sys_org_code <", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeLessThanOrEqualTo(String value) {
                            addCriterion("sys_org_code <=", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeLike(String value) {
                            addCriterion("sys_org_code like", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeNotLike(String value) {
                            addCriterion("sys_org_code not like", value, "sysOrgCode");
                            return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeIn(List<String> values) {
                                addCriterion("sys_org_code in", values, "sysOrgCode");
                                return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeNotIn(List<String> values) {
                                addCriterion("sys_org_code not in", values, "sysOrgCode");
                                return (Criteria) this;
                            }

                            public Criteria andSysOrgCodeBetween(String value1, String value2) {
                                addCriterion("sys_org_code between", value1, value2, "sysOrgCode");
                                return (Criteria) this;
                             }

                             public Criteria andSysOrgCodeNotBetween(String value1, String value2) {
                                addCriterion("sys_org_code not between", value1, value2, "sysOrgCode");
                                return (Criteria) this;
                             }
                            public Criteria andDelFlagIsNull() {
                            addCriterion("del_flag is null");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagIsNotNull() {
                            addCriterion("del_flag is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagEqualTo(String value) {
                            addCriterion("del_flag =", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagNotEqualTo(String value) {
                            addCriterion("del_flag <>", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagGreaterThan(String value) {
                            addCriterion("del_flag >", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
                            addCriterion("del_flag >=", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagLessThan(String value) {
                            addCriterion("del_flag <", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagLessThanOrEqualTo(String value) {
                            addCriterion("del_flag <=", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagLike(String value) {
                            addCriterion("del_flag like", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagNotLike(String value) {
                            addCriterion("del_flag not like", value, "delFlag");
                            return (Criteria) this;
                            }

                            public Criteria andDelFlagIn(List<String> values) {
                                addCriterion("del_flag in", values, "delFlag");
                                return (Criteria) this;
                            }

                            public Criteria andDelFlagNotIn(List<String> values) {
                                addCriterion("del_flag not in", values, "delFlag");
                                return (Criteria) this;
                            }

                            public Criteria andDelFlagBetween(String value1, String value2) {
                                addCriterion("del_flag between", value1, value2, "delFlag");
                                return (Criteria) this;
                             }

                             public Criteria andDelFlagNotBetween(String value1, String value2) {
                                addCriterion("del_flag not between", value1, value2, "delFlag");
                                return (Criteria) this;
                             }
                            public Criteria andObligateAIsNull() {
                            addCriterion("obligate_a is null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateAIsNotNull() {
                            addCriterion("obligate_a is not null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateAEqualTo(String value) {
                            addCriterion("obligate_a =", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateANotEqualTo(String value) {
                            addCriterion("obligate_a <>", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateAGreaterThan(String value) {
                            addCriterion("obligate_a >", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateAGreaterThanOrEqualTo(String value) {
                            addCriterion("obligate_a >=", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateALessThan(String value) {
                            addCriterion("obligate_a <", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateALessThanOrEqualTo(String value) {
                            addCriterion("obligate_a <=", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateALike(String value) {
                            addCriterion("obligate_a like", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateANotLike(String value) {
                            addCriterion("obligate_a not like", value, "obligateA");
                            return (Criteria) this;
                            }

                            public Criteria andObligateAIn(List<String> values) {
                                addCriterion("obligate_a in", values, "obligateA");
                                return (Criteria) this;
                            }

                            public Criteria andObligateANotIn(List<String> values) {
                                addCriterion("obligate_a not in", values, "obligateA");
                                return (Criteria) this;
                            }

                            public Criteria andObligateABetween(String value1, String value2) {
                                addCriterion("obligate_a between", value1, value2, "obligateA");
                                return (Criteria) this;
                             }

                             public Criteria andObligateANotBetween(String value1, String value2) {
                                addCriterion("obligate_a not between", value1, value2, "obligateA");
                                return (Criteria) this;
                             }
                            public Criteria andObligateBIsNull() {
                            addCriterion("obligate_b is null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBIsNotNull() {
                            addCriterion("obligate_b is not null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBEqualTo(String value) {
                            addCriterion("obligate_b =", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBNotEqualTo(String value) {
                            addCriterion("obligate_b <>", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBGreaterThan(String value) {
                            addCriterion("obligate_b >", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBGreaterThanOrEqualTo(String value) {
                            addCriterion("obligate_b >=", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBLessThan(String value) {
                            addCriterion("obligate_b <", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBLessThanOrEqualTo(String value) {
                            addCriterion("obligate_b <=", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBLike(String value) {
                            addCriterion("obligate_b like", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBNotLike(String value) {
                            addCriterion("obligate_b not like", value, "obligateB");
                            return (Criteria) this;
                            }

                            public Criteria andObligateBIn(List<String> values) {
                                addCriterion("obligate_b in", values, "obligateB");
                                return (Criteria) this;
                            }

                            public Criteria andObligateBNotIn(List<String> values) {
                                addCriterion("obligate_b not in", values, "obligateB");
                                return (Criteria) this;
                            }

                            public Criteria andObligateBBetween(String value1, String value2) {
                                addCriterion("obligate_b between", value1, value2, "obligateB");
                                return (Criteria) this;
                             }

                             public Criteria andObligateBNotBetween(String value1, String value2) {
                                addCriterion("obligate_b not between", value1, value2, "obligateB");
                                return (Criteria) this;
                             }
                            public Criteria andObligateCIsNull() {
                            addCriterion("obligate_c is null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCIsNotNull() {
                            addCriterion("obligate_c is not null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCEqualTo(String value) {
                            addCriterion("obligate_c =", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCNotEqualTo(String value) {
                            addCriterion("obligate_c <>", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCGreaterThan(String value) {
                            addCriterion("obligate_c >", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCGreaterThanOrEqualTo(String value) {
                            addCriterion("obligate_c >=", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCLessThan(String value) {
                            addCriterion("obligate_c <", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCLessThanOrEqualTo(String value) {
                            addCriterion("obligate_c <=", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCLike(String value) {
                            addCriterion("obligate_c like", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCNotLike(String value) {
                            addCriterion("obligate_c not like", value, "obligateC");
                            return (Criteria) this;
                            }

                            public Criteria andObligateCIn(List<String> values) {
                                addCriterion("obligate_c in", values, "obligateC");
                                return (Criteria) this;
                            }

                            public Criteria andObligateCNotIn(List<String> values) {
                                addCriterion("obligate_c not in", values, "obligateC");
                                return (Criteria) this;
                            }

                            public Criteria andObligateCBetween(String value1, String value2) {
                                addCriterion("obligate_c between", value1, value2, "obligateC");
                                return (Criteria) this;
                             }

                             public Criteria andObligateCNotBetween(String value1, String value2) {
                                addCriterion("obligate_c not between", value1, value2, "obligateC");
                                return (Criteria) this;
                             }
                            public Criteria andObligateDIsNull() {
                            addCriterion("obligate_d is null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDIsNotNull() {
                            addCriterion("obligate_d is not null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDEqualTo(String value) {
                            addCriterion("obligate_d =", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDNotEqualTo(String value) {
                            addCriterion("obligate_d <>", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDGreaterThan(String value) {
                            addCriterion("obligate_d >", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDGreaterThanOrEqualTo(String value) {
                            addCriterion("obligate_d >=", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDLessThan(String value) {
                            addCriterion("obligate_d <", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDLessThanOrEqualTo(String value) {
                            addCriterion("obligate_d <=", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDLike(String value) {
                            addCriterion("obligate_d like", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDNotLike(String value) {
                            addCriterion("obligate_d not like", value, "obligateD");
                            return (Criteria) this;
                            }

                            public Criteria andObligateDIn(List<String> values) {
                                addCriterion("obligate_d in", values, "obligateD");
                                return (Criteria) this;
                            }

                            public Criteria andObligateDNotIn(List<String> values) {
                                addCriterion("obligate_d not in", values, "obligateD");
                                return (Criteria) this;
                            }

                            public Criteria andObligateDBetween(String value1, String value2) {
                                addCriterion("obligate_d between", value1, value2, "obligateD");
                                return (Criteria) this;
                             }

                             public Criteria andObligateDNotBetween(String value1, String value2) {
                                addCriterion("obligate_d not between", value1, value2, "obligateD");
                                return (Criteria) this;
                             }
                            public Criteria andObligateEIsNull() {
                            addCriterion("obligate_e is null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateEIsNotNull() {
                            addCriterion("obligate_e is not null");
                            return (Criteria) this;
                            }

                            public Criteria andObligateEEqualTo(String value) {
                            addCriterion("obligate_e =", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateENotEqualTo(String value) {
                            addCriterion("obligate_e <>", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateEGreaterThan(String value) {
                            addCriterion("obligate_e >", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateEGreaterThanOrEqualTo(String value) {
                            addCriterion("obligate_e >=", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateELessThan(String value) {
                            addCriterion("obligate_e <", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateELessThanOrEqualTo(String value) {
                            addCriterion("obligate_e <=", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateELike(String value) {
                            addCriterion("obligate_e like", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateENotLike(String value) {
                            addCriterion("obligate_e not like", value, "obligateE");
                            return (Criteria) this;
                            }

                            public Criteria andObligateEIn(List<String> values) {
                                addCriterion("obligate_e in", values, "obligateE");
                                return (Criteria) this;
                            }

                            public Criteria andObligateENotIn(List<String> values) {
                                addCriterion("obligate_e not in", values, "obligateE");
                                return (Criteria) this;
                            }

                            public Criteria andObligateEBetween(String value1, String value2) {
                                addCriterion("obligate_e between", value1, value2, "obligateE");
                                return (Criteria) this;
                             }

                             public Criteria andObligateENotBetween(String value1, String value2) {
                                addCriterion("obligate_e not between", value1, value2, "obligateE");
                                return (Criteria) this;
                             }
                            public Criteria andNoIsNull() {
                            addCriterion("no is null");
                            return (Criteria) this;
                            }

                            public Criteria andNoIsNotNull() {
                            addCriterion("no is not null");
                            return (Criteria) this;
                            }

                            public Criteria andNoEqualTo(String value) {
                            addCriterion("no =", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoNotEqualTo(String value) {
                            addCriterion("no <>", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoGreaterThan(String value) {
                            addCriterion("no >", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoGreaterThanOrEqualTo(String value) {
                            addCriterion("no >=", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoLessThan(String value) {
                            addCriterion("no <", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoLessThanOrEqualTo(String value) {
                            addCriterion("no <=", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoLike(String value) {
                            addCriterion("no like", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoNotLike(String value) {
                            addCriterion("no not like", value, "no");
                            return (Criteria) this;
                            }

                            public Criteria andNoIn(List<String> values) {
                                addCriterion("no in", values, "no");
                                return (Criteria) this;
                            }

                            public Criteria andNoNotIn(List<String> values) {
                                addCriterion("no not in", values, "no");
                                return (Criteria) this;
                            }

                            public Criteria andNoBetween(String value1, String value2) {
                                addCriterion("no between", value1, value2, "no");
                                return (Criteria) this;
                             }

                             public Criteria andNoNotBetween(String value1, String value2) {
                                addCriterion("no not between", value1, value2, "no");
                                return (Criteria) this;
                             }
                            public Criteria andNameIsNull() {
                            addCriterion("name is null");
                            return (Criteria) this;
                            }

                            public Criteria andNameIsNotNull() {
                            addCriterion("name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andNameEqualTo(String value) {
                            addCriterion("name =", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameNotEqualTo(String value) {
                            addCriterion("name <>", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameGreaterThan(String value) {
                            addCriterion("name >", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameGreaterThanOrEqualTo(String value) {
                            addCriterion("name >=", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameLessThan(String value) {
                            addCriterion("name <", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameLessThanOrEqualTo(String value) {
                            addCriterion("name <=", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameLike(String value) {
                            addCriterion("name like", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameNotLike(String value) {
                            addCriterion("name not like", value, "name");
                            return (Criteria) this;
                            }

                            public Criteria andNameIn(List<String> values) {
                                addCriterion("name in", values, "name");
                                return (Criteria) this;
                            }

                            public Criteria andNameNotIn(List<String> values) {
                                addCriterion("name not in", values, "name");
                                return (Criteria) this;
                            }

                            public Criteria andNameBetween(String value1, String value2) {
                                addCriterion("name between", value1, value2, "name");
                                return (Criteria) this;
                             }

                             public Criteria andNameNotBetween(String value1, String value2) {
                                addCriterion("name not between", value1, value2, "name");
                                return (Criteria) this;
                             }
                            public Criteria andKeyIsNull() {
                            addCriterion("key is null");
                            return (Criteria) this;
                            }

                            public Criteria andKeyIsNotNull() {
                            addCriterion("key is not null");
                            return (Criteria) this;
                            }

                            public Criteria andKeyEqualTo(String value) {
                            addCriterion("key =", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyNotEqualTo(String value) {
                            addCriterion("key <>", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyGreaterThan(String value) {
                            addCriterion("key >", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyGreaterThanOrEqualTo(String value) {
                            addCriterion("key >=", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyLessThan(String value) {
                            addCriterion("key <", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyLessThanOrEqualTo(String value) {
                            addCriterion("key <=", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyLike(String value) {
                            addCriterion("key like", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyNotLike(String value) {
                            addCriterion("key not like", value, "key");
                            return (Criteria) this;
                            }

                            public Criteria andKeyIn(List<String> values) {
                                addCriterion("key in", values, "key");
                                return (Criteria) this;
                            }

                            public Criteria andKeyNotIn(List<String> values) {
                                addCriterion("key not in", values, "key");
                                return (Criteria) this;
                            }

                            public Criteria andKeyBetween(String value1, String value2) {
                                addCriterion("key between", value1, value2, "key");
                                return (Criteria) this;
                             }

                             public Criteria andKeyNotBetween(String value1, String value2) {
                                addCriterion("key not between", value1, value2, "key");
                                return (Criteria) this;
                             }
                            public Criteria andValIsNull() {
                            addCriterion("val is null");
                            return (Criteria) this;
                            }

                            public Criteria andValIsNotNull() {
                            addCriterion("val is not null");
                            return (Criteria) this;
                            }

                            public Criteria andValEqualTo(String value) {
                            addCriterion("val =", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValNotEqualTo(String value) {
                            addCriterion("val <>", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValGreaterThan(String value) {
                            addCriterion("val >", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValGreaterThanOrEqualTo(String value) {
                            addCriterion("val >=", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValLessThan(String value) {
                            addCriterion("val <", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValLessThanOrEqualTo(String value) {
                            addCriterion("val <=", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValLike(String value) {
                            addCriterion("val like", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValNotLike(String value) {
                            addCriterion("val not like", value, "val");
                            return (Criteria) this;
                            }

                            public Criteria andValIn(List<String> values) {
                                addCriterion("val in", values, "val");
                                return (Criteria) this;
                            }

                            public Criteria andValNotIn(List<String> values) {
                                addCriterion("val not in", values, "val");
                                return (Criteria) this;
                            }

                            public Criteria andValBetween(String value1, String value2) {
                                addCriterion("val between", value1, value2, "val");
                                return (Criteria) this;
                             }

                             public Criteria andValNotBetween(String value1, String value2) {
                                addCriterion("val not between", value1, value2, "val");
                                return (Criteria) this;
                             }
                            public Criteria andVerIsNull() {
                            addCriterion("ver is null");
                            return (Criteria) this;
                            }

                            public Criteria andVerIsNotNull() {
                            addCriterion("ver is not null");
                            return (Criteria) this;
                            }

                            public Criteria andVerEqualTo(String value) {
                            addCriterion("ver =", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerNotEqualTo(String value) {
                            addCriterion("ver <>", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerGreaterThan(String value) {
                            addCriterion("ver >", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerGreaterThanOrEqualTo(String value) {
                            addCriterion("ver >=", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerLessThan(String value) {
                            addCriterion("ver <", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerLessThanOrEqualTo(String value) {
                            addCriterion("ver <=", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerLike(String value) {
                            addCriterion("ver like", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerNotLike(String value) {
                            addCriterion("ver not like", value, "ver");
                            return (Criteria) this;
                            }

                            public Criteria andVerIn(List<String> values) {
                                addCriterion("ver in", values, "ver");
                                return (Criteria) this;
                            }

                            public Criteria andVerNotIn(List<String> values) {
                                addCriterion("ver not in", values, "ver");
                                return (Criteria) this;
                            }

                            public Criteria andVerBetween(String value1, String value2) {
                                addCriterion("ver between", value1, value2, "ver");
                                return (Criteria) this;
                             }

                             public Criteria andVerNotBetween(String value1, String value2) {
                                addCriterion("ver not between", value1, value2, "ver");
                                return (Criteria) this;
                             }
                            public Criteria andRmkIsNull() {
                            addCriterion("rmk is null");
                            return (Criteria) this;
                            }

                            public Criteria andRmkIsNotNull() {
                            addCriterion("rmk is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRmkEqualTo(String value) {
                            addCriterion("rmk =", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkNotEqualTo(String value) {
                            addCriterion("rmk <>", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkGreaterThan(String value) {
                            addCriterion("rmk >", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkGreaterThanOrEqualTo(String value) {
                            addCriterion("rmk >=", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkLessThan(String value) {
                            addCriterion("rmk <", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkLessThanOrEqualTo(String value) {
                            addCriterion("rmk <=", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkLike(String value) {
                            addCriterion("rmk like", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkNotLike(String value) {
                            addCriterion("rmk not like", value, "rmk");
                            return (Criteria) this;
                            }

                            public Criteria andRmkIn(List<String> values) {
                                addCriterion("rmk in", values, "rmk");
                                return (Criteria) this;
                            }

                            public Criteria andRmkNotIn(List<String> values) {
                                addCriterion("rmk not in", values, "rmk");
                                return (Criteria) this;
                            }

                            public Criteria andRmkBetween(String value1, String value2) {
                                addCriterion("rmk between", value1, value2, "rmk");
                                return (Criteria) this;
                             }

                             public Criteria andRmkNotBetween(String value1, String value2) {
                                addCriterion("rmk not between", value1, value2, "rmk");
                                return (Criteria) this;
                             }
                            public Criteria andEngIsNull() {
                            addCriterion("eng is null");
                            return (Criteria) this;
                            }

                            public Criteria andEngIsNotNull() {
                            addCriterion("eng is not null");
                            return (Criteria) this;
                            }

                            public Criteria andEngEqualTo(String value) {
                            addCriterion("eng =", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngNotEqualTo(String value) {
                            addCriterion("eng <>", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngGreaterThan(String value) {
                            addCriterion("eng >", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngGreaterThanOrEqualTo(String value) {
                            addCriterion("eng >=", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngLessThan(String value) {
                            addCriterion("eng <", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngLessThanOrEqualTo(String value) {
                            addCriterion("eng <=", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngLike(String value) {
                            addCriterion("eng like", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngNotLike(String value) {
                            addCriterion("eng not like", value, "eng");
                            return (Criteria) this;
                            }

                            public Criteria andEngIn(List<String> values) {
                                addCriterion("eng in", values, "eng");
                                return (Criteria) this;
                            }

                            public Criteria andEngNotIn(List<String> values) {
                                addCriterion("eng not in", values, "eng");
                                return (Criteria) this;
                            }

                            public Criteria andEngBetween(String value1, String value2) {
                                addCriterion("eng between", value1, value2, "eng");
                                return (Criteria) this;
                             }

                             public Criteria andEngNotBetween(String value1, String value2) {
                                addCriterion("eng not between", value1, value2, "eng");
                                return (Criteria) this;
                             }
                            public Criteria andChnIsNull() {
                            addCriterion("chn is null");
                            return (Criteria) this;
                            }

                            public Criteria andChnIsNotNull() {
                            addCriterion("chn is not null");
                            return (Criteria) this;
                            }

                            public Criteria andChnEqualTo(String value) {
                            addCriterion("chn =", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnNotEqualTo(String value) {
                            addCriterion("chn <>", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnGreaterThan(String value) {
                            addCriterion("chn >", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnGreaterThanOrEqualTo(String value) {
                            addCriterion("chn >=", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnLessThan(String value) {
                            addCriterion("chn <", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnLessThanOrEqualTo(String value) {
                            addCriterion("chn <=", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnLike(String value) {
                            addCriterion("chn like", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnNotLike(String value) {
                            addCriterion("chn not like", value, "chn");
                            return (Criteria) this;
                            }

                            public Criteria andChnIn(List<String> values) {
                                addCriterion("chn in", values, "chn");
                                return (Criteria) this;
                            }

                            public Criteria andChnNotIn(List<String> values) {
                                addCriterion("chn not in", values, "chn");
                                return (Criteria) this;
                            }

                            public Criteria andChnBetween(String value1, String value2) {
                                addCriterion("chn between", value1, value2, "chn");
                                return (Criteria) this;
                             }

                             public Criteria andChnNotBetween(String value1, String value2) {
                                addCriterion("chn not between", value1, value2, "chn");
                                return (Criteria) this;
                             }
                            public Criteria andFmtIsNull() {
                            addCriterion("fmt is null");
                            return (Criteria) this;
                            }

                            public Criteria andFmtIsNotNull() {
                            addCriterion("fmt is not null");
                            return (Criteria) this;
                            }

                            public Criteria andFmtEqualTo(String value) {
                            addCriterion("fmt =", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtNotEqualTo(String value) {
                            addCriterion("fmt <>", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtGreaterThan(String value) {
                            addCriterion("fmt >", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtGreaterThanOrEqualTo(String value) {
                            addCriterion("fmt >=", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtLessThan(String value) {
                            addCriterion("fmt <", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtLessThanOrEqualTo(String value) {
                            addCriterion("fmt <=", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtLike(String value) {
                            addCriterion("fmt like", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtNotLike(String value) {
                            addCriterion("fmt not like", value, "fmt");
                            return (Criteria) this;
                            }

                            public Criteria andFmtIn(List<String> values) {
                                addCriterion("fmt in", values, "fmt");
                                return (Criteria) this;
                            }

                            public Criteria andFmtNotIn(List<String> values) {
                                addCriterion("fmt not in", values, "fmt");
                                return (Criteria) this;
                            }

                            public Criteria andFmtBetween(String value1, String value2) {
                                addCriterion("fmt between", value1, value2, "fmt");
                                return (Criteria) this;
                             }

                             public Criteria andFmtNotBetween(String value1, String value2) {
                                addCriterion("fmt not between", value1, value2, "fmt");
                                return (Criteria) this;
                             }
                            public Criteria andLabeldictionaryIsNull() {
                            addCriterion("labeldictionary is null");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryIsNotNull() {
                            addCriterion("labeldictionary is not null");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryEqualTo(String value) {
                            addCriterion("labeldictionary =", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryNotEqualTo(String value) {
                            addCriterion("labeldictionary <>", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryGreaterThan(String value) {
                            addCriterion("labeldictionary >", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryGreaterThanOrEqualTo(String value) {
                            addCriterion("labeldictionary >=", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryLessThan(String value) {
                            addCriterion("labeldictionary <", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryLessThanOrEqualTo(String value) {
                            addCriterion("labeldictionary <=", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryLike(String value) {
                            addCriterion("labeldictionary like", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryNotLike(String value) {
                            addCriterion("labeldictionary not like", value, "labeldictionary");
                            return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryIn(List<String> values) {
                                addCriterion("labeldictionary in", values, "labeldictionary");
                                return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryNotIn(List<String> values) {
                                addCriterion("labeldictionary not in", values, "labeldictionary");
                                return (Criteria) this;
                            }

                            public Criteria andLabeldictionaryBetween(String value1, String value2) {
                                addCriterion("labeldictionary between", value1, value2, "labeldictionary");
                                return (Criteria) this;
                             }

                             public Criteria andLabeldictionaryNotBetween(String value1, String value2) {
                                addCriterion("labeldictionary not between", value1, value2, "labeldictionary");
                                return (Criteria) this;
                             }
                            public Criteria andZipnameIsNull() {
                            addCriterion("zipname is null");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameIsNotNull() {
                            addCriterion("zipname is not null");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameEqualTo(String value) {
                            addCriterion("zipname =", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameNotEqualTo(String value) {
                            addCriterion("zipname <>", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameGreaterThan(String value) {
                            addCriterion("zipname >", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameGreaterThanOrEqualTo(String value) {
                            addCriterion("zipname >=", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameLessThan(String value) {
                            addCriterion("zipname <", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameLessThanOrEqualTo(String value) {
                            addCriterion("zipname <=", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameLike(String value) {
                            addCriterion("zipname like", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameNotLike(String value) {
                            addCriterion("zipname not like", value, "zipname");
                            return (Criteria) this;
                            }

                            public Criteria andZipnameIn(List<String> values) {
                                addCriterion("zipname in", values, "zipname");
                                return (Criteria) this;
                            }

                            public Criteria andZipnameNotIn(List<String> values) {
                                addCriterion("zipname not in", values, "zipname");
                                return (Criteria) this;
                            }

                            public Criteria andZipnameBetween(String value1, String value2) {
                                addCriterion("zipname between", value1, value2, "zipname");
                                return (Criteria) this;
                             }

                             public Criteria andZipnameNotBetween(String value1, String value2) {
                                addCriterion("zipname not between", value1, value2, "zipname");
                                return (Criteria) this;
                             }
         }


      public static class Criteria extends GeneratedCriteria {

protected Criteria() {
            super();
            }
      }

       public static class Criterion {
            private String condition;

            private Object value;

            private Object secondValue;

            private boolean noValue;

            private boolean singleValue;

            private boolean betweenValue;

            private boolean listValue;

            private String typeHandler;

            public String getCondition() {
            return condition;
            }

            public Object getValue() {
            return value;
            }

            public Object getSecondValue() {
            return secondValue;
            }

            public boolean isNoValue() {
            return noValue;
            }

            public boolean isSingleValue() {
            return singleValue;
            }

            public boolean isBetweenValue() {
            return betweenValue;
            }

            public boolean isListValue() {
            return listValue;
            }

            public String getTypeHandler() {
            return typeHandler;
            }

            protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
            }

            protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
            this.listValue = true;
            } else {
            this.singleValue = true;
            }
            }

            protected Criterion(String condition, Object value) {
            this(condition, value, null);
            }

            protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
            }

            protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
            }
        }
}