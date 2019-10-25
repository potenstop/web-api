package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentSelectOption implements Serializable {
    private Integer contentSelectOptionId;

    private Date createTime;

    private Date updateTime;

    private Integer contentSelectId;

    private static final long serialVersionUID = 1L;

    public Integer getContentSelectOptionId() {
        return contentSelectOptionId;
    }

    public void setContentSelectOptionId(Integer contentSelectOptionId) {
        this.contentSelectOptionId = contentSelectOptionId;
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

    public Integer getContentSelectId() {
        return contentSelectId;
    }

    public void setContentSelectId(Integer contentSelectId) {
        this.contentSelectId = contentSelectId;
    }
}