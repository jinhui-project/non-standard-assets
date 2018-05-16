package com.jinhui.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.model.BaseObject;
import com.jinhui.model.Users;

public interface UsersService {
	
	/**
	 * 注册
	 * @param record
	 * @throws Exception
	 */
//	void registered( Users record,List<MultipartFile> imgcontent,HttpServletRequest request )throws Exception;
	
	int registered( Users record)throws Exception;
	
	/**
	 * 修改
	 * @param record
	 */
	void updateNotNullInfo(@Param("record") Users record )throws Exception;
	
	/**
	 * 登录查询
	 * @param record
	 * @return
	 * @throws Exception
	 */
	BaseObject login(Users record, HttpServletRequest request)throws Exception;
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Users selectById(Integer id);
	
	/**
	 * 分页查询
	 * @param paramsMap
	 * @return
	 */
	List<Users>queryUsersPage(Users users,int pageIndex, int pageSize);
	/**
	 * 查询总数
	 * @param paramsMap
	 * @return
	 */
	Integer queryUsersCount(Users users);
	
	
	Users selectByName( String userName );
	
	
	Users selectByuserName( Users users );

	/**
	 * 使用微信授权登录
	 * @param openId
	 * @return
	 */
	Users identifyByOpenId(String openId);

	void authorizeWechat(String openid, String username);
}
