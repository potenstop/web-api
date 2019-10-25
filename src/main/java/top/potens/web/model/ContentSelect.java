package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentSelect implements Serializable {
    private Integer contentSelectId;

    private Date createTime;

    private Date updateTime;

    private Integer isSign;

    private String answer;

    private String analysis;

    private static final long serialVersionUID = 1L;

    public Integer getContentSelectId() {
        return contentSelectId;
    }

    public void setContentSelectId(Integer contentSelectId) {
        this.contentSelectId = contentSelectId;
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

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
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
}