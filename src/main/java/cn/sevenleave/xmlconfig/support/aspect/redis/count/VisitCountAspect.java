package cn.sevenleave.xmlconfig.support.aspect.redis.count;

import cn.sevenleave.xmlconfig.support.redis.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：用来对访问缓存记录进行计数
 *
 * @author SevenLeave
 * @date 2018-09-11
 */
@Aspect
@Component
public class VisitCountAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitCountAspect.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 描述：定义可重用的切点
     */
    @Pointcut("@annotation(cn.sevenleave.xmlconfig.support.aspect.redis.count.VisitCount)")
    public void countAspect() {
    }

    /**
     * 描述：在方法执行后,计数器自增
     *
     * @param joinpoint
     */
    @After(value = "countAspect()")
    public void afterVisited(JoinPoint joinpoint) {
        String key = "xmlconfig:visit:total";
        boolean exist = redisUtil.hasKey(key);
        if (exist) {
            // key存在,自增
            redisUtil.incrByLong(key, 1);
        } else {
            // key不存在,初始化
            redisUtil.setIfAbsent(key, "1");
        }
        LOGGER.info("xmlconfig:visit:total ++");
    }

}
