package top.potens.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumCourseProblemTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlbumCourseProblemTopicExample() {
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

        public Criteria andAlbumCourseProblemTopicIdIsNull() {
            addCriterion("album_course_problem_topic_id is null");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdIsNotNull() {
            addCriterion("album_course_problem_topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdEqualTo(Integer value) {
            addCriterion("album_course_problem_topic_id =", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdNotEqualTo(Integer value) {
            addCriterion("album_course_problem_topic_id <>", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdGreaterThan(Integer value) {
            addCriterion("album_course_problem_topic_id >", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("album_course_problem_topic_id >=", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdLessThan(Integer value) {
            addCriterion("album_course_problem_topic_id <", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("album_course_problem_topic_id <=", value, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdIn(List<Integer> values) {
            addCriterion("album_course_problem_topic_id in", values, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdNotIn(List<Integer> values) {
            addCriterion("album_course_problem_topic_id not in", values, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("album_course_problem_topic_id between", value1, value2, "albumCourseProblemTopicId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("album_course_problem_topic_id not between", value1, value2, "albumCourseProblemTopicId");
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

        public Criteria andAlbumCourseProblemIdIsNull() {
            addCriterion("album_course_problem_id is null");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdIsNotNull() {
            addCriterion("album_course_problem_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdEqualTo(Integer value) {
            addCriterion("album_course_problem_id =", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdNotEqualTo(Integer value) {
            addCriterion("album_course_problem_id <>", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdGreaterThan(Integer value) {
            addCriterion("album_course_problem_id >", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("album_course_problem_id >=", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdLessThan(Integer value) {
            addCriterion("album_course_problem_id <", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdLessThanOrEqualTo(Integer value) {
            addCriterion("album_course_problem_id <=", value, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdIn(List<Integer> values) {
            addCriterion("album_course_problem_id in", values, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdNotIn(List<Integer> values) {
            addCriterion("album_course_problem_id not in", values, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdBetween(Integer value1, Integer value2) {
            addCriterion("album_course_problem_id between", value1, value2, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andAlbumCourseProblemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("album_course_problem_id not between", value1, value2, "albumCourseProblemId");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionIsNull() {
            addCriterion("input_select_option is null");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionIsNotNull() {
            addCriterion("input_select_option is not null");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionEqualTo(String value) {
            addCriterion("input_select_option =", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionNotEqualTo(String value) {
            addCriterion("input_select_option <>", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionGreaterThan(String value) {
            addCriterion("input_select_option >", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionGreaterThanOrEqualTo(String value) {
            addCriterion("input_select_option >=", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionLessThan(String value) {
            addCriterion("input_select_option <", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionLessThanOrEqualTo(String value) {
            addCriterion("input_select_option <=", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionLike(String value) {
            addCriterion("input_select_option like", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionNotLike(String value) {
            addCriterion("input_select_option not like", value, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionIn(List<String> values) {
            addCriterion("input_select_option in", values, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionNotIn(List<String> values) {
            addCriterion("input_select_option not in", values, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionBetween(String value1, String value2) {
            addCriterion("input_select_option between", value1, value2, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputSelectOptionNotBetween(String value1, String value2) {
            addCriterion("input_select_option not between", value1, value2, "inputSelectOption");
            return (Criteria) this;
        }

        public Criteria andInputProblemIsNull() {
            addCriterion("input_problem is null");
            return (Criteria) this;
        }

        public Criteria andInputProblemIsNotNull() {
            addCriterion("input_problem is not null");
            return (Criteria) this;
        }

        public Criteria andInputProblemEqualTo(String value) {
            addCriterion("input_problem =", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemNotEqualTo(String value) {
            addCriterion("input_problem <>", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemGreaterThan(String value) {
            addCriterion("input_problem >", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemGreaterThanOrEqualTo(String value) {
            addCriterion("input_problem >=", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemLessThan(String value) {
            addCriterion("input_problem <", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemLessThanOrEqualTo(String value) {
            addCriterion("input_problem <=", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemLike(String value) {
            addCriterion("input_problem like", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemNotLike(String value) {
            addCriterion("input_problem not like", value, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemIn(List<String> values) {
            addCriterion("input_problem in", values, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemNotIn(List<String> values) {
            addCriterion("input_problem not in", values, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemBetween(String value1, String value2) {
            addCriterion("input_problem between", value1, value2, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andInputProblemNotBetween(String value1, String value2) {
            addCriterion("input_problem not between", value1, value2, "inputProblem");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andGradeAmountIsNull() {
            addCriterion("grade_amount is null");
            return (Criteria) this;
        }

        public Criteria andGradeAmountIsNotNull() {
            addCriterion("grade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGradeAmountEqualTo(BigDecimal value) {
            addCriterion("grade_amount =", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("grade_amount <>", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountGreaterThan(BigDecimal value) {
            addCriterion("grade_amount >", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("grade_amount >=", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountLessThan(BigDecimal value) {
            addCriterion("grade_amount <", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("grade_amount <=", value, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountIn(List<BigDecimal> values) {
            addCriterion("grade_amount in", values, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("grade_amount not in", values, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("grade_amount between", value1, value2, "gradeAmount");
            return (Criteria) this;
        }

        public Criteria andGradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("grade_amount not between", value1, value2, "gradeAmount");
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