package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentLabel implements Serializable {
    private Integer contentLabelId;

    private Date createTime;

    private Date updateTime;

    private Integer contentId;

    private Integer labelId;

    private static final long serialVersionUID = 1L;

    public Integer getContentLabelId() {
        return contentLabelId;
    }

    public void setContentLabelId(Integer contentLabelId) {
        this.contentLabelId = contentLabelId;
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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}