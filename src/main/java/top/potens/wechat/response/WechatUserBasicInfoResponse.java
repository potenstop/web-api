package top.potens.wechat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatUserBasicInfoClientResponse
 * @projectName web-api
 * @date 2019/12/24 11:46
 */
@Data
public class WechatUserBasicInfoResponse {
    private Integer subscribe;

    private String openId;

    private String nickname;

    private Integer sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headImgUrl;

    private Long subscribeTime;

    private String unionId;

    private String remark;

    private Integer groupId;

    private List<Integer> tagIdList;

    private String subscribeScene;

    private Integer qrScene;

    private String qrSceneStr;

}
