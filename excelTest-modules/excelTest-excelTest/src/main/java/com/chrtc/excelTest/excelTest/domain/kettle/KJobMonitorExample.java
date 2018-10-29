package com.chrtc.excelTest.excelTest.domain.kettle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by AUTO on 2018-08-09 14:18:47.
 * k_job_monitor where
 */
public class KJobMonitorExample{
protected String orderByClause;

protected boolean distinct;

protected List<Criteria> oredCriteria;

    public KJobMonitorExample() {
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
                            public Criteria andMonitorIdIsNull() {
                            addCriterion("monitor_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdIsNotNull() {
                            addCriterion("monitor_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdEqualTo(String value) {
                            addCriterion("monitor_id =", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdNotEqualTo(String value) {
                            addCriterion("monitor_id <>", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdGreaterThan(String value) {
                            addCriterion("monitor_id >", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdGreaterThanOrEqualTo(String value) {
                            addCriterion("monitor_id >=", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdLessThan(String value) {
                            addCriterion("monitor_id <", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdLessThanOrEqualTo(String value) {
                            addCriterion("monitor_id <=", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdLike(String value) {
                            addCriterion("monitor_id like", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdNotLike(String value) {
                            addCriterion("monitor_id not like", value, "monitorId");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorIdIn(List<String> values) {
                                addCriterion("monitor_id in", values, "monitorId");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorIdNotIn(List<String> values) {
                                addCriterion("monitor_id not in", values, "monitorId");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorIdBetween(String value1, String value2) {
                                addCriterion("monitor_id between", value1, value2, "monitorId");
                                return (Criteria) this;
                             }

                             public Criteria andMonitorIdNotBetween(String value1, String value2) {
                                addCriterion("monitor_id not between", value1, value2, "monitorId");
                                return (Criteria) this;
                             }
                            public Criteria andMonitorJobIsNull() {
                            addCriterion("monitor_job is null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobIsNotNull() {
                            addCriterion("monitor_job is not null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobEqualTo(String value) {
                            addCriterion("monitor_job =", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobNotEqualTo(String value) {
                            addCriterion("monitor_job <>", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobGreaterThan(String value) {
                            addCriterion("monitor_job >", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobGreaterThanOrEqualTo(String value) {
                            addCriterion("monitor_job >=", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobLessThan(String value) {
                            addCriterion("monitor_job <", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobLessThanOrEqualTo(String value) {
                            addCriterion("monitor_job <=", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobLike(String value) {
                            addCriterion("monitor_job like", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobNotLike(String value) {
                            addCriterion("monitor_job not like", value, "monitorJob");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorJobIn(List<String> values) {
                                addCriterion("monitor_job in", values, "monitorJob");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorJobNotIn(List<String> values) {
                                addCriterion("monitor_job not in", values, "monitorJob");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorJobBetween(String value1, String value2) {
                                addCriterion("monitor_job between", value1, value2, "monitorJob");
                                return (Criteria) this;
                             }

                             public Criteria andMonitorJobNotBetween(String value1, String value2) {
                                addCriterion("monitor_job not between", value1, value2, "monitorJob");
                                return (Criteria) this;
                             }
                            public Criteria andMonitorSuccessIsNull() {
                            addCriterion("monitor_success is null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessIsNotNull() {
                            addCriterion("monitor_success is not null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessEqualTo(String value) {
                            addCriterion("monitor_success =", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessNotEqualTo(String value) {
                            addCriterion("monitor_success <>", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessGreaterThan(String value) {
                            addCriterion("monitor_success >", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessGreaterThanOrEqualTo(String value) {
                            addCriterion("monitor_success >=", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessLessThan(String value) {
                            addCriterion("monitor_success <", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessLessThanOrEqualTo(String value) {
                            addCriterion("monitor_success <=", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessLike(String value) {
                            addCriterion("monitor_success like", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessNotLike(String value) {
                            addCriterion("monitor_success not like", value, "monitorSuccess");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessIn(List<String> values) {
                                addCriterion("monitor_success in", values, "monitorSuccess");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessNotIn(List<String> values) {
                                addCriterion("monitor_success not in", values, "monitorSuccess");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorSuccessBetween(String value1, String value2) {
                                addCriterion("monitor_success between", value1, value2, "monitorSuccess");
                                return (Criteria) this;
                             }

                             public Criteria andMonitorSuccessNotBetween(String value1, String value2) {
                                addCriterion("monitor_success not between", value1, value2, "monitorSuccess");
                                return (Criteria) this;
                             }
                            public Criteria andMonitorFailIsNull() {
                            addCriterion("monitor_fail is null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailIsNotNull() {
                            addCriterion("monitor_fail is not null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailEqualTo(String value) {
                            addCriterion("monitor_fail =", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailNotEqualTo(String value) {
                            addCriterion("monitor_fail <>", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailGreaterThan(String value) {
                            addCriterion("monitor_fail >", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailGreaterThanOrEqualTo(String value) {
                            addCriterion("monitor_fail >=", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailLessThan(String value) {
                            addCriterion("monitor_fail <", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailLessThanOrEqualTo(String value) {
                            addCriterion("monitor_fail <=", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailLike(String value) {
                            addCriterion("monitor_fail like", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailNotLike(String value) {
                            addCriterion("monitor_fail not like", value, "monitorFail");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorFailIn(List<String> values) {
                                addCriterion("monitor_fail in", values, "monitorFail");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorFailNotIn(List<String> values) {
                                addCriterion("monitor_fail not in", values, "monitorFail");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorFailBetween(String value1, String value2) {
                                addCriterion("monitor_fail between", value1, value2, "monitorFail");
                                return (Criteria) this;
                             }

                             public Criteria andMonitorFailNotBetween(String value1, String value2) {
                                addCriterion("monitor_fail not between", value1, value2, "monitorFail");
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
                            public Criteria andMonitorStatusIsNull() {
                            addCriterion("monitor_status is null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusIsNotNull() {
                            addCriterion("monitor_status is not null");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusEqualTo(String value) {
                            addCriterion("monitor_status =", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusNotEqualTo(String value) {
                            addCriterion("monitor_status <>", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusGreaterThan(String value) {
                            addCriterion("monitor_status >", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusGreaterThanOrEqualTo(String value) {
                            addCriterion("monitor_status >=", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusLessThan(String value) {
                            addCriterion("monitor_status <", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusLessThanOrEqualTo(String value) {
                            addCriterion("monitor_status <=", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusLike(String value) {
                            addCriterion("monitor_status like", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusNotLike(String value) {
                            addCriterion("monitor_status not like", value, "monitorStatus");
                            return (Criteria) this;
                            }

                            public Criteria andMonitorStatusIn(List<String> values) {
                                addCriterion("monitor_status in", values, "monitorStatus");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorStatusNotIn(List<String> values) {
                                addCriterion("monitor_status not in", values, "monitorStatus");
                                return (Criteria) this;
                            }

                            public Criteria andMonitorStatusBetween(String value1, String value2) {
                                addCriterion("monitor_status between", value1, value2, "monitorStatus");
                                return (Criteria) this;
                             }

                             public Criteria andMonitorStatusNotBetween(String value1, String value2) {
                                addCriterion("monitor_status not between", value1, value2, "monitorStatus");
                                return (Criteria) this;
                             }
                            public Criteria andRunStatusIsNull() {
                            addCriterion("run_status is null");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusIsNotNull() {
                            addCriterion("run_status is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusEqualTo(String value) {
                            addCriterion("run_status =", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusNotEqualTo(String value) {
                            addCriterion("run_status <>", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusGreaterThan(String value) {
                            addCriterion("run_status >", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusGreaterThanOrEqualTo(String value) {
                            addCriterion("run_status >=", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusLessThan(String value) {
                            addCriterion("run_status <", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusLessThanOrEqualTo(String value) {
                            addCriterion("run_status <=", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusLike(String value) {
                            addCriterion("run_status like", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusNotLike(String value) {
                            addCriterion("run_status not like", value, "runStatus");
                            return (Criteria) this;
                            }

                            public Criteria andRunStatusIn(List<String> values) {
                                addCriterion("run_status in", values, "runStatus");
                                return (Criteria) this;
                            }

                            public Criteria andRunStatusNotIn(List<String> values) {
                                addCriterion("run_status not in", values, "runStatus");
                                return (Criteria) this;
                            }

                            public Criteria andRunStatusBetween(String value1, String value2) {
                                addCriterion("run_status between", value1, value2, "runStatus");
                                return (Criteria) this;
                             }

                             public Criteria andRunStatusNotBetween(String value1, String value2) {
                                addCriterion("run_status not between", value1, value2, "runStatus");
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