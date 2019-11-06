package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentTopicSelectOption implements Serializable {
    private Integer contentTopicSelectOptionId;

    private Date createTime;

    private Date updateTime;

    private Integer contentId;

    private Integer contentTopicId;

    private String optionLabel;

    private Integer isOptionAnswer;

    private static final long serialVersionUID = 1L;

    public Integer getContentTopicSelectOptionId() {
        return contentTopicSelectOptionId;
    }

    public void setContentTopicSelectOptionId(Integer contentTopicSelectOptionId) {
        this.contentTopicSelectOptionId = contentTopicSelectOptionId;
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

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentTopicId() {
        return contentTopicId;
    }

    public void setContentTopicId(Integer contentTopicId) {
        this.contentTopicId = contentTopicId;
    }

    public String getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel == null ? null : optionLabel.trim();
    }

    public Integer getIsOptionAnswer() {
        return isOptionAnswer;
    }

    public void setIsOptionAnswer(Integer isOptionAnswer) {
        this.isOptionAnswer = isOptionAnswer;
    }
}