package com.jinhui.service.actionLog;

import javax.servlet.http.HttpServletRequest;

import com.jinhui.model.Users;

public interface ActionLogService {

	/**
	 * 记录操作日志（登录，注册之外的请求调用）
	 * @param request
	 * @param sessionData
	 * @return
	 */
	public int insertActionLog(HttpServletRequest request,String sessionData);
	
	/**
	 * 记录操作日志（登录，注册请求调用）
	 * @param request
	 * @param uid
	 * @param uname
	 * @return
	 */
	public int insertActionLog(HttpServletRequest request,Users user);
}
