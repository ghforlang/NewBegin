package cn.edu.nbu.aspectj.advices;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Aspect
@Component
public class AllTypeAdvices {


    //切点复用
    @Pointcut("execution(* cn.edu.nbu.aspectj.MyMath.*(..))")
    public void pointcut(){
    }

    //切点复用前
//    @Before("execution(* cn.edu.nbu.aspectj.MyMath.*(..))")
//    public void before(JoinPoint jp){
//        System.out.println("before join");
//        System.out.println(jp.getSignature().getName());
//    }
//
//    @After("execution(* cn.edu.nbu.aspectj.MyMath.*(..))")
//    public void after(JoinPoint jp){
//        System.out.println("after join");
//        System.out.println(jp.getSignature().getName());
//    }

    //切点复用后
    @Before("pointcut()")
    public void before(JoinPoint jp){
        System.out.println("before join");
        System.out.println(jp.getSignature().getName());
    }
    @After("pointcut()")
    public void after(JoinPoint jp){
        System.out.println("after join");
        System.out.println(jp.getSignature().getName());
    }


    //切点函数within，指定包下所有类的所有方法被切入
    @After("within(cn.edu.nbu.biz.*)")
    public void within(JoinPoint jp){
        System.out.println("within join");
    }

    //切点函数this,实现了UserService接口的任意连接点
    @After("this(cn.edu.nbu.biz.user.UserService)")
    public void thisnow(JoinPoint jp){
        System.out.println("this join");
        System.out.println(jp.getSignature().getName());
    }

    //切点函数args,方法有两个int类型的才会被切入
    @After("args(int,int)")
    public void args(JoinPoint jp){
        System.out.println("args join");
        System.out.println(jp.getSignature().getName());
    }

    //@annotation切点函数,要求方法必须被cn.edu.nbu.annotation.MethodAnnotation注解才会被切入
    @After("@annotation(cn.edu.nbu.annotation.MethodAnnotation)")
    public  void annotation(JoinPoint jp){
        System.out.println("annotation join");
        System.out.println(jp.getSignature().getName());
    }

    @Around("execution(* cn.edu.nbu.aspectj.MyMath.a*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println(pjp.getSignature().getName());
        System.out.println("around before");
        Object result = pjp.proceed();
        System.out.println("around after");
        return result;
    }

    @AfterReturning(pointcut = "execution(* cn.edu.nbu.aspectj.MyMath.a*(..))",returning = "result")
    public void afterReturning(JoinPoint jp,Object result){
        System.out.println(jp.getSignature().getName());
        System.out.println("结果是：" + result);
    }

    @AfterThrowing(pointcut = "execution(* cn.edu.nbu.aspectj.MyMath.a*(..))",throwing = "exp")
    public void afterThrowing(JoinPoint jp,Exception exp){
        System.out.println(jp.getSignature().getName());
        System.out.println("异常消息:" + exp.getMessage());
        System.out.println("异常通知");
    }
}
