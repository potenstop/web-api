package top.potens.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Created by wenshao on 2019/7/21.
 */
@ApiModel("外部评论")
public class ContentCommentOutRequest {
    private String id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private Integer against;

    private Integer vote;

    private Integer share;

    private UserOutRequest user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    public UserOutRequest getUser() {
        return this.user;
    }

    public void setUser(UserOutRequest user) {
        this.user = user;
    }

}
