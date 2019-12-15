package top.potens.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AlbumCourseProblemTopic implements Serializable {
    private Integer albumCourseProblemTopicId;

    private Date createTime;

    private Date updateTime;

    private Integer albumCourseProblemId;

    private Integer contentId;

    private Integer contentTopicId;

    private String inputSelectOption;

    private String inputProblem;

    private Integer state;

    private BigDecimal gradeAmount;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumCourseProblemTopicId() {
        return albumCourseProblemTopicId;
    }

    public void setAlbumCourseProblemTopicId(Integer albumCourseProblemTopicId) {
        this.albumCourseProblemTopicId = albumCourseProblemTopicId;
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

    public Integer getAlbumCourseProblemId() {
        return albumCourseProblemId;
    }

    public void setAlbumCourseProblemId(Integer albumCourseProblemId) {
        this.albumCourseProblemId = albumCourseProblemId;
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

    public String getInputSelectOption() {
        return inputSelectOption;
    }

    public void setInputSelectOption(String inputSelectOption) {
        this.inputSelectOption = inputSelectOption == null ? null : inputSelectOption.trim();
    }

    public String getInputProblem() {
        return inputProblem;
    }

    public void setInputProblem(String inputProblem) {
        this.inputProblem = inputProblem == null ? null : inputProblem.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getGradeAmount() {
        return gradeAmount;
    }

    public void setGradeAmount(BigDecimal gradeAmount) {
        this.gradeAmount = gradeAmount;
    }
}