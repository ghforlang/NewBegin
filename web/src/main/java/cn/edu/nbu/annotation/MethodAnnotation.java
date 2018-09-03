package cn.edu.nbu.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MethodAnnotation {
    String value() default "methodAnnotation";
}
