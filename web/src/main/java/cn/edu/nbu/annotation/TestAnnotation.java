package cn.edu.nbu.annotation;

import cn.edu.nbu.common.enums.AnnotationTypeEnum;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@MyAnnotation(values = "testClassAnnotation",type = AnnotationTypeEnum.B)
public class TestAnnotation {

    @FiledAnnoatation("type")
    private Integer type;

    @MethodAnnotation("test")
    public void test(){
        System.out.println("just a test");
    }

    public  @ForJDK8 TestAnnotation(){
        TestAnnotation a = new @ForJDK8 TestAnnotation();
        System.out.println("a : " + a.toString());
    }

    public TestAnnotation getAnnotation(){
        String str = (@ForJDK8 String)"a";
        System.out.println("str : " + str);

        return new @ForJDK8 TestAnnotation();
    }

//    class UnmodifiableList<T> implements List<@ForJDK8 T>

    public void testException() throws @ForJDK8 Exception{
        System.out.println("testException");
    }

}
