package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class MemberAuth implements Serializable {
    private Integer memberAuthId;

    private Date createTime;

    private Date updateTime;

    private Integer memberId;

    private Integer channelId;

    private String identifier;

    private String credential;

    private static final long serialVersionUID = 1L;

    public Integer getMemberAuthId() {
        return memberAuthId;
    }

    public void setMemberAuthId(Integer memberAuthId) {
        this.memberAuthId = memberAuthId;
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
    }
}