package com.jinhui.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.Users;

public class TokenUtil {


	public static void tokenOperate(HttpServletRequest request,UserMapper userMapper)
			throws Exception {
		String userReq = (String) RedisUtils.getValue(request.getParameter("token"), ConstantEntity.USER_TICKET);
		Users users  = JSON.parseObject(userReq,Users.class);
		if (users != null) {
			if(users.getUsername() != null && !"".equals(users.getUsername())){
				users = userMapper.selectByuserName(users);				
				if(users != null){
					String ticket = generateTicket(request, users);
					RedisUtils.removeValue(ticket, ConstantEntity.USER_TICKET);
					RedisUtils.setValue(ticket, ConstantEntity.USER_TICKET,JSON.toJSONString(users), 30);
				}else{
					System.out.println("打印用户信息空");
				}
				
			}
			
		}
	}

	/**
	 * 根据用户信息生成ticket
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private static String generateTicket(HttpServletRequest request, Users user) {
		long current = System.currentTimeMillis();
		StringBuilder bf = new StringBuilder();
		bf.append(user.getId());
		bf.append(user.getGid());
		bf.append(user.getUsername());
		bf.append(request.getHeader("x-forwarded-for")); // ticket包含客户端ip信息，同一个帐号在不同机器同时登录的情况应该生成不同的ticket
		bf.append(current);
		String msg = "";
		System.out.println("【bf.toString()】：" + bf.toString());
		msg = UtilTool.md5Tool(bf.toString());
		return msg;
	}

}
