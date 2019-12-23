package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

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
public class WechatStandardLocationMessageRequest extends WechatStandardMessageBaseRequest {
    @ApiModelProperty(value = "地理位置纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "地理位置经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "地图缩放大小")
    private String scale;

    @ApiModelProperty(value = "地理位置信息")
    private String label;
}
