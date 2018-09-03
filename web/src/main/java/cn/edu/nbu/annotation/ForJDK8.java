package cn.edu.nbu.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@Target({ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface ForJDK8 {

}
