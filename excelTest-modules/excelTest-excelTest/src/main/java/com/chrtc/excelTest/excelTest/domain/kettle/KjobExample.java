package com.chrtc.excelTest.excelTest.domain.kettle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by AUTO on 2018-08-02 10:22:05.
 * k_job where
 */
public class KjobExample{
protected String orderByClause;

protected boolean distinct;

protected List<Criteria> oredCriteria;

    public KjobExample() {
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
                            public Criteria andJobIdIsNull() {
                            addCriterion("job_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdIsNotNull() {
                            addCriterion("job_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdEqualTo(String value) {
                            addCriterion("job_id =", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdNotEqualTo(String value) {
                            addCriterion("job_id <>", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdGreaterThan(String value) {
                            addCriterion("job_id >", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdGreaterThanOrEqualTo(String value) {
                            addCriterion("job_id >=", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdLessThan(String value) {
                            addCriterion("job_id <", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdLessThanOrEqualTo(String value) {
                            addCriterion("job_id <=", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdLike(String value) {
                            addCriterion("job_id like", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdNotLike(String value) {
                            addCriterion("job_id not like", value, "jobId");
                            return (Criteria) this;
                            }

                            public Criteria andJobIdIn(List<String> values) {
                                addCriterion("job_id in", values, "jobId");
                                return (Criteria) this;
                            }

                            public Criteria andJobIdNotIn(List<String> values) {
                                addCriterion("job_id not in", values, "jobId");
                                return (Criteria) this;
                            }

                            public Criteria andJobIdBetween(String value1, String value2) {
                                addCriterion("job_id between", value1, value2, "jobId");
                                return (Criteria) this;
                             }

                             public Criteria andJobIdNotBetween(String value1, String value2) {
                                addCriterion("job_id not between", value1, value2, "jobId");
                                return (Criteria) this;
                             }
                            public Criteria andJobNameIsNull() {
                            addCriterion("job_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameIsNotNull() {
                            addCriterion("job_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameEqualTo(String value) {
                            addCriterion("job_name =", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameNotEqualTo(String value) {
                            addCriterion("job_name <>", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameGreaterThan(String value) {
                            addCriterion("job_name >", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameGreaterThanOrEqualTo(String value) {
                            addCriterion("job_name >=", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameLessThan(String value) {
                            addCriterion("job_name <", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameLessThanOrEqualTo(String value) {
                            addCriterion("job_name <=", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameLike(String value) {
                            addCriterion("job_name like", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameNotLike(String value) {
                            addCriterion("job_name not like", value, "jobName");
                            return (Criteria) this;
                            }

                            public Criteria andJobNameIn(List<String> values) {
                                addCriterion("job_name in", values, "jobName");
                                return (Criteria) this;
                            }

                            public Criteria andJobNameNotIn(List<String> values) {
                                addCriterion("job_name not in", values, "jobName");
                                return (Criteria) this;
                            }

                            public Criteria andJobNameBetween(String value1, String value2) {
                                addCriterion("job_name between", value1, value2, "jobName");
                                return (Criteria) this;
                             }

                             public Criteria andJobNameNotBetween(String value1, String value2) {
                                addCriterion("job_name not between", value1, value2, "jobName");
                                return (Criteria) this;
                             }
                            public Criteria andJobDescriptionIsNull() {
                            addCriterion("job_description is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionIsNotNull() {
                            addCriterion("job_description is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionEqualTo(String value) {
                            addCriterion("job_description =", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionNotEqualTo(String value) {
                            addCriterion("job_description <>", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionGreaterThan(String value) {
                            addCriterion("job_description >", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionGreaterThanOrEqualTo(String value) {
                            addCriterion("job_description >=", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionLessThan(String value) {
                            addCriterion("job_description <", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionLessThanOrEqualTo(String value) {
                            addCriterion("job_description <=", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionLike(String value) {
                            addCriterion("job_description like", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionNotLike(String value) {
                            addCriterion("job_description not like", value, "jobDescription");
                            return (Criteria) this;
                            }

                            public Criteria andJobDescriptionIn(List<String> values) {
                                addCriterion("job_description in", values, "jobDescription");
                                return (Criteria) this;
                            }

                            public Criteria andJobDescriptionNotIn(List<String> values) {
                                addCriterion("job_description not in", values, "jobDescription");
                                return (Criteria) this;
                            }

                            public Criteria andJobDescriptionBetween(String value1, String value2) {
                                addCriterion("job_description between", value1, value2, "jobDescription");
                                return (Criteria) this;
                             }

                             public Criteria andJobDescriptionNotBetween(String value1, String value2) {
                                addCriterion("job_description not between", value1, value2, "jobDescription");
                                return (Criteria) this;
                             }
                            public Criteria andJobTypeIsNull() {
                            addCriterion("job_type is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeIsNotNull() {
                            addCriterion("job_type is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeEqualTo(String value) {
                            addCriterion("job_type =", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeNotEqualTo(String value) {
                            addCriterion("job_type <>", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeGreaterThan(String value) {
                            addCriterion("job_type >", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeGreaterThanOrEqualTo(String value) {
                            addCriterion("job_type >=", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeLessThan(String value) {
                            addCriterion("job_type <", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeLessThanOrEqualTo(String value) {
                            addCriterion("job_type <=", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeLike(String value) {
                            addCriterion("job_type like", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeNotLike(String value) {
                            addCriterion("job_type not like", value, "jobType");
                            return (Criteria) this;
                            }

                            public Criteria andJobTypeIn(List<String> values) {
                                addCriterion("job_type in", values, "jobType");
                                return (Criteria) this;
                            }

                            public Criteria andJobTypeNotIn(List<String> values) {
                                addCriterion("job_type not in", values, "jobType");
                                return (Criteria) this;
                            }

                            public Criteria andJobTypeBetween(String value1, String value2) {
                                addCriterion("job_type between", value1, value2, "jobType");
                                return (Criteria) this;
                             }

                             public Criteria andJobTypeNotBetween(String value1, String value2) {
                                addCriterion("job_type not between", value1, value2, "jobType");
                                return (Criteria) this;
                             }
                            public Criteria andJobPathIsNull() {
                            addCriterion("job_path is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathIsNotNull() {
                            addCriterion("job_path is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathEqualTo(String value) {
                            addCriterion("job_path =", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathNotEqualTo(String value) {
                            addCriterion("job_path <>", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathGreaterThan(String value) {
                            addCriterion("job_path >", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathGreaterThanOrEqualTo(String value) {
                            addCriterion("job_path >=", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathLessThan(String value) {
                            addCriterion("job_path <", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathLessThanOrEqualTo(String value) {
                            addCriterion("job_path <=", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathLike(String value) {
                            addCriterion("job_path like", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathNotLike(String value) {
                            addCriterion("job_path not like", value, "jobPath");
                            return (Criteria) this;
                            }

                            public Criteria andJobPathIn(List<String> values) {
                                addCriterion("job_path in", values, "jobPath");
                                return (Criteria) this;
                            }

                            public Criteria andJobPathNotIn(List<String> values) {
                                addCriterion("job_path not in", values, "jobPath");
                                return (Criteria) this;
                            }

                            public Criteria andJobPathBetween(String value1, String value2) {
                                addCriterion("job_path between", value1, value2, "jobPath");
                                return (Criteria) this;
                             }

                             public Criteria andJobPathNotBetween(String value1, String value2) {
                                addCriterion("job_path not between", value1, value2, "jobPath");
                                return (Criteria) this;
                             }
                            public Criteria andJobRepositoryIdIsNull() {
                            addCriterion("job_repository_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdIsNotNull() {
                            addCriterion("job_repository_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdEqualTo(String value) {
                            addCriterion("job_repository_id =", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdNotEqualTo(String value) {
                            addCriterion("job_repository_id <>", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdGreaterThan(String value) {
                            addCriterion("job_repository_id >", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdGreaterThanOrEqualTo(String value) {
                            addCriterion("job_repository_id >=", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdLessThan(String value) {
                            addCriterion("job_repository_id <", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdLessThanOrEqualTo(String value) {
                            addCriterion("job_repository_id <=", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdLike(String value) {
                            addCriterion("job_repository_id like", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdNotLike(String value) {
                            addCriterion("job_repository_id not like", value, "jobRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdIn(List<String> values) {
                                addCriterion("job_repository_id in", values, "jobRepositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdNotIn(List<String> values) {
                                addCriterion("job_repository_id not in", values, "jobRepositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andJobRepositoryIdBetween(String value1, String value2) {
                                addCriterion("job_repository_id between", value1, value2, "jobRepositoryId");
                                return (Criteria) this;
                             }

                             public Criteria andJobRepositoryIdNotBetween(String value1, String value2) {
                                addCriterion("job_repository_id not between", value1, value2, "jobRepositoryId");
                                return (Criteria) this;
                             }
                            public Criteria andJobQuartzIsNull() {
                            addCriterion("job_quartz is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzIsNotNull() {
                            addCriterion("job_quartz is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzEqualTo(String value) {
                            addCriterion("job_quartz =", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzNotEqualTo(String value) {
                            addCriterion("job_quartz <>", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzGreaterThan(String value) {
                            addCriterion("job_quartz >", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzGreaterThanOrEqualTo(String value) {
                            addCriterion("job_quartz >=", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzLessThan(String value) {
                            addCriterion("job_quartz <", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzLessThanOrEqualTo(String value) {
                            addCriterion("job_quartz <=", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzLike(String value) {
                            addCriterion("job_quartz like", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzNotLike(String value) {
                            addCriterion("job_quartz not like", value, "jobQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andJobQuartzIn(List<String> values) {
                                addCriterion("job_quartz in", values, "jobQuartz");
                                return (Criteria) this;
                            }

                            public Criteria andJobQuartzNotIn(List<String> values) {
                                addCriterion("job_quartz not in", values, "jobQuartz");
                                return (Criteria) this;
                            }

                            public Criteria andJobQuartzBetween(String value1, String value2) {
                                addCriterion("job_quartz between", value1, value2, "jobQuartz");
                                return (Criteria) this;
                             }

                             public Criteria andJobQuartzNotBetween(String value1, String value2) {
                                addCriterion("job_quartz not between", value1, value2, "jobQuartz");
                                return (Criteria) this;
                             }
                            public Criteria andJobRecordIsNull() {
                            addCriterion("job_record is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordIsNotNull() {
                            addCriterion("job_record is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordEqualTo(String value) {
                            addCriterion("job_record =", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordNotEqualTo(String value) {
                            addCriterion("job_record <>", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordGreaterThan(String value) {
                            addCriterion("job_record >", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordGreaterThanOrEqualTo(String value) {
                            addCriterion("job_record >=", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordLessThan(String value) {
                            addCriterion("job_record <", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordLessThanOrEqualTo(String value) {
                            addCriterion("job_record <=", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordLike(String value) {
                            addCriterion("job_record like", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordNotLike(String value) {
                            addCriterion("job_record not like", value, "jobRecord");
                            return (Criteria) this;
                            }

                            public Criteria andJobRecordIn(List<String> values) {
                                addCriterion("job_record in", values, "jobRecord");
                                return (Criteria) this;
                            }

                            public Criteria andJobRecordNotIn(List<String> values) {
                                addCriterion("job_record not in", values, "jobRecord");
                                return (Criteria) this;
                            }

                            public Criteria andJobRecordBetween(String value1, String value2) {
                                addCriterion("job_record between", value1, value2, "jobRecord");
                                return (Criteria) this;
                             }

                             public Criteria andJobRecordNotBetween(String value1, String value2) {
                                addCriterion("job_record not between", value1, value2, "jobRecord");
                                return (Criteria) this;
                             }
                            public Criteria andJobLogLevelIsNull() {
                            addCriterion("job_log_level is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelIsNotNull() {
                            addCriterion("job_log_level is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelEqualTo(String value) {
                            addCriterion("job_log_level =", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelNotEqualTo(String value) {
                            addCriterion("job_log_level <>", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelGreaterThan(String value) {
                            addCriterion("job_log_level >", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelGreaterThanOrEqualTo(String value) {
                            addCriterion("job_log_level >=", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelLessThan(String value) {
                            addCriterion("job_log_level <", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelLessThanOrEqualTo(String value) {
                            addCriterion("job_log_level <=", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelLike(String value) {
                            addCriterion("job_log_level like", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelNotLike(String value) {
                            addCriterion("job_log_level not like", value, "jobLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andJobLogLevelIn(List<String> values) {
                                addCriterion("job_log_level in", values, "jobLogLevel");
                                return (Criteria) this;
                            }

                            public Criteria andJobLogLevelNotIn(List<String> values) {
                                addCriterion("job_log_level not in", values, "jobLogLevel");
                                return (Criteria) this;
                            }

                            public Criteria andJobLogLevelBetween(String value1, String value2) {
                                addCriterion("job_log_level between", value1, value2, "jobLogLevel");
                                return (Criteria) this;
                             }

                             public Criteria andJobLogLevelNotBetween(String value1, String value2) {
                                addCriterion("job_log_level not between", value1, value2, "jobLogLevel");
                                return (Criteria) this;
                             }
                            public Criteria andJobStatusIsNull() {
                            addCriterion("job_status is null");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusIsNotNull() {
                            addCriterion("job_status is not null");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusEqualTo(String value) {
                            addCriterion("job_status =", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusNotEqualTo(String value) {
                            addCriterion("job_status <>", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusGreaterThan(String value) {
                            addCriterion("job_status >", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusGreaterThanOrEqualTo(String value) {
                            addCriterion("job_status >=", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusLessThan(String value) {
                            addCriterion("job_status <", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusLessThanOrEqualTo(String value) {
                            addCriterion("job_status <=", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusLike(String value) {
                            addCriterion("job_status like", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusNotLike(String value) {
                            addCriterion("job_status not like", value, "jobStatus");
                            return (Criteria) this;
                            }

                            public Criteria andJobStatusIn(List<String> values) {
                                addCriterion("job_status in", values, "jobStatus");
                                return (Criteria) this;
                            }

                            public Criteria andJobStatusNotIn(List<String> values) {
                                addCriterion("job_status not in", values, "jobStatus");
                                return (Criteria) this;
                            }

                            public Criteria andJobStatusBetween(String value1, String value2) {
                                addCriterion("job_status between", value1, value2, "jobStatus");
                                return (Criteria) this;
                             }

                             public Criteria andJobStatusNotBetween(String value1, String value2) {
                                addCriterion("job_status not between", value1, value2, "jobStatus");
                                return (Criteria) this;
                             }
                            public Criteria andAddTimeIsNull() {
                            addCriterion("add_time is null");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeIsNotNull() {
                            addCriterion("add_time is not null");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeEqualTo(String value) {
                            addCriterion("add_time =", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeNotEqualTo(String value) {
                            addCriterion("add_time <>", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeGreaterThan(String value) {
                            addCriterion("add_time >", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
                            addCriterion("add_time >=", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLessThan(String value) {
                            addCriterion("add_time <", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLessThanOrEqualTo(String value) {
                            addCriterion("add_time <=", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLike(String value) {
                            addCriterion("add_time like", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeNotLike(String value) {
                            addCriterion("add_time not like", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeIn(List<String> values) {
                                addCriterion("add_time in", values, "addTime");
                                return (Criteria) this;
                            }

                            public Criteria andAddTimeNotIn(List<String> values) {
                                addCriterion("add_time not in", values, "addTime");
                                return (Criteria) this;
                            }

                            public Criteria andAddTimeBetween(String value1, String value2) {
                                addCriterion("add_time between", value1, value2, "addTime");
                                return (Criteria) this;
                             }

                             public Criteria andAddTimeNotBetween(String value1, String value2) {
                                addCriterion("add_time not between", value1, value2, "addTime");
                                return (Criteria) this;
                             }
                            public Criteria andAddUserIsNull() {
                            addCriterion("add_user is null");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserIsNotNull() {
                            addCriterion("add_user is not null");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserEqualTo(String value) {
                            addCriterion("add_user =", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserNotEqualTo(String value) {
                            addCriterion("add_user <>", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserGreaterThan(String value) {
                            addCriterion("add_user >", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserGreaterThanOrEqualTo(String value) {
                            addCriterion("add_user >=", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLessThan(String value) {
                            addCriterion("add_user <", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLessThanOrEqualTo(String value) {
                            addCriterion("add_user <=", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLike(String value) {
                            addCriterion("add_user like", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserNotLike(String value) {
                            addCriterion("add_user not like", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserIn(List<String> values) {
                                addCriterion("add_user in", values, "addUser");
                                return (Criteria) this;
                            }

                            public Criteria andAddUserNotIn(List<String> values) {
                                addCriterion("add_user not in", values, "addUser");
                                return (Criteria) this;
                            }

                            public Criteria andAddUserBetween(String value1, String value2) {
                                addCriterion("add_user between", value1, value2, "addUser");
                                return (Criteria) this;
                             }

                             public Criteria andAddUserNotBetween(String value1, String value2) {
                                addCriterion("add_user not between", value1, value2, "addUser");
                                return (Criteria) this;
                             }
                            public Criteria andEditTimeIsNull() {
                            addCriterion("edit_time is null");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeIsNotNull() {
                            addCriterion("edit_time is not null");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeEqualTo(String value) {
                            addCriterion("edit_time =", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeNotEqualTo(String value) {
                            addCriterion("edit_time <>", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeGreaterThan(String value) {
                            addCriterion("edit_time >", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeGreaterThanOrEqualTo(String value) {
                            addCriterion("edit_time >=", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLessThan(String value) {
                            addCriterion("edit_time <", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLessThanOrEqualTo(String value) {
                            addCriterion("edit_time <=", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLike(String value) {
                            addCriterion("edit_time like", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeNotLike(String value) {
                            addCriterion("edit_time not like", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeIn(List<String> values) {
                                addCriterion("edit_time in", values, "editTime");
                                return (Criteria) this;
                            }

                            public Criteria andEditTimeNotIn(List<String> values) {
                                addCriterion("edit_time not in", values, "editTime");
                                return (Criteria) this;
                            }

                            public Criteria andEditTimeBetween(String value1, String value2) {
                                addCriterion("edit_time between", value1, value2, "editTime");
                                return (Criteria) this;
                             }

                             public Criteria andEditTimeNotBetween(String value1, String value2) {
                                addCriterion("edit_time not between", value1, value2, "editTime");
                                return (Criteria) this;
                             }
                            public Criteria andEditUserIsNull() {
                            addCriterion("edit_user is null");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserIsNotNull() {
                            addCriterion("edit_user is not null");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserEqualTo(String value) {
                            addCriterion("edit_user =", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserNotEqualTo(String value) {
                            addCriterion("edit_user <>", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserGreaterThan(String value) {
                            addCriterion("edit_user >", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserGreaterThanOrEqualTo(String value) {
                            addCriterion("edit_user >=", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLessThan(String value) {
                            addCriterion("edit_user <", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLessThanOrEqualTo(String value) {
                            addCriterion("edit_user <=", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLike(String value) {
                            addCriterion("edit_user like", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserNotLike(String value) {
                            addCriterion("edit_user not like", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserIn(List<String> values) {
                                addCriterion("edit_user in", values, "editUser");
                                return (Criteria) this;
                            }

                            public Criteria andEditUserNotIn(List<String> values) {
                                addCriterion("edit_user not in", values, "editUser");
                                return (Criteria) this;
                            }

                            public Criteria andEditUserBetween(String value1, String value2) {
                                addCriterion("edit_user between", value1, value2, "editUser");
                                return (Criteria) this;
                             }

                             public Criteria andEditUserNotBetween(String value1, String value2) {
                                addCriterion("edit_user not between", value1, value2, "editUser");
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