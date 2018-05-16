package com.jinhui.controller.user;

import com.jinhui.controller.base.WebConstants;
import com.jinhui.controller.base.WebResult;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;
import com.jinhui.service.user.UsersService;
import com.jinhui.wechat.WechatAuthorizeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class WechatLoginController {
	Logger logger = LoggerFactory.getLogger(WechatLoginController.class);
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/refreshToken")
	@ResponseBody
	public String refreshToken(){
		WebResult result = WebResult.refreshTokenResult("刷新token");
		return result.toJson();
	}

	@RequestMapping(WechatAuthorizeManager.WechatAuthCodeUrl)
	@ResponseBody
	public String wechatCode(String code, HttpServletRequest request, HttpServletResponse response){
		try {
			WechatAuthorizeManager.requestWechat(request,code);
			WechatAuthorizeManager.redirectRequest(request, response);
			return null;
		} catch (Exception e) {
			logger.warn("获取微信授权token错误:{}", e);
			WebResult result = WebResult.failureResult(e.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping("/wechatLogin")
	@ResponseBody
	public BaseObject wechatLogin(HttpServletRequest request){
		BaseObject bo = new BaseObject();
		try {
			String openid = WechatAuthorizeManager.getOpenId(request);
			if(StringUtils.isEmpty(openid)) {
				bo.setCode(WebConstants.REFRESH_TOKEN_CODE);
				bo.setMessage("刷新微信授权token");
				return bo;
			}
			if(logger.isInfoEnabled()) {
				logger.info("查询微信授权登录,openid={}", openid);
			}
			Users users = usersService.identifyByOpenId(openid);
			if(users == null) {
				bo.setCode(WebConstants.REDIRECT_CODE);
				bo.setMessage("微信未授权登录");
				return bo;
			}
			bo = usersService.login(users, request);
		} catch (Exception ex){
			logger.warn("微信授权登录异常: {}", ex);
			bo.setCode(WebConstants.RESULT_FAIL_CODE);
			bo.setMessage(ex.getMessage());
		}
		return bo;
	}

}
