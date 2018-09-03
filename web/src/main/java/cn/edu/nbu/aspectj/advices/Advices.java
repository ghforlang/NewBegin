package cn.edu.nbu.aspectj.advices;

import org.aspectj.lang.JoinPoint;

/**
 * Created by Administrator on 2018/8/20 0020.
 * 通知类，横切逻辑
 */
public class Advices {

    public void before(JoinPoint jp){
        System.out.println("before operation");
        System.out.println(jp.getSignature().getName());
    }

    public void after(JoinPoint jp){
        System.out.println("after operation");
        System.out.println(jp.getSignature().getName());
    }

}
