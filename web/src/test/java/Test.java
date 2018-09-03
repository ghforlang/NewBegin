import cn.edu.nbu.biz.user.impl.UserServiceImpl;
import cn.edu.nbu.core.CLassPathXMLApplicationContext;

/**
 * Created by Administrator on 2018/8/20 0020.
 */
public class Test {

    @org.junit.Test
    public void test(){
        CLassPathXMLApplicationContext path = new CLassPathXMLApplicationContext("bean.xml");
        UserServiceImpl userService = (UserServiceImpl) path.getBean("userService");
        userService.show();
    }
}
