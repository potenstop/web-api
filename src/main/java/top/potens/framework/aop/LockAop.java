package top.potens.framework.aop;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParserContext;
import org.springframework.stereotype.Component;
import top.potens.framework.annotation.Lock;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import top.potens.framework.enums.LockModel;
import top.potens.framework.exception.LockException;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className LockAop
 * @projectName web-api
 * @date 2019/8/24 10:35
 */
@Aspect
@Component
public class LockAop {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${fx.config.redisson.default.attempt.timeout:100}")
    private Integer attemptTimeout;

    @Value("${fx.config.redisson.default.watch.timeout:120}")
    private Integer watchTimeout;

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(lock)")
    public void controllerAspect(Lock lock) {
    }

    /**
     * 通过spring Spel 获取参数
     *
     * @param key            定义的key值 以#开头 例如:#user
     * @param parameterNames 形参
     * @param values         形参值
     * @return
     */
    private String getVauleBySpel(String key, String[] parameterNames, Object[] values) {

        ExpressionParser parser1 = new SpelExpressionParser();
        ParserContext parserContext1 = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }
            @Override
            public String getExpressionPrefix() {
                return "#{";
            }
            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'hello '}#{'freebuf!'}#{username}";
        Expression expression1 = parser1.parseExpression(template, parserContext1);

        if (!key.contains("#")) {
            return key;
        }
        // spel解析器
        ExpressionParser parser = new SpelExpressionParser();
        // spel上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        System.out.println(expression1.getValue(context));
        Expression expression = null;
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }
            @Override
            public String getExpressionPrefix() {
                return "#{";
            }
            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        try {
            expression = parser.parseExpression(key, parserContext);
        } catch (Exception e) {
            throw new RuntimeException("解析失败");
        }
        Object value = expression.getValue(context);
        String s = value.toString();
        logger.info("spel表达式key={},value={}", key, s);
        return s;
    }

    @Around(value = "controllerAspect(lock)", argNames = "proceedingJoinPoint,lock")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Lock lock) throws Throwable {
        String[] keys = lock.keys();
        if (keys.length == 0) {
            throw new RuntimeException("keys不能为空");
        }
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) proceedingJoinPoint.getSignature()).getMethod());
        Object[] args = proceedingJoinPoint.getArgs();

        long attemptTimeout = lock.attemptTimeout();
        if (attemptTimeout == 0) {
            attemptTimeout = this.attemptTimeout;
        }
        long lockWatchTimeout = lock.lockWatchTimeout();
        if (lockWatchTimeout == 0) {
            lockWatchTimeout = this.watchTimeout;
        }
        LockModel lockModel = lock.lockModel();
        if (lockModel.equals(LockModel.AUTO)) {
            if (keys.length > 1) {
                lockModel = LockModel.MULTIPLE;
            } else {
                lockModel = LockModel.FAIR;
            }
        }
        if (!lockModel.equals(LockModel.MULTIPLE) && !lockModel.equals(LockModel.REDLOCK) && keys.length > 1) {
            throw new RuntimeException("参数有多个,锁模式为->" + lockModel.name() + ".无法锁定");
        }
        logger.info("model:[{}] attemptTimeout:[{}] lockWatchTimeout:[{}] timeUnit:[{}]", lockModel.name(), attemptTimeout, lockWatchTimeout, lock.timeUnit());
        boolean res = false;
        RLock rLock = null;
        //一直等待加锁.
        switch (lockModel) {
            case FAIR:
                rLock = redissonClient.getFairLock(getVauleBySpel(keys[0], parameterNames, args));
                break;
            case REDLOCK:
                RLock[] locks = new RLock[keys.length];
                int index = 0;
                for (String key : keys) {
                    locks[index++] = redissonClient.getLock(getVauleBySpel(key, parameterNames, args));
                }
                rLock = new RedissonRedLock(locks);
                break;
            case MULTIPLE:
                RLock[] locks1 = new RLock[keys.length];
                int index1 = 0;
                for (String key : keys) {
                    locks1[index1++] = redissonClient.getLock(getVauleBySpel(key, parameterNames, args));
                }
                rLock = new RedissonMultiLock(locks1);
                break;
            case REENTRANT:
                rLock = redissonClient.getLock(getVauleBySpel(keys[0], parameterNames, args));
                break;
            case READ:
                RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock(getVauleBySpel(keys[0], parameterNames, args));
                rLock = rReadWriteLock.readLock();
                break;
            case WRITE:
                RReadWriteLock rReadWriteLock1 = redissonClient.getReadWriteLock(getVauleBySpel(keys[0], parameterNames, args));
                rLock = rReadWriteLock1.writeLock();
                break;
        }

        //执行aop
        if (rLock != null) {
            try {
                if (attemptTimeout == -1) {
                    res = true;
                    // 一直等待加锁
                    rLock.lock(lockWatchTimeout, TimeUnit.MILLISECONDS);
                } else {
                     res = rLock.tryLock(attemptTimeout, lockWatchTimeout, lock.timeUnit());
                }
                if (res) {
                    return proceedingJoinPoint.proceed();
                } else {
                    throw new LockException("获取锁失败");
                }
            } finally {
                if (res) {
                    rLock.unlock();
                }
            }
        }
        throw new LockException("获取锁失败");
    }

}
