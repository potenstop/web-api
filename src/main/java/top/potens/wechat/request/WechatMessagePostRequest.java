package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述: 微信请求的对象
 *
 * @author yanshaowen
 * @className WechatMessagePostRequest
 * @projectName web-api
 * @date 2019/12/23 13:01
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatMessagePostRequest {
    @JsonProperty(value = "ToUserName")
    @ApiModelProperty(value = "开发者微信号")
    private String toUserName;

    @JsonProperty(value = "FromUserName")
    @ApiModelProperty(value = "发送方帐号（一个OpenID）")
    private String openId;

    @JsonProperty(value = "CreateTime")
    @ApiModelProperty(value = "消息创建时间 （整型）")
    private Long createTime;

    @JsonProperty(value = "MsgType")
    @ApiModelProperty(value = "消息类型")
    private String msgType;

    @JsonProperty(value = "MsgId")
    @ApiModelProperty(value = "消息id")
    private String msgId;

    @JsonProperty(value = "Content")
    @ApiModelProperty(value = "文本消息内容")
    private String content;

    @JsonProperty(value = "PicUrl")
    @ApiModelProperty(value = "图片链接（由系统生成）")
    private String picUrl;

    @JsonProperty(value = "MediaId")
    @ApiModelProperty(value = "图片消息媒体id，可以调用获取临时素材接口拉取数据。")
    private String mediaId;

    @JsonProperty(value = "Format")
    @ApiModelProperty(value = "语音格式，如amr，speex等")
    private String format;

    @JsonProperty(value = "Recognition")
    @ApiModelProperty(value = "语音识别结果，UTF8编码")
    private String recognition;

    @JsonProperty(value = "ThumbMediaId")
    @ApiModelProperty(value = "视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据")
    private String thumbMediaId;

    @JsonProperty(value = "Location_X")
    @ApiModelProperty(value = "地理位置维度")
    private String locationX;

    @JsonProperty(value = "Location_Y")
    @ApiModelProperty(value = "地理位置经度")
    private String locationY;

    @JsonProperty(value = "Scale")
    @ApiModelProperty(value = "地理位置经度")
    private String scale;

    @JsonProperty(value = "Label")
    @ApiModelProperty(value = "地理位置信息")
    private String label;

    @JsonProperty(value = "Title")
    @ApiModelProperty(value = "消息标题")
    private String title;

    @JsonProperty(value = "Description")
    @ApiModelProperty(value = "消息描述")
    private String description;

    @JsonProperty(value = "Url")
    @ApiModelProperty(value = "消息链接")
    private String url;

    @JsonProperty(value = "Event")
    @ApiModelProperty(value = "事件类型")
    private String event;

    @JsonProperty(value = "Latitude")
    @ApiModelProperty(value = "地理位置纬度")
    private String latitude;

    @JsonProperty(value = "Longitude")
    @ApiModelProperty(value = "地理位置经度")
    private String longitude;

    @JsonProperty(value = "Precision")
    @ApiModelProperty(value = "地理位置精度")
    private String precision;

    @JsonProperty(value = "EventKey")
    @ApiModelProperty(value = "事件KEY值")
    private String eventKey;


}
