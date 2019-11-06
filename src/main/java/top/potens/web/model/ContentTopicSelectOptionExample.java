package top.potens.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentTopicSelectOptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContentTopicSelectOptionExample() {
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

        public Criteria andContentTopicSelectOptionIdIsNull() {
            addCriterion("content_topic_select_option_id is null");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdIsNotNull() {
            addCriterion("content_topic_select_option_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdEqualTo(Integer value) {
            addCriterion("content_topic_select_option_id =", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdNotEqualTo(Integer value) {
            addCriterion("content_topic_select_option_id <>", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdGreaterThan(Integer value) {
            addCriterion("content_topic_select_option_id >", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_topic_select_option_id >=", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdLessThan(Integer value) {
            addCriterion("content_topic_select_option_id <", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_topic_select_option_id <=", value, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdIn(List<Integer> values) {
            addCriterion("content_topic_select_option_id in", values, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdNotIn(List<Integer> values) {
            addCriterion("content_topic_select_option_id not in", values, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdBetween(Integer value1, Integer value2) {
            addCriterion("content_topic_select_option_id between", value1, value2, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andContentTopicSelectOptionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_topic_select_option_id not between", value1, value2, "contentTopicSelectOptionId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNull() {
            addCriterion("content_id is null");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNotNull() {
            addCriterion("content_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentIdEqualTo(Integer value) {
            addCriterion("content_id =", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotEqualTo(Integer value) {
            addCriterion("content_id <>", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThan(Integer value) {
            addCriterion("content_id >", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_id >=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThan(Integer value) {
            addCriterion("content_id <", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_id <=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdIn(List<Integer> values) {
            addCriterion("content_id in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotIn(List<Integer> values) {
            addCriterion("content_id not in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdBetween(Integer value1, Integer value2) {
            addCriterion("content_id between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_id not between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdIsNull() {
            addCriterion("content_topic_id is null");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdIsNotNull() {
            addCriterion("content_topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdEqualTo(Integer value) {
            addCriterion("content_topic_id =", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdNotEqualTo(Integer value) {
            addCriterion("content_topic_id <>", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdGreaterThan(Integer value) {
            addCriterion("content_topic_id >", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_topic_id >=", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdLessThan(Integer value) {
            addCriterion("content_topic_id <", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_topic_id <=", value, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdIn(List<Integer> values) {
            addCriterion("content_topic_id in", values, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdNotIn(List<Integer> values) {
            addCriterion("content_topic_id not in", values, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("content_topic_id between", value1, value2, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andContentTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_topic_id not between", value1, value2, "contentTopicId");
            return (Criteria) this;
        }

        public Criteria andOptionLabelIsNull() {
            addCriterion("option_label is null");
            return (Criteria) this;
        }

        public Criteria andOptionLabelIsNotNull() {
            addCriterion("option_label is not null");
            return (Criteria) this;
        }

        public Criteria andOptionLabelEqualTo(String value) {
            addCriterion("option_label =", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelNotEqualTo(String value) {
            addCriterion("option_label <>", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelGreaterThan(String value) {
            addCriterion("option_label >", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelGreaterThanOrEqualTo(String value) {
            addCriterion("option_label >=", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelLessThan(String value) {
            addCriterion("option_label <", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelLessThanOrEqualTo(String value) {
            addCriterion("option_label <=", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelLike(String value) {
            addCriterion("option_label like", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelNotLike(String value) {
            addCriterion("option_label not like", value, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelIn(List<String> values) {
            addCriterion("option_label in", values, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelNotIn(List<String> values) {
            addCriterion("option_label not in", values, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelBetween(String value1, String value2) {
            addCriterion("option_label between", value1, value2, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andOptionLabelNotBetween(String value1, String value2) {
            addCriterion("option_label not between", value1, value2, "optionLabel");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerIsNull() {
            addCriterion("is_option_answer is null");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerIsNotNull() {
            addCriterion("is_option_answer is not null");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerEqualTo(Integer value) {
            addCriterion("is_option_answer =", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerNotEqualTo(Integer value) {
            addCriterion("is_option_answer <>", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerGreaterThan(Integer value) {
            addCriterion("is_option_answer >", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_option_answer >=", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerLessThan(Integer value) {
            addCriterion("is_option_answer <", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerLessThanOrEqualTo(Integer value) {
            addCriterion("is_option_answer <=", value, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerIn(List<Integer> values) {
            addCriterion("is_option_answer in", values, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerNotIn(List<Integer> values) {
            addCriterion("is_option_answer not in", values, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerBetween(Integer value1, Integer value2) {
            addCriterion("is_option_answer between", value1, value2, "isOptionAnswer");
            return (Criteria) this;
        }

        public Criteria andIsOptionAnswerNotBetween(Integer value1, Integer value2) {
            addCriterion("is_option_answer not between", value1, value2, "isOptionAnswer");
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