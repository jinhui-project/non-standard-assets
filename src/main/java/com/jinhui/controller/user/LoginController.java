package com.jinhui.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jinhui.controller.base.WebConstants;
import com.jinhui.controller.base.WebResult;
import com.jinhui.controller.user.vo.UserVo;
import com.jinhui.wechat.WechatAuthorizeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;
import com.jinhui.service.actionLog.ActionLogService;
import com.jinhui.service.user.UsersService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.UtilTool;

/**
 * 登录
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private ActionLogService actionLogService;

	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public BaseObject login(UserVo userVo, HttpServletRequest request, HttpServletResponse response){
		BaseObject bo = new BaseObject();
		try {
			Users us = new Users ();
			us.setCode(userVo.getCode());
			us.setMobile(userVo.getUserName());
			us.setPassword(UtilTool.md5Tool(userVo.getPassword()));
			bo =  usersService.login(us, request);
			if(userVo.isWechat()){
				String openid = WechatAuthorizeManager.getOpenId(request);
				if(logger.isInfoEnabled()){
					logger.info("查询微信授权登录,openid={}", openid);
				}
				if(StringUtils.isEmpty(openid))
					throw new IllegalArgumentException("openid为空");
				usersService.authorizeWechat(openid, userVo.getUserName());
			}
			//记录操作日志
			actionLogService.insertActionLog(request, bo.getJson());
			return bo;
		} catch (Exception e) {
			logger.error("【登录login】异常："+e);
			bo.setCode(1);
			bo.setMessage("用户登录失败!");
			return bo;
		}	
	}
	
	/**
	 * 测试验证是否存在
	 * @param ticket
	 * @param request
	 * @return
	 */
	@RequestMapping("/existInfo")
	@ResponseBody
	public String existInfo(String ticket ,HttpServletRequest request){
		String us =  (String) request.getSession().getAttribute(ticket);
		 
		return us;
		
	}
	/**
	 * 退出用户
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("/exitUser")
	@ResponseBody
	public BaseObject exitUser(String ticket ,HttpServletRequest request){
		BaseObject bo = new BaseObject();
		try {
			RedisUtils.removeValue(ticket, ConstantEntity.USER_TICKET);
			bo.setCode(0);
			bo.setMessage("已退出");
			return bo;
		} catch (Exception e) {
			logger.error("【退出exitUser】RedisUtils.removeValue异常："+e);
			bo.setCode(1);
			bo.setMessage("");
			return bo;
		}	
	}

}
