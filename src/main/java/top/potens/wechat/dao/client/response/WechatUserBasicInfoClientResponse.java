package top.potens.wechat.dao.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatUserBasicInfoClientResponse
 * @projectName web-api
 * @date 2019/12/24 11:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatUserBasicInfoClientResponse extends WechatBase {
    @JsonProperty(value = "subscribe")
    private Integer subscribe;

    @JsonProperty(value = "openid")
    private String openId;

    @JsonProperty(value = "nickname")
    private String nickname;

    @JsonProperty(value = "sex")
    private Integer sex;

    @JsonProperty(value = "language")
    private String language;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "province")
    private String province;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "headimgurl")
    private String headImgUrl;

    @JsonProperty(value = "subscribe_time")
    private Long subscribeTime;

    @JsonProperty(value = "unionid")
    private String unionId;

    @JsonProperty(value = "remark")
    private String remark;

    @JsonProperty(value = "groupid")
    private Integer groupId;

    @JsonProperty(value = "tagid_list")
    private List<Integer> tagIdList;

    @JsonProperty(value = "subscribe_scene")
    private String subscribeScene;

    @JsonProperty(value = "qr_scene")
    private Integer qrScene;

    @JsonProperty(value = "qr_scene_str")
    private String qrSceneStr;

}
