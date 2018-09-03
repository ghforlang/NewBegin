package cn.edu.nbu.biz.user.impl;

import cn.edu.nbu.annotation.MyResource;
import cn.edu.nbu.biz.user.dao.UserDao2Impl;
import cn.edu.nbu.biz.user.dao.UserDao3Impl;
import cn.edu.nbu.biz.user.dao.UserDaoImpl;

/**
 * Created by Administrator on 2018/6/6 0006.
 */
public class UserServiceImpl {
    public UserDaoImpl userDao;
    public UserDao3Impl userDao3;



    @MyResource
    public UserDao2Impl userDao2;

    @MyResource(name ="userDao")
    public void setUserDao(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    @MyResource
    public void setUser3Dao(UserDao3Impl user3Dao){
        this.userDao3 = user3Dao;
    }


    public void show(){
        userDao.show();
        userDao2.show();
        userDao3.show();
        System.out.println("this is service method!");
    }

}
