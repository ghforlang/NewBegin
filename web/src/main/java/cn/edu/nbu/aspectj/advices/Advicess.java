package cn.edu.nbu.aspectj.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Component
@Aspect
public class Advicess {

    @Before("execution(* cn.edu.nbu.aspectj.MyMath2.*(..))")
    public void before(JoinPoint jp){
        System.out.println("before aspect");
        System.out.println(jp.getSignature().getName());
    }

    @After("execution(* cn.edu.nbu.aspectj.MyMath2.*(..))")
    public void after(JoinPoint jp){
        System.out.println("after aspect");
        System.out.println(jp.getSignature().getName());
    }
}
