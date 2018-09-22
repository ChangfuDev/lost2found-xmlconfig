package cn.sevenleave.xmlconfig.support.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author SevenLeave
 * @date 2018-08-01 15:56
 * 描述：日志切面
 * console打印和文件写入
 */
@Aspect
@Component
public class LogAspect {

    ThreadLocal<Long> time = new ThreadLocal<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    private static final Logger AOP_LOGGER = LoggerFactory.getLogger("aopLogger");

    /**
     * 描述：定义可重用的切点
     * 切点
     */
    @Pointcut("@annotation(cn.sevenleave.xmlconfig.support.aspect.log.Log)")
    public void methodAspect() {
    }

    /**
     * 描述：目标方法执行前
     * 前置通知
     * @param joinPoint
     */
    @Before(value = "methodAspect()")
    public void beforeExec(JoinPoint joinPoint) {
        // 设置开始时间，生成uuid
        time.set(System.currentTimeMillis());
    }

    /**
     * 描述：目标方法执行后
     * 后置通知
     * @param joinPoint
     */
    @After(value = "methodAspect()")
    public void afterExec(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        String occurTime = LocalDateTime.now().toString();
        String execTime = "执行用时是:" + (System.currentTimeMillis() - time.get());
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        // 反射获取被织入的方法
        Method method = ms.getMethod();
        String methodName = method.getName();
        String annotationName = method.getAnnotation(Log.class).name();
        sb.append(occurTime + " -- " + execTime + " -- " + methodName + " -- " + annotationName + " -- 被调用！");

        LOGGER.info("sb.toString() " + sb.toString());
        AOP_LOGGER.debug(sb.toString());
    }


}
