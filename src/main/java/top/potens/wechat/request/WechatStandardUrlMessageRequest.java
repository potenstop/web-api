package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 用户主动的发送消息 音频
 *
 * @author yanshaowen
 * @className WechatStandardTextMessageRequest
 * @projectName web-api
 * @date 2019/12/23 11:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatStandardUrlMessageRequest extends WechatStandardMessageBaseRequest {
    @ApiModelProperty(value = "消息标题")
    private String title;

    @ApiModelProperty(value = "消息描述")
    private String description;

    @ApiModelProperty(value = "消息链接")
    private String url;
}
