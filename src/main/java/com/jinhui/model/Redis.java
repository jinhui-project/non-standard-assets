package com.jinhui.model;


import org.springframework.stereotype.Component;



@Component
public class Redis {
	
	
	private String redisIp;

	public  String getRedisIp() {
		return redisIp;
	}

	public  void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}
	
	

}
