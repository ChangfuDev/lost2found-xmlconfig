package cn.sevenleave.xmlconfig.system.aspect.redis.count;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述：统计某个Controller访问的次数
 *
 * @author SevenLeave
 * @date 2018-09-11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface VisitCount {
}
