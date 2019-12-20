package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.StringUtil;
import top.potens.wechat.code.WechatCode;
import top.potens.wechat.dao.client.WechatProxyClient;
import top.potens.wechat.dao.client.response.WxmpTokenResponse;
import top.potens.wechat.service.TokenService;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className TokenServiceImpl
 * @projectName web-api
 * @date 2019/12/17 16:59
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {
    @Value("${wxmp.app.id}")
    private String wxmpAppId;
    @Value("${wxmp.app.secret}")
    private String wxmpAppSecret;
    private String grantType = "client_credential";
    private final WechatProxyClient wechatProxyClient;
    private final RedissonClient redissonClient;

    @Override
    public String getWxmpToken() {
        String methodName = "获取公众号token";
        String key = "appToken:" + wxmpAppId + ":" + wxmpAppSecret;
        RBucket<String> wxmpTokenBucket = redissonClient.getBucket(key);
        String token = wxmpTokenBucket.get();
        if (StringUtil.isNotBlank(token)) {
            AppLogger.debug("{} 在缓存中存在 token:[{}]", methodName, token);
            return token;
        }
        AppLogger.debug("{} 在缓存中不存在 token:[{}]", methodName, token);

        WxmpTokenResponse wxmpToken = null;
        try {
            wxmpToken = wechatProxyClient.getWxmpToken(grantType, wxmpAppId, wxmpAppSecret);
        }catch (Exception e) {
            AppLogger.error("{} 接口异常了", e, methodName);
            throw new ApiException(WechatCode.TOKEN_API_EXCEPTION);
        }
        if (wxmpToken.getErrCode() != null) {
            AppLogger.error("{} wxmpToken:[{}]", methodName, JSON.toJSONString(wxmpToken));
            throw new ApiException(WechatCode.TOKEN_API_ERROR, wxmpToken.getErrMsg());
        } else  {
            // 更新到缓存中
            wxmpTokenBucket.set(wxmpToken.getAccessToken(), 7000L, TimeUnit.SECONDS);
            return wxmpToken.getAccessToken();
        }
    }
}
