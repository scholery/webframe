package com.frame.model.bean;

import java.util.ArrayList;
import java.util.List;

public class SysPropertyCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysPropertyCriteria() {
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

        public Criteria andPropertyCatalogIsNull() {
            addCriterion("property_catalog is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogIsNotNull() {
            addCriterion("property_catalog is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogEqualTo(String value) {
            addCriterion("property_catalog =", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogNotEqualTo(String value) {
            addCriterion("property_catalog <>", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogGreaterThan(String value) {
            addCriterion("property_catalog >", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("property_catalog >=", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogLessThan(String value) {
            addCriterion("property_catalog <", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogLessThanOrEqualTo(String value) {
            addCriterion("property_catalog <=", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogLike(String value) {
            addCriterion("property_catalog like", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogNotLike(String value) {
            addCriterion("property_catalog not like", value, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogIn(List<String> values) {
            addCriterion("property_catalog in", values, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogNotIn(List<String> values) {
            addCriterion("property_catalog not in", values, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogBetween(String value1, String value2) {
            addCriterion("property_catalog between", value1, value2, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCatalogNotBetween(String value1, String value2) {
            addCriterion("property_catalog not between", value1, value2, "propertyCatalog");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeIsNull() {
            addCriterion("property_code is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeIsNotNull() {
            addCriterion("property_code is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeEqualTo(String value) {
            addCriterion("property_code =", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeNotEqualTo(String value) {
            addCriterion("property_code <>", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeGreaterThan(String value) {
            addCriterion("property_code >", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("property_code >=", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeLessThan(String value) {
            addCriterion("property_code <", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeLessThanOrEqualTo(String value) {
            addCriterion("property_code <=", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeLike(String value) {
            addCriterion("property_code like", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeNotLike(String value) {
            addCriterion("property_code not like", value, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeIn(List<String> values) {
            addCriterion("property_code in", values, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeNotIn(List<String> values) {
            addCriterion("property_code not in", values, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeBetween(String value1, String value2) {
            addCriterion("property_code between", value1, value2, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyCodeNotBetween(String value1, String value2) {
            addCriterion("property_code not between", value1, value2, "propertyCode");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNull() {
            addCriterion("property_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNotNull() {
            addCriterion("property_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameEqualTo(String value) {
            addCriterion("property_name =", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotEqualTo(String value) {
            addCriterion("property_name <>", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThan(String value) {
            addCriterion("property_name >", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_name >=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThan(String value) {
            addCriterion("property_name <", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("property_name <=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLike(String value) {
            addCriterion("property_name like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotLike(String value) {
            addCriterion("property_name not like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIn(List<String> values) {
            addCriterion("property_name in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotIn(List<String> values) {
            addCriterion("property_name not in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameBetween(String value1, String value2) {
            addCriterion("property_name between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotBetween(String value1, String value2) {
            addCriterion("property_name not between", value1, value2, "propertyName");
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