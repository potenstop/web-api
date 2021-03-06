package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;
import top.potens.web.common.constant.RegexpConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by wenshao on 2019/6/23.
 */
public class UserRegisterRequest {
    @ApiModelProperty(value = "用户昵称", example = "potens")
    private String nickname;

    @ApiModelProperty(value = "认证密码", example = "123456")
    @NotBlank
    private String credential;

    @ApiModelProperty(value = "对应类型的凭证", example = "15712345678")
    private String identifier;

    @ApiModelProperty(value = "类型", example = "1")
    @NotNull
    private String channelCode;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}