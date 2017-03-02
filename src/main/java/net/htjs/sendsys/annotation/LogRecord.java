package net.htjs.sendsys.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 注解定义，标明需要日志记录，调用方式@LogRecord(description = "描述")
 * author  dyenigma
 * date 2016/9/18 15:39
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
    String description() default "";
}
