package com.jinhui.util;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在用户登录后，从redis中获取用户信息
 * Created by luoyuanq on 2017/9/29 0029.
 */
public class UserUtils {


    private static Logger logger = LoggerFactory.getLogger(UserUtils.class);


    public static String getUserName(){

        return getUser().getUsername();
    }


    public static Users getUser(){


        //本地测试用
        String env = System.getProperty("deploy.env");
        if("dev".equals(env)) {
            Users user = new Users();
            user.setGname("全毅测试机构");
            user.setUsername("11111111114");
            return user;
        }


        String authenticUser = RedisUtils.getAuthenticUser();
        if(null==authenticUser||authenticUser.equals("")){
            throw new RuntimeException("用户未登录，获取不到用户信息");
        }
        Users userObject = JSON.parseObject(authenticUser, Users.class);

        return userObject;

    }

}
