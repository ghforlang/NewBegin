import cn.edu.nbu.aspectj.MyMath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2018/8/20 0020.
 */
public class TestAspect {

    @org.junit.Test
    public void testAspect(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop01.xml");
        MyMath math = ctx.getBean("math", MyMath.class);
        int n1 = 100, n2 = 5;
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }
}
