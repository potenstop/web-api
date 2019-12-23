package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 用户主动的发送消息
 *
 * @author yanshaowen
 * @className WechatStandardMessageBaseRequest
 * @projectName web-api
 * @date 2019/12/23 10:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatStandardMessageBaseRequest extends WechatMessageBaseRequest {
    @ApiModelProperty(value = "消息id")
    private String msgId;
}
