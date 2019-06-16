package top.potens.web.model;

import java.io.Serializable;

public class MemberAuth implements Serializable {
    private Integer memberAuthId;

    private Integer memberId;

    private Integer identityType;

    private String identifier;

    private String credential;

    private static final long serialVersionUID = 1L;

    public Integer getMemberAuthId() {
        return memberAuthId;
    }

    public void setMemberAuthId(Integer memberAuthId) {
        this.memberAuthId = memberAuthId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
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