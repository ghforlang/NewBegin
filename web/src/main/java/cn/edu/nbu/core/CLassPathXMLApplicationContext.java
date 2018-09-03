package cn.edu.nbu.core;

import cn.edu.nbu.annotation.MyResource;
import cn.edu.nbu.biz.user.impl.UserServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/6/6 0006.
 * spring中的注解原理
 * https://blog.csdn.net/u010987379/article/details/52152795
 */


public class CLassPathXMLApplicationContext {
    List<BeanDefine> beanList = new ArrayList<>();
    Map<String,Object> sigletions = new HashMap<>();

    public static void main(String[] args) {

        CLassPathXMLApplicationContext path = new CLassPathXMLApplicationContext(
                "bean.xml");
        UserServiceImpl userService =(UserServiceImpl)path.getBean("userService");
        userService.show();
    }

    public CLassPathXMLApplicationContext(String fileName) {

        //读取配置文件中管理的beans
        this.readXML(fileName);

        //实例化bean
        this.instanceBean();

        //注解处理器
        this.annotationInject();
    }


    public Object getBean(String beanId){
        return sigletions.get(beanId);
    }

    public void annotationInject() {
        for(String beanName : sigletions.keySet()){
            Object bean = sigletions.get(beanName);
            if(Objects.nonNull(bean)){
                this.propertyAnnotation(bean);
                this.fieldAnnotation(bean);
            }
        }
    }

    public void fieldAnnotation(Object bean) {

        try {
            //获取其全部的字段描述
            Field[] fields = bean.getClass().getFields();
            for(Field f : fields){
                if(f!=null && f.isAnnotationPresent(MyResource.class)){
                    MyResource resource = f.getAnnotation(MyResource.class);
                    String name ="";
                    Object value = null;
                    if(resource.name()!=null&&!"".equals(resource.name())){
                        name = resource.name();
                        value = sigletions.get(name);
                    }else{
                        for(String key : sigletions.keySet()){
                            //判断当前属性所属的类型是否在配置文件中存在
                            if(f.getType().isAssignableFrom(sigletions.get(key).getClass())){
                                //获取类型匹配的实例对象
                                value = sigletions.get(key);
                                break;
                            }
                        }
                    }
                    //允许访问private字段
                    f.setAccessible(true);
                    //把引用对象注入属性
                    f.set(bean, value);
                }
            }
        } catch (Exception e) {
            System.out.println("字段注解解析异常..........");
        }
    }

    public void propertyAnnotation(Object bean) {
        try {
            PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
            for(PropertyDescriptor propertyDescriptor : ps){
                Method setter = propertyDescriptor.getWriteMethod();
                if(Objects.nonNull(setter) && setter.isAnnotationPresent(MyResource.class)){
                    MyResource resource = setter.getAnnotation(MyResource.class);
                    String name = "";
                    Object value = null;
                    if(resource.name() != null && !"".endsWith(resource.name())){
                        name = resource.name();
                        value = sigletions.get(name);
                    }else{
                        for(String key : sigletions.keySet()){
                            if(propertyDescriptor.getPropertyType().isAssignableFrom(sigletions.get(key).getClass())){
                                value = sigletions.get(key);
                                break;
                            }
                        }
                    }
                    setter.setAccessible(true);
                    try {
                        setter.invoke(bean,value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
            System.out.println("set方法解析异常");
        }
    }

    public void instanceBean() {
        for(BeanDefine bean : beanList){
            try {
                sigletions.put(bean.getId(),Class.forName(bean.getClazz()).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void readXML(String fileName) {
        Document document = null;
        SAXReader saxReader = new SAXReader();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            document = saxReader.read(classLoader.getResourceAsStream(fileName));
            Element beans = document.getRootElement();
            Iterator<Element> iterator = beans.elementIterator();
            while(iterator.hasNext()){
                Element element = iterator.next();
                BeanDefine beanDefine = new BeanDefine(
                        element.attributeValue("id"),
                        element.attributeValue("class")
                );
                beanList.add(beanDefine);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("配置文件读取出错");
        }

    }


}
