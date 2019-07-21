package top.potens.web.request;

import io.swagger.annotations.ApiModel;

/**
 * Created by wenshao on 2019/7/21.
 */
@ApiModel("外部")
public class ContentCommentOutRequest {
    private String content;

    private String time;

    private Integer against;

    private Integer vote;

    private Integer share;

    private MemberOutRequest member;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAgainst() {
        return this.against;
    }

    public void setAgainst(Integer against) {
        this.against = against;
    }

    public Integer getVote() {
        return this.vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getShare() {
        return this.share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public MemberOutRequest getMember() {
        return this.member;
    }

    public void setMember(MemberOutRequest member) {
        this.member = member;
    }

}
