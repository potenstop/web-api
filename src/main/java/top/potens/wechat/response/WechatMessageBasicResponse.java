package top.potens.wechat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatMessageBasicResponse
 * @projectName web-api
 * @date 2019/12/24 17:12
 */
@Data
public class WechatMessageBasicResponse {
    @JsonProperty(value = "ToUserName")
    @JacksonXmlCData(value =true)
    private String toUserName;

    @JsonProperty(value = "FromUserName")
    @JacksonXmlCData(value =true)
    private String fromUserName;

    @JsonProperty(value = "CreateTime")
    @JacksonXmlCData(value =true)
    private Long createTime;

    @JsonProperty(value = "MsgType")
    @JacksonXmlCData(value =true)
    private String msgType;
}
