package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentTopic implements Serializable {
    private Integer contentTopicId;

    private Date createTime;

    private Date updateTime;

    private String title;

    private Integer contentId;

    private String answer;

    private String analysis;

    private Integer topicType;

    private static final long serialVersionUID = 1L;

    public Integer getContentTopicId() {
        return contentTopicId;
    }

    public void setContentTopicId(Integer contentTopicId) {
        this.contentTopicId = contentTopicId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }
}