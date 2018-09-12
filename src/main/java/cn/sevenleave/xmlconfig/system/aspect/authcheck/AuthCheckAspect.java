package cn.sevenleave.xmlconfig.system.aspect.authcheck;

import cn.sevenleave.xmlconfig.utils.model.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author SevenLeave
 * @date 2018-08-03 14:19
 * 描述：身份认证，未认证时打印日志
 */
@Aspect
@Component
public class AuthCheckAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCheckAspect.class);
    private static final Logger AOP_LOGGER = LoggerFactory.getLogger("aopLogger");

    //-- 通过切点来选择连接点，详见手册 --
    // 1.限定匹配带有指定注解的连接点
    @Pointcut("@annotation(cn.sevenleave.xmlconfig.system.aspect.authcheck.AuthCheck)")
    public void methodAspect() {
    }
    // 2.限定匹配某个类的某个方法被执行时，作为连接点
    // @Pointcut("execution(* cn.sevenleave.xmlconfig.user.controller.UserInfoController.register(..))")

    /**
     * 描述：验证用户操作权限
     * 如果可能中断切点的方法，就应该使用Around通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("methodAspect()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userToken = getUserToken(request);
        if (!userToken.equals(AuthCheckConstants.MY_TEMPORARY_USERTOKEN.getUserToken())) {
            // 日志输出
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            Method method = ms.getMethod();
            String target = joinPoint.getTarget().toString();
            String methodName = method.getName();
            String occurTime = LocalDateTime.now().toString();
            StringBuffer sb = new StringBuffer();
            sb.append(occurTime + " -- ");
            sb.append(target + " -- ");
            sb.append(methodName + " -- ");
            sb.append("403,无权限执行此操作！");
            AOP_LOGGER.error(sb.toString());
            return JsonResult.fail("403", "无权限执行此操作！");
        } else {
            // 验证通过，继续执行被织入的方法
            return joinPoint.proceed();
        }
    }


    /**
     * 描述：获取cookie中的userToken值
     *
     * @param request
     * @return
     */
    private String getUserToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }

        for (Cookie cookie : cookies) {
            if ("userToken".equals(cookie.getName())) {
                // 测试使用的token值
                return AuthCheckConstants.MY_TEMPORARY_USERTOKEN.getUserToken();
            }
        }
        return "";
    }

}
