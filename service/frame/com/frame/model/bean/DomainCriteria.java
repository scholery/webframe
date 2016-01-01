package com.frame.model.bean;

import java.util.ArrayList;
import java.util.List;

public class DomainCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DomainCriteria() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDomainCodeIsNull() {
            addCriterion("domain_code is null");
            return (Criteria) this;
        }

        public Criteria andDomainCodeIsNotNull() {
            addCriterion("domain_code is not null");
            return (Criteria) this;
        }

        public Criteria andDomainCodeEqualTo(String value) {
            addCriterion("domain_code =", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeNotEqualTo(String value) {
            addCriterion("domain_code <>", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeGreaterThan(String value) {
            addCriterion("domain_code >", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeGreaterThanOrEqualTo(String value) {
            addCriterion("domain_code >=", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeLessThan(String value) {
            addCriterion("domain_code <", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeLessThanOrEqualTo(String value) {
            addCriterion("domain_code <=", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeLike(String value) {
            addCriterion("domain_code like", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeNotLike(String value) {
            addCriterion("domain_code not like", value, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeIn(List<String> values) {
            addCriterion("domain_code in", values, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeNotIn(List<String> values) {
            addCriterion("domain_code not in", values, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeBetween(String value1, String value2) {
            addCriterion("domain_code between", value1, value2, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainCodeNotBetween(String value1, String value2) {
            addCriterion("domain_code not between", value1, value2, "domainCode");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameIsNull() {
            addCriterion("domain_short_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameIsNotNull() {
            addCriterion("domain_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameEqualTo(String value) {
            addCriterion("domain_short_name =", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameNotEqualTo(String value) {
            addCriterion("domain_short_name <>", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameGreaterThan(String value) {
            addCriterion("domain_short_name >", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_short_name >=", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameLessThan(String value) {
            addCriterion("domain_short_name <", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameLessThanOrEqualTo(String value) {
            addCriterion("domain_short_name <=", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameLike(String value) {
            addCriterion("domain_short_name like", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameNotLike(String value) {
            addCriterion("domain_short_name not like", value, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameIn(List<String> values) {
            addCriterion("domain_short_name in", values, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameNotIn(List<String> values) {
            addCriterion("domain_short_name not in", values, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameBetween(String value1, String value2) {
            addCriterion("domain_short_name between", value1, value2, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainShortNameNotBetween(String value1, String value2) {
            addCriterion("domain_short_name not between", value1, value2, "domainShortName");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyIsNull() {
            addCriterion("domain_title_key is null");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyIsNotNull() {
            addCriterion("domain_title_key is not null");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyEqualTo(String value) {
            addCriterion("domain_title_key =", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyNotEqualTo(String value) {
            addCriterion("domain_title_key <>", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyGreaterThan(String value) {
            addCriterion("domain_title_key >", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyGreaterThanOrEqualTo(String value) {
            addCriterion("domain_title_key >=", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyLessThan(String value) {
            addCriterion("domain_title_key <", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyLessThanOrEqualTo(String value) {
            addCriterion("domain_title_key <=", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyLike(String value) {
            addCriterion("domain_title_key like", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyNotLike(String value) {
            addCriterion("domain_title_key not like", value, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyIn(List<String> values) {
            addCriterion("domain_title_key in", values, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyNotIn(List<String> values) {
            addCriterion("domain_title_key not in", values, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyBetween(String value1, String value2) {
            addCriterion("domain_title_key between", value1, value2, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainTitleKeyNotBetween(String value1, String value2) {
            addCriterion("domain_title_key not between", value1, value2, "domainTitleKey");
            return (Criteria) this;
        }

        public Criteria andDomainIconIsNull() {
            addCriterion("domain_icon is null");
            return (Criteria) this;
        }

        public Criteria andDomainIconIsNotNull() {
            addCriterion("domain_icon is not null");
            return (Criteria) this;
        }

        public Criteria andDomainIconEqualTo(String value) {
            addCriterion("domain_icon =", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconNotEqualTo(String value) {
            addCriterion("domain_icon <>", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconGreaterThan(String value) {
            addCriterion("domain_icon >", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconGreaterThanOrEqualTo(String value) {
            addCriterion("domain_icon >=", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconLessThan(String value) {
            addCriterion("domain_icon <", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconLessThanOrEqualTo(String value) {
            addCriterion("domain_icon <=", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconLike(String value) {
            addCriterion("domain_icon like", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconNotLike(String value) {
            addCriterion("domain_icon not like", value, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconIn(List<String> values) {
            addCriterion("domain_icon in", values, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconNotIn(List<String> values) {
            addCriterion("domain_icon not in", values, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconBetween(String value1, String value2) {
            addCriterion("domain_icon between", value1, value2, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainIconNotBetween(String value1, String value2) {
            addCriterion("domain_icon not between", value1, value2, "domainIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconIsNull() {
            addCriterion("domain_small_icon is null");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconIsNotNull() {
            addCriterion("domain_small_icon is not null");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconEqualTo(String value) {
            addCriterion("domain_small_icon =", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconNotEqualTo(String value) {
            addCriterion("domain_small_icon <>", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconGreaterThan(String value) {
            addCriterion("domain_small_icon >", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconGreaterThanOrEqualTo(String value) {
            addCriterion("domain_small_icon >=", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconLessThan(String value) {
            addCriterion("domain_small_icon <", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconLessThanOrEqualTo(String value) {
            addCriterion("domain_small_icon <=", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconLike(String value) {
            addCriterion("domain_small_icon like", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconNotLike(String value) {
            addCriterion("domain_small_icon not like", value, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconIn(List<String> values) {
            addCriterion("domain_small_icon in", values, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconNotIn(List<String> values) {
            addCriterion("domain_small_icon not in", values, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconBetween(String value1, String value2) {
            addCriterion("domain_small_icon between", value1, value2, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andDomainSmallIconNotBetween(String value1, String value2) {
            addCriterion("domain_small_icon not between", value1, value2, "domainSmallIcon");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNull() {
            addCriterion("sort_index is null");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNotNull() {
            addCriterion("sort_index is not null");
            return (Criteria) this;
        }

        public Criteria andSortIndexEqualTo(Integer value) {
            addCriterion("sort_index =", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotEqualTo(Integer value) {
            addCriterion("sort_index <>", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThan(Integer value) {
            addCriterion("sort_index >", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_index >=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThan(Integer value) {
            addCriterion("sort_index <", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThanOrEqualTo(Integer value) {
            addCriterion("sort_index <=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexIn(List<Integer> values) {
            addCriterion("sort_index in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotIn(List<Integer> values) {
            addCriterion("sort_index not in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexBetween(Integer value1, Integer value2) {
            addCriterion("sort_index between", value1, value2, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_index not between", value1, value2, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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