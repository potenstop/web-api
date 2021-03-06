package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 用户扫描带场景值二维码时  已关注公众号
 *
 * @author yanshaowen
 * @className WechatEventSubscribeMessageRequest
 * @projectName web-api
 * @date 2019/12/23 10:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatEventScanMessageRequest extends WechatEventMessageBaseRequest {
    @ApiModelProperty(value = "事件KEY值，qrscene_为前缀，后面为二维码的参数值")
    private String eventKey;

    @ApiModelProperty(value = "二维码的ticket，可用来换取二维码图片")
    private String ticket;
}
