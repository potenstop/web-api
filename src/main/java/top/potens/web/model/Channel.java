package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class Channel implements Serializable {
    private Integer channelId;

    private Date createTime;

    private Date updateTime;

    private String channelName;

    private String channelCode;

    private Integer memberCanLogin;

    private Integer memberCheckCredential;

    private static final long serialVersionUID = 1L;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Integer getMemberCanLogin() {
        return memberCanLogin;
    }

    public void setMemberCanLogin(Integer memberCanLogin) {
        this.memberCanLogin = memberCanLogin;
    }

    public Integer getMemberCheckCredential() {
        return memberCheckCredential;
    }

    public void setMemberCheckCredential(Integer memberCheckCredential) {
        this.memberCheckCredential = memberCheckCredential;
    }
}