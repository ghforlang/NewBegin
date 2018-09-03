package cn.edu.nbu.annotation;

import cn.edu.nbu.common.enums.AnnotationTypeEnum;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface MyAnnotation {
    String[] values() default "axe";

    AnnotationTypeEnum type() default AnnotationTypeEnum.A;
}
