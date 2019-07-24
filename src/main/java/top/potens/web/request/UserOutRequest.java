package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wenshao on 2019/7/21.
 */
@ApiModel("外部用户")
public class UserOutRequest {
    @ApiModelProperty(value = "名称", name = "userName", dataType = "123")
    private String userName;

    @ApiModelProperty(value = "头像地址", name = "avatar", dataType = "http://1111")
    private String avatar;

    @ApiModelProperty(value = "来源网站 net:网易", name = "sourceWeb", dataType = "net")
    private String sourceWeb;

    @ApiModelProperty(value = "来源场景 comment:评论", name = "sourceEntrance", dataType = "comment")
    private String sourceEntrance;

    @ApiModelProperty(value = "来源的id", name = "sourceUserId", dataType = "1")
    private String sourceUserId;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSourceWeb() {
        return this.sourceWeb;
    }

    public void setSourceWeb(String sourceWeb) {
        this.sourceWeb = sourceWeb;
    }

    public String getSourceEntrance() {
        return this.sourceEntrance;
    }

    public void setSourceEntrance(String sourceEntrance) {
        this.sourceEntrance = sourceEntrance;
    }

    public String getSourceUserId() {
        return this.sourceUserId;
    }

    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }
}
