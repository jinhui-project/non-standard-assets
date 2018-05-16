package com.jinhui.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jinhui.constant.ConstantEntity;
import com.jinhui.service.actionLog.ActionLogService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.ReqActionContains;

/**
 * 记录操作日志拦截器
 * @time 2017-4-5 上午11:24:53  
 * @author wsc
 * @desc
 *
 */
public class OperateLogInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(OperateLogInterceptor.class);
	@Autowired
	private ActionLogService actionLogService;

	public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc)  
            throws Exception {  
          
    }  
  
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception {  
    }  
  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
    	String servletPath = request.getServletPath();
        if(ReqActionContains.actionMaps().containsKey(servletPath.substring(servletPath.lastIndexOf("/")+1))){
        	 //获取用户信息json数据 
	        String userJsonData =(String) RedisUtils.getValue(request.getParameter("token") ,ConstantEntity.USER_TICKET);
			//记录操作日志
			actionLogService.insertActionLog(request, userJsonData);
			return true;
        }
        return true;  
    }  
    
}
