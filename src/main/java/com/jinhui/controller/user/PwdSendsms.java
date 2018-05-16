package com.jinhui.controller.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esms.common.entity.GsmsResponse;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;
import com.jinhui.service.user.UsersService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.SMSender;

@Controller
@RequestMapping("/admin")
public class PwdSendsms {
	Logger logger = LoggerFactory.getLogger(PwdSendsms.class);
	@Value("${accountName}")
	private String	accountName;
	@Value("${password}")
	private String	password;
	@Value("${gateWayIP}")
	private String gateWayIP;
	@Value("${gateWayPort}")
	private int gateWayPort;
	
	@Autowired
	private UsersService usersService;

	/**
	 * 手机 用户修改密码发送验证码
	 * 
	 * @return
	 */
	@RequestMapping("/sendsmsPwd")
	@ResponseBody
	public BaseObject sendsmsPwd(String mobile, String codeImg) {
		BaseObject bo = new BaseObject();
		
		Users u = usersService.selectByName(mobile);
		if( null == u ){
			bo.setCode(1);
			bo.setMessage("该手机号码未注册，无法重置密码!");
			return bo;
		}

		if (null == codeImg || "".equals(codeImg)) {
			bo.setCode(1);
			bo.setMessage("请输入图形验证码!");
			return bo;
		}
		String sessionCode = "";
		try {
			sessionCode = (String) RedisUtils.getValue(codeImg.toUpperCase(),
					ConstantEntity.IMG_CODE);
		} catch (Exception e1) {
			logger.error("【图形验证码】RedisUtils异常：" + e1);
			bo.setCode(1);
			bo.setMessage("图形验证码不存在!");
			return bo;
		}
		if (null != sessionCode || !"".equals(sessionCode)) {
			if (!codeImg.equalsIgnoreCase(sessionCode)) {
				bo.setCode(1);
				bo.setMessage("图形验证码不正确!");
				return bo;
			}
		}

		bo.setCode(0);
		bo.setMessage("发送成功!");
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		String content = new String("您正在使用金飞镖平台用户密码修改服务,验证码为：" + mobile_code
				+ ",有效时间3分钟,请勿将其提供予其他人。");
		GsmsResponse resp = null;
		SMSender smSender = null;
		try {
			smSender = new SMSender(accountName,password,gateWayIP,gateWayPort);
			resp = smSender.doSendSms(mobile, content);
			if (resp.getResult() == 0) {
				logger.info("手机号为：【" + mobile + "】" + "验证码为：" + mobile_code);
				RedisUtils.setValue(mobile, ConstantEntity.PWD_CODE,
						mobile_code + "", ConstantEntity.PWD_CODE_EXPIRE);
				bo.setCode(0);
				bo.setMessage("验证码发送成功！");
			} else {
				bo.setCode(1);
				bo.setMessage(resp.getMessage());
			}
		} catch (Exception e) {
			bo.setCode(1);
			bo.setMessage("验证码发送失败！");
		}
			
		return bo;
	}

}
