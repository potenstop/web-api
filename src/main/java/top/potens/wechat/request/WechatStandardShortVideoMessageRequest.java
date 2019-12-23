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
public class WechatStandardShortVideoMessageRequest extends WechatStandardMessageBaseRequest {
    @ApiModelProperty(value = "图片消息媒体id，可以调用获取临时素材接口拉取数据。")
    private String mediaId;

    @ApiModelProperty(value = "视频消息缩略图的媒体id，可以调用获取临时素材接口拉取数据。")
    private String thumbMediaId;
}
