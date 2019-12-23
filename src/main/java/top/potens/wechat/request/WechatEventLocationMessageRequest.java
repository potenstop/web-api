package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 功能描述: 上报地理位置
 *
 * @author yanshaowen
 * @className WechatEventSubscribeMessageRequest
 * @projectName web-api
 * @date 2019/12/23 10:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatEventLocationMessageRequest extends WechatEventMessageBaseRequest {
    @JsonProperty(value = "Latitude")
    @ApiModelProperty(value = "地理位置纬度")
    private BigDecimal latitude;

    @JsonProperty(value = "Longitude")
    @ApiModelProperty(value = "地理位置经度")
    private BigDecimal longitude;

    @JsonProperty(value = "Precision")
    @ApiModelProperty(value = "地理位置精度")
    private String precision;
}
