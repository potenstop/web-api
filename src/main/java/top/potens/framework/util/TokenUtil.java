package top.potens.framework.util;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import top.potens.framework.constant.TokenConstant;
import top.potens.framework.context.HttpContext;
import top.potens.framework.model.TokenUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className TokenUtil
 * @projectName web-api
 * @date 2019/8/29 10:24
 */
@Component
public class TokenUtil {


    /**
    *
    * 方法功能描述: 设置token
    *
    * @author yanshaowen
    * @date 2019/8/29 17:44
    * @param tokenUser  tokenUser
    * @return
    * @throws
    */
    public static void updateToken(TokenUser tokenUser) {
        String uuid = StringUtil.getUuid();
        RedissonClient bean = SpringUtil.getBean(RedissonClient.class);
        RBucket<Object> bucket = bean.getBucket(TokenConstant.TOKEN_REDISSION_NAME + uuid);
        bucket.set(tokenUser, TokenConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        Cookie cookie = new Cookie(TokenConstant.TOKEN_NAME, uuid);
        cookie.setPath("/");
        cookie.setMaxAge(TokenConstant.TOKEN_EXPIRE_TIME);
        HttpServletResponse response = HttpContext.getResponse();
        response.addCookie(cookie);
    }
}
