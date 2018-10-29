package com.chrtc.excelTest.excelTest.domain.kettle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 * k_repository where
 */
public class KRepositoryExample{
protected String orderByClause;

protected boolean distinct;

protected List<Criteria> oredCriteria;

    public KRepositoryExample() {
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
                            public Criteria andRepositoryNameIsNull() {
                            addCriterion("repository_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameIsNotNull() {
                            addCriterion("repository_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameEqualTo(String value) {
                            addCriterion("repository_name =", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameNotEqualTo(String value) {
                            addCriterion("repository_name <>", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameGreaterThan(String value) {
                            addCriterion("repository_name >", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameGreaterThanOrEqualTo(String value) {
                            addCriterion("repository_name >=", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameLessThan(String value) {
                            addCriterion("repository_name <", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameLessThanOrEqualTo(String value) {
                            addCriterion("repository_name <=", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameLike(String value) {
                            addCriterion("repository_name like", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameNotLike(String value) {
                            addCriterion("repository_name not like", value, "repositoryName");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryNameIn(List<String> values) {
                                addCriterion("repository_name in", values, "repositoryName");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryNameNotIn(List<String> values) {
                                addCriterion("repository_name not in", values, "repositoryName");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryNameBetween(String value1, String value2) {
                                addCriterion("repository_name between", value1, value2, "repositoryName");
                                return (Criteria) this;
                             }

                             public Criteria andRepositoryNameNotBetween(String value1, String value2) {
                                addCriterion("repository_name not between", value1, value2, "repositoryName");
                                return (Criteria) this;
                             }
                            public Criteria andRepositoryUsernameIsNull() {
                            addCriterion("repository_username is null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameIsNotNull() {
                            addCriterion("repository_username is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameEqualTo(String value) {
                            addCriterion("repository_username =", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameNotEqualTo(String value) {
                            addCriterion("repository_username <>", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameGreaterThan(String value) {
                            addCriterion("repository_username >", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameGreaterThanOrEqualTo(String value) {
                            addCriterion("repository_username >=", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameLessThan(String value) {
                            addCriterion("repository_username <", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameLessThanOrEqualTo(String value) {
                            addCriterion("repository_username <=", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameLike(String value) {
                            addCriterion("repository_username like", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameNotLike(String value) {
                            addCriterion("repository_username not like", value, "repositoryUsername");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameIn(List<String> values) {
                                addCriterion("repository_username in", values, "repositoryUsername");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameNotIn(List<String> values) {
                                addCriterion("repository_username not in", values, "repositoryUsername");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryUsernameBetween(String value1, String value2) {
                                addCriterion("repository_username between", value1, value2, "repositoryUsername");
                                return (Criteria) this;
                             }

                             public Criteria andRepositoryUsernameNotBetween(String value1, String value2) {
                                addCriterion("repository_username not between", value1, value2, "repositoryUsername");
                                return (Criteria) this;
                             }
                            public Criteria andRepositoryPasswordIsNull() {
                            addCriterion("repository_password is null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordIsNotNull() {
                            addCriterion("repository_password is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordEqualTo(String value) {
                            addCriterion("repository_password =", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordNotEqualTo(String value) {
                            addCriterion("repository_password <>", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordGreaterThan(String value) {
                            addCriterion("repository_password >", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordGreaterThanOrEqualTo(String value) {
                            addCriterion("repository_password >=", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordLessThan(String value) {
                            addCriterion("repository_password <", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordLessThanOrEqualTo(String value) {
                            addCriterion("repository_password <=", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordLike(String value) {
                            addCriterion("repository_password like", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordNotLike(String value) {
                            addCriterion("repository_password not like", value, "repositoryPassword");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordIn(List<String> values) {
                                addCriterion("repository_password in", values, "repositoryPassword");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordNotIn(List<String> values) {
                                addCriterion("repository_password not in", values, "repositoryPassword");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryPasswordBetween(String value1, String value2) {
                                addCriterion("repository_password between", value1, value2, "repositoryPassword");
                                return (Criteria) this;
                             }

                             public Criteria andRepositoryPasswordNotBetween(String value1, String value2) {
                                addCriterion("repository_password not between", value1, value2, "repositoryPassword");
                                return (Criteria) this;
                             }
                            public Criteria andRepositoryTypeIsNull() {
                            addCriterion("repository_type is null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeIsNotNull() {
                            addCriterion("repository_type is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeEqualTo(String value) {
                            addCriterion("repository_type =", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeNotEqualTo(String value) {
                            addCriterion("repository_type <>", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeGreaterThan(String value) {
                            addCriterion("repository_type >", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeGreaterThanOrEqualTo(String value) {
                            addCriterion("repository_type >=", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeLessThan(String value) {
                            addCriterion("repository_type <", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeLessThanOrEqualTo(String value) {
                            addCriterion("repository_type <=", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeLike(String value) {
                            addCriterion("repository_type like", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeNotLike(String value) {
                            addCriterion("repository_type not like", value, "repositoryType");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeIn(List<String> values) {
                                addCriterion("repository_type in", values, "repositoryType");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeNotIn(List<String> values) {
                                addCriterion("repository_type not in", values, "repositoryType");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryTypeBetween(String value1, String value2) {
                                addCriterion("repository_type between", value1, value2, "repositoryType");
                                return (Criteria) this;
                             }

                             public Criteria andRepositoryTypeNotBetween(String value1, String value2) {
                                addCriterion("repository_type not between", value1, value2, "repositoryType");
                                return (Criteria) this;
                             }
                            public Criteria andDatabaseAccessIsNull() {
                            addCriterion("database_access is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessIsNotNull() {
                            addCriterion("database_access is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessEqualTo(String value) {
                            addCriterion("database_access =", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessNotEqualTo(String value) {
                            addCriterion("database_access <>", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessGreaterThan(String value) {
                            addCriterion("database_access >", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessGreaterThanOrEqualTo(String value) {
                            addCriterion("database_access >=", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessLessThan(String value) {
                            addCriterion("database_access <", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessLessThanOrEqualTo(String value) {
                            addCriterion("database_access <=", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessLike(String value) {
                            addCriterion("database_access like", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessNotLike(String value) {
                            addCriterion("database_access not like", value, "databaseAccess");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessIn(List<String> values) {
                                addCriterion("database_access in", values, "databaseAccess");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessNotIn(List<String> values) {
                                addCriterion("database_access not in", values, "databaseAccess");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseAccessBetween(String value1, String value2) {
                                addCriterion("database_access between", value1, value2, "databaseAccess");
                                return (Criteria) this;
                             }

                             public Criteria andDatabaseAccessNotBetween(String value1, String value2) {
                                addCriterion("database_access not between", value1, value2, "databaseAccess");
                                return (Criteria) this;
                             }
                            public Criteria andDatabaseHostIsNull() {
                            addCriterion("database_host is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostIsNotNull() {
                            addCriterion("database_host is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostEqualTo(String value) {
                            addCriterion("database_host =", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostNotEqualTo(String value) {
                            addCriterion("database_host <>", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostGreaterThan(String value) {
                            addCriterion("database_host >", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostGreaterThanOrEqualTo(String value) {
                            addCriterion("database_host >=", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostLessThan(String value) {
                            addCriterion("database_host <", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostLessThanOrEqualTo(String value) {
                            addCriterion("database_host <=", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostLike(String value) {
                            addCriterion("database_host like", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostNotLike(String value) {
                            addCriterion("database_host not like", value, "databaseHost");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseHostIn(List<String> values) {
                                addCriterion("database_host in", values, "databaseHost");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseHostNotIn(List<String> values) {
                                addCriterion("database_host not in", values, "databaseHost");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseHostBetween(String value1, String value2) {
                                addCriterion("database_host between", value1, value2, "databaseHost");
                                return (Criteria) this;
                             }

                             public Criteria andDatabaseHostNotBetween(String value1, String value2) {
                                addCriterion("database_host not between", value1, value2, "databaseHost");
                                return (Criteria) this;
                             }
                            public Criteria andDatabasePortIsNull() {
                            addCriterion("database_port is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortIsNotNull() {
                            addCriterion("database_port is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortEqualTo(String value) {
                            addCriterion("database_port =", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortNotEqualTo(String value) {
                            addCriterion("database_port <>", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortGreaterThan(String value) {
                            addCriterion("database_port >", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortGreaterThanOrEqualTo(String value) {
                            addCriterion("database_port >=", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortLessThan(String value) {
                            addCriterion("database_port <", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortLessThanOrEqualTo(String value) {
                            addCriterion("database_port <=", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortLike(String value) {
                            addCriterion("database_port like", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortNotLike(String value) {
                            addCriterion("database_port not like", value, "databasePort");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePortIn(List<String> values) {
                                addCriterion("database_port in", values, "databasePort");
                                return (Criteria) this;
                            }

                            public Criteria andDatabasePortNotIn(List<String> values) {
                                addCriterion("database_port not in", values, "databasePort");
                                return (Criteria) this;
                            }

                            public Criteria andDatabasePortBetween(String value1, String value2) {
                                addCriterion("database_port between", value1, value2, "databasePort");
                                return (Criteria) this;
                             }

                             public Criteria andDatabasePortNotBetween(String value1, String value2) {
                                addCriterion("database_port not between", value1, value2, "databasePort");
                                return (Criteria) this;
                             }
                            public Criteria andDatabaseNameIsNull() {
                            addCriterion("database_name is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameIsNotNull() {
                            addCriterion("database_name is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameEqualTo(String value) {
                            addCriterion("database_name =", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameNotEqualTo(String value) {
                            addCriterion("database_name <>", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameGreaterThan(String value) {
                            addCriterion("database_name >", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
                            addCriterion("database_name >=", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameLessThan(String value) {
                            addCriterion("database_name <", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
                            addCriterion("database_name <=", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameLike(String value) {
                            addCriterion("database_name like", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameNotLike(String value) {
                            addCriterion("database_name not like", value, "databaseName");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseNameIn(List<String> values) {
                                addCriterion("database_name in", values, "databaseName");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseNameNotIn(List<String> values) {
                                addCriterion("database_name not in", values, "databaseName");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseNameBetween(String value1, String value2) {
                                addCriterion("database_name between", value1, value2, "databaseName");
                                return (Criteria) this;
                             }

                             public Criteria andDatabaseNameNotBetween(String value1, String value2) {
                                addCriterion("database_name not between", value1, value2, "databaseName");
                                return (Criteria) this;
                             }
                            public Criteria andDatabaseUsernameIsNull() {
                            addCriterion("database_username is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameIsNotNull() {
                            addCriterion("database_username is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameEqualTo(String value) {
                            addCriterion("database_username =", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameNotEqualTo(String value) {
                            addCriterion("database_username <>", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameGreaterThan(String value) {
                            addCriterion("database_username >", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameGreaterThanOrEqualTo(String value) {
                            addCriterion("database_username >=", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameLessThan(String value) {
                            addCriterion("database_username <", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameLessThanOrEqualTo(String value) {
                            addCriterion("database_username <=", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameLike(String value) {
                            addCriterion("database_username like", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameNotLike(String value) {
                            addCriterion("database_username not like", value, "databaseUsername");
                            return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameIn(List<String> values) {
                                addCriterion("database_username in", values, "databaseUsername");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameNotIn(List<String> values) {
                                addCriterion("database_username not in", values, "databaseUsername");
                                return (Criteria) this;
                            }

                            public Criteria andDatabaseUsernameBetween(String value1, String value2) {
                                addCriterion("database_username between", value1, value2, "databaseUsername");
                                return (Criteria) this;
                             }

                             public Criteria andDatabaseUsernameNotBetween(String value1, String value2) {
                                addCriterion("database_username not between", value1, value2, "databaseUsername");
                                return (Criteria) this;
                             }
                            public Criteria andDatabasePasswordIsNull() {
                            addCriterion("database_password is null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordIsNotNull() {
                            addCriterion("database_password is not null");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordEqualTo(String value) {
                            addCriterion("database_password =", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordNotEqualTo(String value) {
                            addCriterion("database_password <>", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordGreaterThan(String value) {
                            addCriterion("database_password >", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordGreaterThanOrEqualTo(String value) {
                            addCriterion("database_password >=", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordLessThan(String value) {
                            addCriterion("database_password <", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordLessThanOrEqualTo(String value) {
                            addCriterion("database_password <=", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordLike(String value) {
                            addCriterion("database_password like", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordNotLike(String value) {
                            addCriterion("database_password not like", value, "databasePassword");
                            return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordIn(List<String> values) {
                                addCriterion("database_password in", values, "databasePassword");
                                return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordNotIn(List<String> values) {
                                addCriterion("database_password not in", values, "databasePassword");
                                return (Criteria) this;
                            }

                            public Criteria andDatabasePasswordBetween(String value1, String value2) {
                                addCriterion("database_password between", value1, value2, "databasePassword");
                                return (Criteria) this;
                             }

                             public Criteria andDatabasePasswordNotBetween(String value1, String value2) {
                                addCriterion("database_password not between", value1, value2, "databasePassword");
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

                            public Criteria andAddTimeEqualTo(Date value) {
                            addCriterion("add_time =", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeNotEqualTo(Date value) {
                            addCriterion("add_time <>", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeGreaterThan(Date value) {
                            addCriterion("add_time >", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
                            addCriterion("add_time >=", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLessThan(Date value) {
                            addCriterion("add_time <", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLessThanOrEqualTo(Date value) {
                            addCriterion("add_time <=", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeLike(Date value) {
                            addCriterion("add_time like", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeNotLike(Date value) {
                            addCriterion("add_time not like", value, "addTime");
                            return (Criteria) this;
                            }

                            public Criteria andAddTimeIn(List<Date> values) {
                                addCriterion("add_time in", values, "addTime");
                                return (Criteria) this;
                            }

                            public Criteria andAddTimeNotIn(List<Date> values) {
                                addCriterion("add_time not in", values, "addTime");
                                return (Criteria) this;
                            }

                            public Criteria andAddTimeBetween(Date value1, Date value2) {
                                addCriterion("add_time between", value1, value2, "addTime");
                                return (Criteria) this;
                             }

                             public Criteria andAddTimeNotBetween(Date value1, Date value2) {
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

                            public Criteria andAddUserEqualTo(int value) {
                            addCriterion("add_user =", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserNotEqualTo(int value) {
                            addCriterion("add_user <>", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserGreaterThan(int value) {
                            addCriterion("add_user >", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserGreaterThanOrEqualTo(int value) {
                            addCriterion("add_user >=", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLessThan(int value) {
                            addCriterion("add_user <", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLessThanOrEqualTo(int value) {
                            addCriterion("add_user <=", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserLike(int value) {
                            addCriterion("add_user like", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserNotLike(int value) {
                            addCriterion("add_user not like", value, "addUser");
                            return (Criteria) this;
                            }

                            public Criteria andAddUserIn(List<Integer> values) {
                                addCriterion("add_user in", values, "addUser");
                                return (Criteria) this;
                            }

                            public Criteria andAddUserNotIn(List<Integer> values) {
                                addCriterion("add_user not in", values, "addUser");
                                return (Criteria) this;
                            }

                            public Criteria andAddUserBetween(int value1, int value2) {
                                addCriterion("add_user between", value1, value2, "addUser");
                                return (Criteria) this;
                             }

                             public Criteria andAddUserNotBetween(int value1, int value2) {
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

                            public Criteria andEditTimeEqualTo(Date value) {
                            addCriterion("edit_time =", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeNotEqualTo(Date value) {
                            addCriterion("edit_time <>", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeGreaterThan(Date value) {
                            addCriterion("edit_time >", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeGreaterThanOrEqualTo(Date value) {
                            addCriterion("edit_time >=", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLessThan(Date value) {
                            addCriterion("edit_time <", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLessThanOrEqualTo(Date value) {
                            addCriterion("edit_time <=", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeLike(Date value) {
                            addCriterion("edit_time like", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeNotLike(Date value) {
                            addCriterion("edit_time not like", value, "editTime");
                            return (Criteria) this;
                            }

                            public Criteria andEditTimeIn(List<Date> values) {
                                addCriterion("edit_time in", values, "editTime");
                                return (Criteria) this;
                            }

                            public Criteria andEditTimeNotIn(List<Date> values) {
                                addCriterion("edit_time not in", values, "editTime");
                                return (Criteria) this;
                            }

                            public Criteria andEditTimeBetween(Date value1, Date value2) {
                                addCriterion("edit_time between", value1, value2, "editTime");
                                return (Criteria) this;
                             }

                             public Criteria andEditTimeNotBetween(Date value1, Date value2) {
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

                            public Criteria andEditUserEqualTo(int value) {
                            addCriterion("edit_user =", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserNotEqualTo(int value) {
                            addCriterion("edit_user <>", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserGreaterThan(int value) {
                            addCriterion("edit_user >", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserGreaterThanOrEqualTo(int value) {
                            addCriterion("edit_user >=", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLessThan(int value) {
                            addCriterion("edit_user <", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLessThanOrEqualTo(int value) {
                            addCriterion("edit_user <=", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserLike(int value) {
                            addCriterion("edit_user like", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserNotLike(int value) {
                            addCriterion("edit_user not like", value, "editUser");
                            return (Criteria) this;
                            }

                            public Criteria andEditUserIn(List<Integer> values) {
                                addCriterion("edit_user in", values, "editUser");
                                return (Criteria) this;
                            }

                            public Criteria andEditUserNotIn(List<Integer> values) {
                                addCriterion("edit_user not in", values, "editUser");
                                return (Criteria) this;
                            }

                            public Criteria andEditUserBetween(int value1, int value2) {
                                addCriterion("edit_user between", value1, value2, "editUser");
                                return (Criteria) this;
                             }

                             public Criteria andEditUserNotBetween(int value1, int value2) {
                                addCriterion("edit_user not between", value1, value2, "editUser");
                                return (Criteria) this;
                             }
                            public Criteria andRepositoryIdIsNull() {
                            addCriterion("repository_id is null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdIsNotNull() {
                            addCriterion("repository_id is not null");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdEqualTo(int value) {
                            addCriterion("repository_id =", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdNotEqualTo(int value) {
                            addCriterion("repository_id <>", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdGreaterThan(int value) {
                            addCriterion("repository_id >", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdGreaterThanOrEqualTo(int value) {
                            addCriterion("repository_id >=", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdLessThan(int value) {
                            addCriterion("repository_id <", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdLessThanOrEqualTo(int value) {
                            addCriterion("repository_id <=", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdLike(int value) {
                            addCriterion("repository_id like", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdNotLike(int value) {
                            addCriterion("repository_id not like", value, "repositoryId");
                            return (Criteria) this;
                            }

                            public Criteria andRepositoryIdIn(List<Integer> values) {
                                addCriterion("repository_id in", values, "repositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryIdNotIn(List<Integer> values) {
                                addCriterion("repository_id not in", values, "repositoryId");
                                return (Criteria) this;
                            }

                            public Criteria andRepositoryIdBetween(int value1, int value2) {
                                addCriterion("repository_id between", value1, value2, "repositoryId");
                                return (Criteria) this;
                             }

                             public Criteria andRepositoryIdNotBetween(int value1, int value2) {
                                addCriterion("repository_id not between", value1, value2, "repositoryId");
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