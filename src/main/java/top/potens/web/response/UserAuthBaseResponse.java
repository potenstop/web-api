package top.potens.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by wenshao on 2019/6/15.
 */
@ApiModel(description = "用户对象")
@Data
public class UserAuthBaseResponse {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "用户昵称", example = "123")
    private String nickname;

    @ApiModelProperty(value = "头像", example = "http://")
    private String avatar;

    @ApiModelProperty(value = "auth list", example = "[]")
    private List<UserAuthResponse> userAuthList;

    public UserAuthResponse createUserAuthResponse() {
        return new UserAuthResponse();
    }

    @ApiModel(description = "用户auth信息")
    @Data
    public class UserAuthResponse {
        @ApiModelProperty(value = "auth id", example = "1")
        private Integer userAuthId;

        @ApiModelProperty(value = "auth type", example = "1")
        private Integer identityType;

        @ApiModelProperty(value = "auth对应的id", example = "15611111111")
        private String identifier;
    }

}
