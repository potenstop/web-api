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
import top.potens.wechat.service.AuthService;
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
public class AuthServiceImpl implements AuthService {
    @Value("${wxmp.app.id}")
    private String wxmpAppId;

    @Override
    public String wxmpAuthorize() {
        return null;
    }
}
