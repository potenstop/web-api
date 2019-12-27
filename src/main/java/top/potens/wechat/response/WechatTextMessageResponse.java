package top.potens.wechat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatMessageBasicResponse
 * @projectName web-api
 * @date 2019/12/24 17:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatTextMessageResponse extends WechatMessageBasicResponse {
    @JsonProperty(value = "Content")
    @JacksonXmlCData(value =true)
    private String content;
}
