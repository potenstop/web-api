package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentZone implements Serializable {
    private Integer contentZoneId;

    private Date createTime;

    private Date updateTime;

    private String zoneName;

    private String zoneCode;

    private static final long serialVersionUID = 1L;

    public Integer getContentZoneId() {
        return contentZoneId;
    }

    public void setContentZoneId(Integer contentZoneId) {
        this.contentZoneId = contentZoneId;
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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
    }
}