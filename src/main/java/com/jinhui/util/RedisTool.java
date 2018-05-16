package com.jinhui.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.jinhui.model.Redis;

public class RedisTool {

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springmvc-servlet.xml");  
	private static Redis redis = (Redis) applicationContext.getBean("redis"); 
	
	private static String redisip = redis.getRedisIp();	
	private static JedisPool pool = new JedisPool(new JedisPoolConfig(), redisip);  
	private static Jedis jedis = pool.getResource();
	public static Jedis getJedis(){		
		return jedis;		
	}

}
