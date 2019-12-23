package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 关注的扫码事件
 *
 * @author yanshaowen
 * @className WechatEventSubscribeMessageRequest
 * @projectName web-api
 * @date 2019/12/23 10:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatEventClickMessageRequest extends WechatEventMessageBaseRequest {
    @JsonProperty(value = "EventKey")
    @ApiModelProperty(value = "事件KEY值，设置的跳转URL")
    private String eventKey;
}
