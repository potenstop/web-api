package top.potens.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContentCommentExample() {
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

        public Criteria andContentCommentIdIsNull() {
            addCriterion("content_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdIsNotNull() {
            addCriterion("content_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdEqualTo(Integer value) {
            addCriterion("content_comment_id =", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdNotEqualTo(Integer value) {
            addCriterion("content_comment_id <>", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdGreaterThan(Integer value) {
            addCriterion("content_comment_id >", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_comment_id >=", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdLessThan(Integer value) {
            addCriterion("content_comment_id <", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_comment_id <=", value, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdIn(List<Integer> values) {
            addCriterion("content_comment_id in", values, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdNotIn(List<Integer> values) {
            addCriterion("content_comment_id not in", values, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("content_comment_id between", value1, value2, "contentCommentId");
            return (Criteria) this;
        }

        public Criteria andContentCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_comment_id not between", value1, value2, "contentCommentId");
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

        public Criteria andOutCommentIdIsNull() {
            addCriterion("out_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdIsNotNull() {
            addCriterion("out_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdEqualTo(String value) {
            addCriterion("out_comment_id =", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdNotEqualTo(String value) {
            addCriterion("out_comment_id <>", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdGreaterThan(String value) {
            addCriterion("out_comment_id >", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("out_comment_id >=", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdLessThan(String value) {
            addCriterion("out_comment_id <", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdLessThanOrEqualTo(String value) {
            addCriterion("out_comment_id <=", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdLike(String value) {
            addCriterion("out_comment_id like", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdNotLike(String value) {
            addCriterion("out_comment_id not like", value, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdIn(List<String> values) {
            addCriterion("out_comment_id in", values, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdNotIn(List<String> values) {
            addCriterion("out_comment_id not in", values, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdBetween(String value1, String value2) {
            addCriterion("out_comment_id between", value1, value2, "outCommentId");
            return (Criteria) this;
        }

        public Criteria andOutCommentIdNotBetween(String value1, String value2) {
            addCriterion("out_comment_id not between", value1, value2, "outCommentId");
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAgainstIsNull() {
            addCriterion("against is null");
            return (Criteria) this;
        }

        public Criteria andAgainstIsNotNull() {
            addCriterion("against is not null");
            return (Criteria) this;
        }

        public Criteria andAgainstEqualTo(Integer value) {
            addCriterion("against =", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstNotEqualTo(Integer value) {
            addCriterion("against <>", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstGreaterThan(Integer value) {
            addCriterion("against >", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstGreaterThanOrEqualTo(Integer value) {
            addCriterion("against >=", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstLessThan(Integer value) {
            addCriterion("against <", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstLessThanOrEqualTo(Integer value) {
            addCriterion("against <=", value, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstIn(List<Integer> values) {
            addCriterion("against in", values, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstNotIn(List<Integer> values) {
            addCriterion("against not in", values, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstBetween(Integer value1, Integer value2) {
            addCriterion("against between", value1, value2, "against");
            return (Criteria) this;
        }

        public Criteria andAgainstNotBetween(Integer value1, Integer value2) {
            addCriterion("against not between", value1, value2, "against");
            return (Criteria) this;
        }

        public Criteria andVoteIsNull() {
            addCriterion("vote is null");
            return (Criteria) this;
        }

        public Criteria andVoteIsNotNull() {
            addCriterion("vote is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEqualTo(Integer value) {
            addCriterion("vote =", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotEqualTo(Integer value) {
            addCriterion("vote <>", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThan(Integer value) {
            addCriterion("vote >", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote >=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThan(Integer value) {
            addCriterion("vote <", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThanOrEqualTo(Integer value) {
            addCriterion("vote <=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteIn(List<Integer> values) {
            addCriterion("vote in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotIn(List<Integer> values) {
            addCriterion("vote not in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteBetween(Integer value1, Integer value2) {
            addCriterion("vote between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotBetween(Integer value1, Integer value2) {
            addCriterion("vote not between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andShareIsNull() {
            addCriterion("`share` is null");
            return (Criteria) this;
        }

        public Criteria andShareIsNotNull() {
            addCriterion("`share` is not null");
            return (Criteria) this;
        }

        public Criteria andShareEqualTo(Integer value) {
            addCriterion("`share` =", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotEqualTo(Integer value) {
            addCriterion("`share` <>", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareGreaterThan(Integer value) {
            addCriterion("`share` >", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareGreaterThanOrEqualTo(Integer value) {
            addCriterion("`share` >=", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareLessThan(Integer value) {
            addCriterion("`share` <", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareLessThanOrEqualTo(Integer value) {
            addCriterion("`share` <=", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareIn(List<Integer> values) {
            addCriterion("`share` in", values, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotIn(List<Integer> values) {
            addCriterion("`share` not in", values, "share");
            return (Criteria) this;
        }

        public Criteria andShareBetween(Integer value1, Integer value2) {
            addCriterion("`share` between", value1, value2, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotBetween(Integer value1, Integer value2) {
            addCriterion("`share` not between", value1, value2, "share");
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