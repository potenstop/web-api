package top.potens.wechat.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className MessageBaseRequest
 * @projectName web-api
 * @date 2019/12/22 13:21
 */
@Data
public class MessageBaseRequest {
    @JsonProperty(value = "ToUserName")
    private String toUserName;

}
