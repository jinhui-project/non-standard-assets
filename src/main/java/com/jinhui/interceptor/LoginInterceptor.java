package com.jinhui.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.Users;
import com.jinhui.util.UserUtils;
import com.jinhui.wechat.WechatAuthorizeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.util.RedisUtils;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        /**
         * 开发环境不限制
         */
        String env = System.getProperty("deploy.env");
        if ("dev".equals(env))
            return true;

        String url = request.getRequestURI();
        if (WechatAuthorizeManager.isWechatRequest(request)) {
            return WechatAuthorizeManager.wechatDispatcher(request, response);
        } else if (url.contains(WechatAuthorizeManager.WechatContextPath)) {
            return false;
        }

        if (url.indexOf("login") >= 0
                || url.indexOf("code") >= 0
                || url.indexOf("exitUser") >= 0) {
            //URL:login.jsp是公开的;除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            /**
             * 为了兼容原来使用在request参数里携带token的方式
             */
            token = request.getParameter("token");
        }


        //获取Session  
        String user = (String) RedisUtils.getValue(token, ConstantEntity.USER_TICKET);
        if (user != null) {
            //每次验证登录之后，重置用户信息在redis中保存的时间,并且在ThreadLocal保存token
            RedisUtils.touch(token, ConstantEntity.USER_TICKET, user, ConstantEntity.REDISUTILS_EXPIRE);

            return true;
        }
        //token已失效，返回提示信息
        if (WechatAuthorizeManager.isWechatRequest(request)) {
            /**
             * 原则上微信上的登录token不会过期，所以返回刷新token提示
             */
            request.getRequestDispatcher("/admin/refreshToken").forward(request, response);
        } else {
            request.getRequestDispatcher("/admin/msgErrorInfo").forward(request, response);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }


}
