package top.potens.framework.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.potens.framework.annotation.UserAuthToken;
import top.potens.framework.constant.CommonConstant;
import top.potens.framework.constant.TokenConstant;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.TokenUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserAuthorizationInterceptor
 * @projectName web-api
 * @date 2019/8/28 19:02
 */
@Slf4j
@Component
public class UserAuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedissonClient redisson;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 获取header里的token 转为TokenUser
        boolean notLogin = true;
        String token = request.getHeader(TokenConstant.TOKEN_NAME);
        if (token != null && token.length() != 0) {
            RBucket<TokenUser> rBucket = redisson.getBucket(TokenConstant.TOKEN_REDISSION_NAME + token);
            TokenUser tokenUser = rBucket.get();
            if (tokenUser != null) {
                notLogin = false;
                request.getSession().setAttribute(TokenConstant.REQUEST_CURRENT_KEY, tokenUser);
            }
        }

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(UserAuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(UserAuthToken.class) != null) {
            if (notLogin) {
                PrintWriter out = null;
                try {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    ApiResult<Object> result = new ApiResult<>();
                    result.setCode("401");
                    result.setMessage("401 unauthorized");
                    out = response.getWriter();
                    out.println(result);
                    return false;
                } catch (Exception e) {
                    log.error("response error", e);
                    return false;
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}