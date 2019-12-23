package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述: 基础消息
 *
 * @author yanshaowen
 * @className WechatMessageBaseRequest
 * @projectName web-api
 * @date 2019/12/22 13:21
 */
@Data
public class WechatMessageBaseRequest {
    @ApiModelProperty(value = "开发者微信号")
    private String toUserName;

    @ApiModelProperty(value = "发送方帐号（一个OpenID）")
    private String openId;

    @ApiModelProperty(value = "消息创建时间 （整型）")
    private Long createTime;

    @ApiModelProperty(value = "消息大类型分类 1: 事件消息 2: 主动消息")
    private Integer classify;

    @ApiModelProperty(value = "消息的类型  InType")
    private Integer msgType;
}
