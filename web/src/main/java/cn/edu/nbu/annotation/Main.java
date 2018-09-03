package cn.edu.nbu.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
public class Main {
    public static void main(String[] args) {
        Class clazz = TestAnnotation.class;

        if(clazz.isAnnotationPresent(MyAnnotation.class)){
            MyAnnotation myAnnotation = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
            if(Objects.nonNull(myAnnotation)){
                System.out.println("myAnnotation : " + myAnnotation.getClass().getName());
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            FiledAnnoatation filedAnnotation = field.getAnnotation(FiledAnnoatation.class);
            if(Objects.nonNull(filedAnnotation)){
                System.out.println("filedAnnotation : " + filedAnnotation.getClass().getName());
            }
        }

        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            if(Objects.nonNull(methodAnnotation)){
                System.out.println("methodAnnotation : " + methodAnnotation.getClass().getName());
            }
        }
    }
}
