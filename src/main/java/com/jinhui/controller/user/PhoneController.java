package com.jinhui.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esms.common.entity.GsmsResponse;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.SMSender;

@Controller
@RequestMapping("/admin")
public class PhoneController {
	Logger logger = LoggerFactory.getLogger(PhoneController.class);
	@Value("${accountName}")
	private String	accountName;
	@Value("${password}")
	private String	password;
	@Value("${gateWayIP}")
	private String gateWayIP;
	@Value("${gateWayPort}")
	private int gateWayPort;
	
	/**
	 * 手机发送验证码
	 * 
	 * @return
	 */
	@RequestMapping("/sendsms")
	@ResponseBody
	public BaseObject sendsms(String mobile,String codeImg) {
		BaseObject bo = new BaseObject();
		if(null == codeImg || "".equals(codeImg)){
			bo.setCode(1);
			bo.setMessage("请输入验证码!");
			return bo;
		}
		String sessionCode = "";
		try {
			sessionCode = (String) RedisUtils.getValue(codeImg.toUpperCase(),ConstantEntity.IMG_CODE);
		} catch (Exception e1) {
				logger.error("【注册时图形验证码】RedisUtils异常："+e1);
				bo.setCode(1);
				bo.setMessage("注册时图形验证码不存在!");
				return bo;		
		}
		if( null != sessionCode || !"".equals(sessionCode)){
			if(!codeImg.equalsIgnoreCase(sessionCode)){
				bo.setCode(1);
				bo.setMessage("注册时图形验证码不正确!");
				return bo;
			}
		}
		bo.setCode(0);
		bo.setMessage("发送成功!");
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String content = new String("您正在使用金飞镖平台服务,验证码为：" + mobile_code + ",有效时间3分钟,请勿将其提供予其他人。");
		GsmsResponse resp = null;
		SMSender smSender = null;
		try {
			smSender = new SMSender(accountName,password,gateWayIP,gateWayPort);
			resp = smSender.doSendSms(mobile, content);
			if (resp.getResult() == 0) {
				logger.info("手机号为：【"+ mobile +"】"+"验证码为："+mobile_code);
				RedisUtils.setValue( mobile_code+"",ConstantEntity.PHONE_CODE, mobile_code+"",ConstantEntity.REDISUTILS_CODE_EXPIRE);
				bo.setCode(0);
				bo.setMessage("验证码发送成功");
			}else{
				bo.setCode(1);
				bo.setMessage(resp.getMessage());
			}
		} catch (Exception e) {
			bo.setCode(1);
			bo.setMessage("验证码发送失败！");
		}
		return bo;
	}
	
	/**
	 * 判断 当前手机号码 获取验证码 是否在规定时间获取  否则 不能重新获取验证码
	 * @param req
	 * @param mobile
	 * @return
	 */
	public boolean codeFlag(HttpServletRequest req ,String mobile) {
		String str = "";
		boolean flag = false;
		try {
			str = req.getSession().getAttribute(mobile).toString();
		} catch (Exception e) {
			
		}
		if( null != str && !"".equals(str)){
			flag =  false;
		}else{
			req.getSession().setAttribute(mobile,mobile);
			req.getSession().setMaxInactiveInterval(1*60);
			flag =  true;
			
		}
		return flag;
		
	}
}
