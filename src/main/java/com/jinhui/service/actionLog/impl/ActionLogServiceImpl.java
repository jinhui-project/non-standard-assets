package com.jinhui.service.actionLog.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jinhui.mapper.actionLog.ActionLogMapper;
import com.jinhui.model.ActionLog;
import com.jinhui.model.Users;
import com.jinhui.service.actionLog.ActionLogService;
import com.jinhui.util.DeviceUtils;
import com.jinhui.util.ReqActionContains;
import com.jinhui.util.SystemUtils;

/**
 * 操作日志服务层
 * @time 2017-4-5 上午9:57:27  
 * @author wsc
 * @desc
 *
 */
@Service
public class ActionLogServiceImpl implements ActionLogService {
	private static Logger logger = LoggerFactory.getLogger(ActionLogServiceImpl.class);
	
	@Autowired
	private ActionLogMapper actionLogMapper;
	
	/**
	 * 记录操作日志（登录，注册请求调用）
	 */
	public int insertActionLog(HttpServletRequest request, Users user) {
		ActionLog actionLog = new ActionLog();
		String url = request.getRequestURI();
		String servletPath = request.getServletPath();
		actionLog.setUid(String.valueOf(user.getId()));
		actionLog.setUsername(user.getName());
		actionLog.setUserAgent(request.getHeader("user-agent"));
		actionLog.setChannel(DeviceUtils.whichChannel(request));
		actionLog.setIp(SystemUtils.getIpAddr(request));
		actionLog.setBrowser(SystemUtils.getBrowserInfo(request));
		actionLog.setSystem(SystemUtils.getSystemInfo(request));
		actionLog.setOperateName(String.valueOf(ReqActionContains.loginAndRegistMap().get(servletPath.substring(servletPath.lastIndexOf("/")+1))));
		actionLog.setUrl(url);
		actionLog.setData(fechchParamsData(request));
		return actionLogMapper.insertSelective(actionLog);
	}

	/**
	 * 记录操作日志（登录，注册之外的请求调用）
	 */
	public int insertActionLog(HttpServletRequest request, String sessionData) {
		ActionLog actionLog = new ActionLog();
		String url = request.getRequestURI();
		String servletPath = request.getServletPath();
		JSONObject jsonObject = JSONObject.parseObject(sessionData);
		actionLog.setUid(jsonObject.getString("id"));
		actionLog.setUsername(jsonObject.getString("name"));
		actionLog.setUserAgent(request.getHeader("user-agent"));
		actionLog.setChannel(DeviceUtils.whichChannel(request));
		actionLog.setIp(SystemUtils.getIpAddr(request));
		actionLog.setBrowser(SystemUtils.getBrowserInfo(request));
		actionLog.setSystem(SystemUtils.getSystemInfo(request));
		actionLog.setOperateName(String.valueOf(ReqActionContains.actionMaps().get(servletPath.substring(servletPath.lastIndexOf("/")+1))));
		actionLog.setUrl(url);
		actionLog.setData(fechchParamsData(request));
		return actionLogMapper.insertSelective(actionLog);
	}
	
	public static String fechchParamsData(HttpServletRequest request){
		Enumeration<String> keys = request.getParameterNames();
		String params = "";
		while (keys.hasMoreElements()) {
			String k = keys.nextElement();
			String v = request.getParameter(k);
			if("".equals(v)){v = null;};
			params += "\""+ k +"\""+":\""+ v +"\",";
		}
		params = StringUtils.substring(params, 0, params.length()-1);
		params = StringUtils.leftPad(params, params.length()+1, "{");
        params = StringUtils.rightPad(params, params.length()+1, "}");
    	logger.info(" params: " + params);
    	return params;
	}
	
	

}
