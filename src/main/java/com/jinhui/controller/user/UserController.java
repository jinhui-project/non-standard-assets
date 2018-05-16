package com.jinhui.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;
import com.jinhui.model.UsersResponse;
import com.jinhui.service.user.UsersService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.UtilTool;

/**
 * 用户信息
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UsersService usersService;
	
	
	/**
	  * token失效处理
	  * 
	  * @param token
	  * @return
	  */
	 @RequestMapping(value="/msgErrorInfo", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	 @ResponseBody
	 public String  msgErrorInfo() {
	     BaseObject ob = new BaseObject();
	     ob.setCode(9);  //token失效代码
	     ob.setMessage("登录过期，请重新登录！");
	     return JSON.toJSONString(ob);
	 }
	 
	/**
	 *判断token失效没有
	 * @param token
	 * @return
	 */
	@RequestMapping("/ckToken")
	@ResponseBody
	public BaseObject ckToken(String token){
		BaseObject ob = new BaseObject();
	   try {
			String username =(String) RedisUtils.getValue(token ,ConstantEntity.USER_TICKET);
			if(null != username){
				ob.setCode(0);
				ob.setMessage("");
				return ob;
			}
			ob.setCode(1);
			ob.setMessage("");
		} catch (Exception e) {
			logger.error("判断token失效没有【判断token失效没有】ckToken异常："+e);
			ob.setCode(1);
			ob.setMessage("");
		}
		return ob;
		
	}
	
	/**
	 * 分页
	 * @param users
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryUserPage",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryUserPage(Users users,int pageIndex, int pageSize){
		UsersResponse ur = new UsersResponse();
		List<Users> list =usersService.queryUsersPage(users, pageIndex, pageSize);
		ur.setList(list);
		ur.setTotalCount(usersService.queryUsersCount(users));
		
		return JSON.toJSONString(ur);	
	}
	
	
	/**
	 * 用户信息根据id查询
	 * @return
	 */
	@RequestMapping(value="/selectById",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String  selectById(Integer id){
		Users us = usersService.selectById(id);
		us.setPassword("");
		return JSON.toJSONString(us);		
	}
	
	
	/**
	 * 用户信息审核/用户信息维护  统一用着一个
	 * @return
	 */
	@RequestMapping(value="/updateUserStateAndInfo",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public BaseObject updateUserStateAndInfo(Users users){
		BaseObject bo = new BaseObject();
		if(null == users.getPassword() || "".equals( users.getPassword() )){
			bo.setCode(1);
			bo.setMessage("请输入密码");
		}
		try {
			if(null == users.getId() || users.getId() == 0 ){
				users.setUsername(users.getMobile());
				users.setPassword(UtilTool.md5Tool(users.getPassword()));
				usersService.registered(users);
				bo.setCode(0);
				bo.setMessage("");
			}else{
				users.setPassword(UtilTool.md5Tool(users.getPassword()));
				usersService.updateNotNullInfo(users);
				bo.setCode(0);
				bo.setMessage("");
			}
			
		} catch (Exception e) {
			logger.error("【用户信息审核/用户信息维护  updateUserStateAndInfo】异常："+e);
			bo.setCode(1);
			bo.setMessage("用户信息审核/用户信息维护 异常");
		}
		return bo;
		
	}
}
