package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 用户主动的发送消息 图片
 *
 * @author yanshaowen
 * @className WechatStandardTextMessageRequest
 * @projectName web-api
 * @date 2019/12/23 11:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatStandardImageMessageRequest extends WechatStandardMessageBaseRequest {
    @ApiModelProperty(value = "图片链接（由系统生成）")
    private String picUrl;

    @ApiModelProperty(value = "图片消息媒体id，可以调用获取临时素材接口拉取数据。")
    private String mediaId;
}
