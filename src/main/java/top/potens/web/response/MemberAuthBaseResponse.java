package top.potens.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2019/6/15.
 */
@ApiModel(description = "用户对象")
@Data
public class MemberAuthBaseResponse {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer memberId;

    @ApiModelProperty(value = "用户昵称", example = "123")
    private String nickname;

    @ApiModelProperty(value = "头像", example = "http://")
    private String avatar;

    @ApiModelProperty(value = "auth list", example = "[]")
    private List<MemberAuthResponse> memberAuthList;

    public MemberAuthResponse createMemberAuthResponse() {
        return new MemberAuthResponse();
    }

    @ApiModel(description = "用户auth信息")
    @Data
    public class MemberAuthResponse {
        @ApiModelProperty(value = "auth id", example = "1")
        private Integer memberAuthId;

        @ApiModelProperty(value = "auth type", example = "1")
        private Integer identityType;

        @ApiModelProperty(value = "auth对应的id", example = "15611111111")
        private String identifier;
    }

}
