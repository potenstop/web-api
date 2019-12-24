package top.potens.wechat.service;

import top.potens.wechat.response.WechatUserBasicInfoResponse;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatUserService
 * @projectName web-api
 * @date 2019/12/24 11:45
 */
public interface WechatUserService {
    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/12/24 11:55
    * @param openId
    * @return
    * @throws
    */
    WechatUserBasicInfoResponse getUserInfo(String openId);
}
