package top.potens.web.model;

import java.io.Serializable;
import java.util.Date;

public class ContentComment implements Serializable {
    private Integer contentCommentId;

    private Date createTime;

    private Date updateTime;

    private String outCommentId;

    private Integer contentId;

    private String content;

    private Integer userId;

    private Integer against;

    private Integer vote;

    private Integer share;

    private static final long serialVersionUID = 1L;

    public Integer getContentCommentId() {
        return contentCommentId;
    }

    public void setContentCommentId(Integer contentCommentId) {
        this.contentCommentId = contentCommentId;
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

    public String getOutCommentId() {
        return outCommentId;
    }

    public void setOutCommentId(String outCommentId) {
        this.outCommentId = outCommentId == null ? null : outCommentId.trim();
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgainst() {
        return against;
    }

    public void setAgainst(Integer against) {
        this.against = against;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }
}