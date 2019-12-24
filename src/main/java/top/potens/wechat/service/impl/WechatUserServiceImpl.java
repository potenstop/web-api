package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.code.WechatCode;
import top.potens.wechat.dao.client.WechatProxyClient;
import top.potens.wechat.dao.client.response.WechatUserBasicInfoClientResponse;
import top.potens.wechat.response.WechatUserBasicInfoResponse;
import top.potens.wechat.service.TokenService;
import top.potens.wechat.service.WechatUserService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WechatUserServiceImpl
 * @projectName web-api
 * @date 2019/12/24 11:55
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WechatUserServiceImpl implements WechatUserService {
    private final TokenService tokenService;
    private final WechatProxyClient wechatProxyClient;
    @Override
    public WechatUserBasicInfoResponse getUserInfo(String openId) {
        String methodName = "获取公众号用户信息";
        String wxmpToken = tokenService.getWxmpToken();
        WechatUserBasicInfoClientResponse userInfo = new WechatUserBasicInfoClientResponse();
        userInfo.setNickname("user" + StringUtil.getUuid().substring(0, 8));
        userInfo.setHeadImgUrl("");
        // return userInfo;
//        try {
//            userInfo = wechatProxyClient.getWxmpUserInfo(wxmpToken, openId, "zh_CN");
//        } catch (Exception e) {
//            AppLogger.error("{} 接口异常了", e, methodName);
//            throw new ApiException(WechatCode.TOKEN_API_ERROR);
//        }
//        if (userInfo.getErrCode() != null) {
//             AppLogger.error("{} 错误的返回码:[{}]", methodName, JSON.toJSONString(userInfo));
//             throw new ApiException(WechatCode.TOKEN_API_ERROR);
//        }
        AppLogger.info("{} 获取用户信息成功:[{}]", methodName, JSON.toJSONString(userInfo));
        return BeanCopierUtil.convert(userInfo, WechatUserBasicInfoResponse.class);
    }
}
