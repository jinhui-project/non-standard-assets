package com.jinhui.model;

import java.io.Serializable;



//@JsonSerialize(include = Inclusion.NON_NULL)
public class BaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2458014690362011639L;
	/**
	 * 返回 编码：0正常，1异常
	 */
	private long code = 0;
	private String message ="";
	private Users json =null;
	private Integer loginNum ;
	/**
	 * 系统当前时间
	 */
	private long expire ;   
	private Integer redisutils_expire;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Users getJson() {
		return json;
	}
	public void setJson(Users json) {
		this.json = json;
	}
	public long getExpire() {
		return expire;
	}
	public void setExpire(long expire) {
		this.expire = expire;
	}
	public Integer getRedisutils_expire() {
		return redisutils_expire;
	}
	public void setRedisutils_expire(Integer redisutils_expire) {
		this.redisutils_expire = redisutils_expire;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}


}
