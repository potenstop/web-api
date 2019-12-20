package top.potens.wechat.dao.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatBase
 * @projectName web-api
 * @date 2019/12/18 11:19
 */
@Data
public class WechatBase {
    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}
