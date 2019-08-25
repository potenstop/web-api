package top.potens.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.serialization.JSON;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ControllerVisitAspect
 * @projectName web-api
 * @date 2019/8/18 13:35
 */
@Aspect
@Component
public class ControllerVisitAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        AppLogger.info("controller-start-request uri:[{}] methodName:[{}] param:[{}]", uri, joinPoint.getSignature().getName(), JSON.toJSONStringNotEx(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        AppLogger.info("controller-end-request uri:[{}] methodName:[{}] result:[{}]", uri, joinPoint.getSignature().getName(), JSON.toJSONStringNotEx(result));
        return result;
    }

    @AfterThrowing(
            pointcut = "pointcut()",
            throwing = "ex"
    )
    public void doAfterEx(JoinPoint joinPoint, Throwable ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        if (ex instanceof ApiException) {
            AppLogger.warn("error uri:[{}] methodName:[{}]", ex, uri, joinPoint.getSignature().getName());
        } else {
            AppLogger.error("error uri:[{}] methodName:[{}]", ex, uri, joinPoint.getSignature().getName());
        }
    }

}
