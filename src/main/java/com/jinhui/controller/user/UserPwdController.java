package com.jinhui.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;
import com.jinhui.service.user.UsersService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.UtilTool;

/**
 * 前台用户修改密码
 * 
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class UserPwdController {

	Logger logger = LoggerFactory.getLogger(UserPwdController.class);

	@Autowired
	private UsersService usersService;
	
	/**
	 * 判断用户是否存在
	 * @param users
	 * @return
	 */
	@RequestMapping("/isUserInfo")
	@ResponseBody
	public BaseObject isUserInfo(Users users){
		BaseObject bo = new BaseObject();
		Users us = usersService.selectByuserName(users);
		if( null == us ){
			bo.setCode(1);
			bo.setMessage("输入的手机号不存在!");
			return bo;
		}
		bo.setCode(0);
		bo.setMessage("");
		return bo;
		
	}

	/**
	 * 用户修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/pwdModify")
	@ResponseBody
	public BaseObject pwdModify(String phone, String password, String code, String codeImg) {
		BaseObject bo = new BaseObject();
		Users users = new Users();
		
		users.setUsername(phone);
		Users us = usersService.selectByuserName(users);
		if( null == us ){
			bo.setCode(1);
			bo.setMessage("输入的手机号不存在!");
			return bo;
		}
		if(null == codeImg || "".equals(codeImg)){
			bo.setCode(1);
			bo.setMessage("请输入图形验证码!");
			return bo;
		}
		if(null == code || "".equals(code)){
			bo.setCode(1);
			bo.setMessage("请输入手机验证码!");
			return bo;
		}
		
		String codeImgRedis = "";
		try {
			codeImgRedis = (String) RedisUtils.getValue(codeImg.toUpperCase(),ConstantEntity.IMG_CODE);
		} catch (Exception e1) {
			logger.error("【用户注册 图形验证码 registeredInfo】RedisUtils异常："+e1);
			bo.setCode(1);
			bo.setMessage("图形验证码失效!");
			return bo;
		}
		if(!codeImg.equalsIgnoreCase(codeImgRedis)){
			bo.setCode(1);
			bo.setMessage("图形验证码不正确!");
			return bo;
		}
		
		String redisCode = "";
		try {
			redisCode = (String) RedisUtils.getValue(phone,ConstantEntity.PWD_CODE);
		} catch (Exception e1) {
				logger.error("【用户修改密码 pwdModify】RedisUtils异常："+e1);
				bo.setCode(1);
				bo.setMessage("手机验证码不存在!");
				return bo;		
		}
		
		if( null != redisCode || !"".equals(redisCode)){
			if(!code.equalsIgnoreCase(redisCode)){
				bo.setCode(1);
				bo.setMessage("手机验证码不正确!");
				return bo;
			}
		}else{
			bo.setCode(1);
			bo.setMessage("手机验证码不存在!");
			return bo;
		}

		
		us.setPassword(UtilTool.md5Tool(password));
		try {
			usersService.updateNotNullInfo(us);
			bo.setCode(0);
			bo.setMessage("用户密码修改成功！");
		} catch (Exception e) {
			logger.error("【用户修改密码   pwdModify】异常："+e);
			bo.setCode(1);
			bo.setMessage("用户密码修改失败！");
		}
		return bo;
	}

}
