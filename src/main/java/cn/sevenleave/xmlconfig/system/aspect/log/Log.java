package cn.sevenleave.xmlconfig.system.aspect.log;

import java.lang.annotation.*;

/**
 * @author SevenLeave
 * @date 2018-08-01 15:53
 * 描述：自定义一个注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {

    // 声明成员变量name
    String name() default "";
}
