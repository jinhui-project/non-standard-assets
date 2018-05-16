package com.jinhui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试类
 * Created by Administrator on 2017/9/27 0027.
 */


@ContextConfiguration(locations = { "classpath:springmvc-servlet.xml"})
@RunWith(SpringRunner.class)
@WebAppConfiguration
public abstract class  TestConfig {

//    @Test
//    public void hello(){
//
//    }


}
