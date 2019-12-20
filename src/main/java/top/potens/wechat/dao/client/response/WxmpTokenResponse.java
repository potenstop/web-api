package top.potens.wechat.dao.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WxmpTokenResponse
 * @projectName web-api
 * @date 2019/12/18 11:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxmpTokenResponse extends WechatBase {
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private Integer expiresIn;
}
