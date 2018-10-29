package com.chrtc.excelTest.excelTest.domain.kettle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by AUTO on 2018-07-11 16:06:50.
 * k_trans where
 */
public class KTransExample{
protected String orderByClause;

protected boolean distinct;

protected List<Criteria> oredCriteria;

    public KTransExample() {
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
                            public Criteria andTransIdIsNull() {
                            addCriterion("trans_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdIsNotNull() {
                            addCriterion("trans_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdEqualTo(String value) {
                            addCriterion("trans_id =", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdNotEqualTo(String value) {
                            addCriterion("trans_id <>", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdGreaterThan(String value) {
                            addCriterion("trans_id >", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_id >=", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdLessThan(String value) {
                            addCriterion("trans_id <", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdLessThanOrEqualTo(String value) {
                            addCriterion("trans_id <=", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdLike(String value) {
                            addCriterion("trans_id like", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdNotLike(String value) {
                            addCriterion("trans_id not like", value, "transId");
                            return (Criteria) this;
                            }

                            public Criteria andTransIdIn(List<String> values) {
                                addCriterion("trans_id in", values, "transId");
                                return (Criteria) this;
                            }

                            public Criteria andTransIdNotIn(List<String> values) {
                                addCriterion("trans_id not in", values, "transId");
                                return (Criteria) this;
                            }

                            public Criteria andTransIdBetween(String value1, String value2) {
                                addCriterion("trans_id between", value1, value2, "transId");
                                return (Criteria) this;
                             }

                             public Criteria andTransIdNotBetween(String value1, String value2) {
                                addCriterion("trans_id not between", value1, value2, "transId");
                                return (Criteria) this;
                             }
                            public Criteria andTransNameIsNull() {
                            addCriterion("trans_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameIsNotNull() {
                            addCriterion("trans_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameEqualTo(String value) {
                            addCriterion("trans_name =", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameNotEqualTo(String value) {
                            addCriterion("trans_name <>", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameGreaterThan(String value) {
                            addCriterion("trans_name >", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_name >=", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameLessThan(String value) {
                            addCriterion("trans_name <", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameLessThanOrEqualTo(String value) {
                            addCriterion("trans_name <=", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameLike(String value) {
                            addCriterion("trans_name like", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameNotLike(String value) {
                            addCriterion("trans_name not like", value, "transName");
                            return (Criteria) this;
                            }

                            public Criteria andTransNameIn(List<String> values) {
                                addCriterion("trans_name in", values, "transName");
                                return (Criteria) this;
                            }

                            public Criteria andTransNameNotIn(List<String> values) {
                                addCriterion("trans_name not in", values, "transName");
                                return (Criteria) this;
                            }

                            public Criteria andTransNameBetween(String value1, String value2) {
                                addCriterion("trans_name between", value1, value2, "transName");
                                return (Criteria) this;
                             }

                             public Criteria andTransNameNotBetween(String value1, String value2) {
                                addCriterion("trans_name not between", value1, value2, "transName");
                                return (Criteria) this;
                             }
                            public Criteria andTransDescriptionIsNull() {
                            addCriterion("trans_description is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionIsNotNull() {
                            addCriterion("trans_description is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionEqualTo(String value) {
                            addCriterion("trans_description =", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionNotEqualTo(String value) {
                            addCriterion("trans_description <>", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionGreaterThan(String value) {
                            addCriterion("trans_description >", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_description >=", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionLessThan(String value) {
                            addCriterion("trans_description <", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionLessThanOrEqualTo(String value) {
                            addCriterion("trans_description <=", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionLike(String value) {
                            addCriterion("trans_description like", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionNotLike(String value) {
                            addCriterion("trans_description not like", value, "transDescription");
                            return (Criteria) this;
                            }

                            public Criteria andTransDescriptionIn(List<String> values) {
                                addCriterion("trans_description in", values, "transDescription");
                                return (Criteria) this;
                            }

                            public Criteria andTransDescriptionNotIn(List<String> values) {
                                addCriterion("trans_description not in", values, "transDescription");
                                return (Criteria) this;
                            }

                            public Criteria andTransDescriptionBetween(String value1, String value2) {
                                addCriterion("trans_description between", value1, value2, "transDescription");
                                return (Criteria) this;
                             }

                             public Criteria andTransDescriptionNotBetween(String value1, String value2) {
                                addCriterion("trans_description not between", value1, value2, "transDescription");
                                return (Criteria) this;
                             }
                            public Criteria andTransTypeIsNull() {
                            addCriterion("trans_type is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeIsNotNull() {
                            addCriterion("trans_type is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeEqualTo(String value) {
                            addCriterion("trans_type =", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeNotEqualTo(String value) {
                            addCriterion("trans_type <>", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeGreaterThan(String value) {
                            addCriterion("trans_type >", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_type >=", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeLessThan(String value) {
                            addCriterion("trans_type <", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeLessThanOrEqualTo(String value) {
                            addCriterion("trans_type <=", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeLike(String value) {
                            addCriterion("trans_type like", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeNotLike(String value) {
                            addCriterion("trans_type not like", value, "transType");
                            return (Criteria) this;
                            }

                            public Criteria andTransTypeIn(List<String> values) {
                                addCriterion("trans_type in", values, "transType");
                                return (Criteria) this;
                            }

                            public Criteria andTransTypeNotIn(List<String> values) {
                                addCriterion("trans_type not in", values, "transType");
                                return (Criteria) this;
                            }

                            public Criteria andTransTypeBetween(String value1, String value2) {
                                addCriterion("trans_type between", value1, value2, "transType");
                                return (Criteria) this;
                             }

                             public Criteria andTransTypeNotBetween(String value1, String value2) {
                                addCriterion("trans_type not between", value1, value2, "transType");
                                return (Criteria) this;
                             }
                            public Criteria andTransPathIsNull() {
                            addCriterion("trans_path is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathIsNotNull() {
                            addCriterion("trans_path is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathEqualTo(String value) {
                            addCriterion("trans_path =", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathNotEqualTo(String value) {
                            addCriterion("trans_path <>", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathGreaterThan(String value) {
                            addCriterion("trans_path >", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_path >=", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathLessThan(String value) {
                            addCriterion("trans_path <", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathLessThanOrEqualTo(String value) {
                            addCriterion("trans_path <=", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathLike(String value) {
                            addCriterion("trans_path like", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathNotLike(String value) {
                            addCriterion("trans_path not like", value, "transPath");
                            return (Criteria) this;
                            }

                            public Criteria andTransPathIn(List<String> values) {
                                addCriterion("trans_path in", values, "transPath");
                                return (Criteria) this;
                            }

                            public Criteria andTransPathNotIn(List<String> values) {
                                addCriterion("trans_path not in", values, "transPath");
                                return (Criteria) this;
                            }

                            public Criteria andTransPathBetween(String value1, String value2) {
                                addCriterion("trans_path between", value1, value2, "transPath");
                                return (Criteria) this;
                             }

                             public Criteria andTransPathNotBetween(String value1, String value2) {
                                addCriterion("trans_path not between", value1, value2, "transPath");
                                return (Criteria) this;
                             }
                            public Criteria andTransRepositoryIdIsNull() {
                            addCriterion("trans_repository_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdIsNotNull() {
                            addCriterion("trans_repository_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdEqualTo(String value) {
                            addCriterion("trans_repository_id =", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdNotEqualTo(String value) {
                            addCriterion("trans_repository_id <>", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdGreaterThan(String value) {
                            addCriterion("trans_repository_id >", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_repository_id >=", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdLessThan(String value) {
                            addCriterion("trans_repository_id <", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdLessThanOrEqualTo(String value) {
                            addCriterion("trans_repository_id <=", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdLike(String value) {
                            addCriterion("trans_repository_id like", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdNotLike(String value) {
                            addCriterion("trans_repository_id not like", value, "transRepositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdIn(List<String> values) {
                                addCriterion("trans_repository_id in", values, "transRepositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdNotIn(List<String> values) {
                                addCriterion("trans_repository_id not in", values, "transRepositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andTransRepositoryIdBetween(String value1, String value2) {
                                addCriterion("trans_repository_id between", value1, value2, "transRepositoryId");
                                return (Criteria) this;
                             }

                             public Criteria andTransRepositoryIdNotBetween(String value1, String value2) {
                                addCriterion("trans_repository_id not between", value1, value2, "transRepositoryId");
                                return (Criteria) this;
                             }
                            public Criteria andTransQuartzIsNull() {
                            addCriterion("trans_quartz is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzIsNotNull() {
                            addCriterion("trans_quartz is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzEqualTo(String value) {
                            addCriterion("trans_quartz =", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzNotEqualTo(String value) {
                            addCriterion("trans_quartz <>", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzGreaterThan(String value) {
                            addCriterion("trans_quartz >", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_quartz >=", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzLessThan(String value) {
                            addCriterion("trans_quartz <", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzLessThanOrEqualTo(String value) {
                            addCriterion("trans_quartz <=", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzLike(String value) {
                            addCriterion("trans_quartz like", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzNotLike(String value) {
                            addCriterion("trans_quartz not like", value, "transQuartz");
                            return (Criteria) this;
                            }

                            public Criteria andTransQuartzIn(List<String> values) {
                                addCriterion("trans_quartz in", values, "transQuartz");
                                return (Criteria) this;
                            }

                            public Criteria andTransQuartzNotIn(List<String> values) {
                                addCriterion("trans_quartz not in", values, "transQuartz");
                                return (Criteria) this;
                            }

                            public Criteria andTransQuartzBetween(String value1, String value2) {
                                addCriterion("trans_quartz between", value1, value2, "transQuartz");
                                return (Criteria) this;
                             }

                             public Criteria andTransQuartzNotBetween(String value1, String value2) {
                                addCriterion("trans_quartz not between", value1, value2, "transQuartz");
                                return (Criteria) this;
                             }
                            public Criteria andTransRecordIsNull() {
                            addCriterion("trans_record is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordIsNotNull() {
                            addCriterion("trans_record is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordEqualTo(String value) {
                            addCriterion("trans_record =", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordNotEqualTo(String value) {
                            addCriterion("trans_record <>", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordGreaterThan(String value) {
                            addCriterion("trans_record >", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_record >=", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordLessThan(String value) {
                            addCriterion("trans_record <", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordLessThanOrEqualTo(String value) {
                            addCriterion("trans_record <=", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordLike(String value) {
                            addCriterion("trans_record like", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordNotLike(String value) {
                            addCriterion("trans_record not like", value, "transRecord");
                            return (Criteria) this;
                            }

                            public Criteria andTransRecordIn(List<String> values) {
                                addCriterion("trans_record in", values, "transRecord");
                                return (Criteria) this;
                            }

                            public Criteria andTransRecordNotIn(List<String> values) {
                                addCriterion("trans_record not in", values, "transRecord");
                                return (Criteria) this;
                            }

                            public Criteria andTransRecordBetween(String value1, String value2) {
                                addCriterion("trans_record between", value1, value2, "transRecord");
                                return (Criteria) this;
                             }

                             public Criteria andTransRecordNotBetween(String value1, String value2) {
                                addCriterion("trans_record not between", value1, value2, "transRecord");
                                return (Criteria) this;
                             }
                            public Criteria andTransLogLevelIsNull() {
                            addCriterion("trans_log_level is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelIsNotNull() {
                            addCriterion("trans_log_level is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelEqualTo(String value) {
                            addCriterion("trans_log_level =", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelNotEqualTo(String value) {
                            addCriterion("trans_log_level <>", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelGreaterThan(String value) {
                            addCriterion("trans_log_level >", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_log_level >=", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelLessThan(String value) {
                            addCriterion("trans_log_level <", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelLessThanOrEqualTo(String value) {
                            addCriterion("trans_log_level <=", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelLike(String value) {
                            addCriterion("trans_log_level like", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelNotLike(String value) {
                            addCriterion("trans_log_level not like", value, "transLogLevel");
                            return (Criteria) this;
                            }

                            public Criteria andTransLogLevelIn(List<String> values) {
                                addCriterion("trans_log_level in", values, "transLogLevel");
                                return (Criteria) this;
                            }

                            public Criteria andTransLogLevelNotIn(List<String> values) {
                                addCriterion("trans_log_level not in", values, "transLogLevel");
                                return (Criteria) this;
                            }

                            public Criteria andTransLogLevelBetween(String value1, String value2) {
                                addCriterion("trans_log_level between", value1, value2, "transLogLevel");
                                return (Criteria) this;
                             }

                             public Criteria andTransLogLevelNotBetween(String value1, String value2) {
                                addCriterion("trans_log_level not between", value1, value2, "transLogLevel");
                                return (Criteria) this;
                             }
                            public Criteria andTransStatusIsNull() {
                            addCriterion("trans_status is null");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusIsNotNull() {
                            addCriterion("trans_status is not null");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusEqualTo(String value) {
                            addCriterion("trans_status =", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusNotEqualTo(String value) {
                            addCriterion("trans_status <>", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusGreaterThan(String value) {
                            addCriterion("trans_status >", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusGreaterThanOrEqualTo(String value) {
                            addCriterion("trans_status >=", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusLessThan(String value) {
                            addCriterion("trans_status <", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusLessThanOrEqualTo(String value) {
                            addCriterion("trans_status <=", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusLike(String value) {
                            addCriterion("trans_status like", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusNotLike(String value) {
                            addCriterion("trans_status not like", value, "transStatus");
                            return (Criteria) this;
                            }

                            public Criteria andTransStatusIn(List<String> values) {
                                addCriterion("trans_status in", values, "transStatus");
                                return (Criteria) this;
                            }

                            public Criteria andTransStatusNotIn(List<String> values) {
                                addCriterion("trans_status not in", values, "transStatus");
                                return (Criteria) this;
                            }

                            public Criteria andTransStatusBetween(String value1, String value2) {
                                addCriterion("trans_status between", value1, value2, "transStatus");
                                return (Criteria) this;
                             }

                             public Criteria andTransStatusNotBetween(String value1, String value2) {
                                addCriterion("trans_status not between", value1, value2, "transStatus");
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