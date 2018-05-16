package com.jinhui.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Users;

public interface UserMapper {
	
	
	
	int insert(@Param("record") Users record );
	
	Users queryUsersInfo(@Param("record") Users record );
	
	Users selectByuserName(@Param("record") Users record );
	
	Users selectById(@Param("id")Integer id);
	
	List<Users>queryUsersPage(Users users);
	
	Integer queryUsersCount(Users users);
	
	void updateNotNullInfo(@Param("record") Users record );
	
	
	Users selectByName(@Param("record") Users record );

	void authorizeWechat(Map map);
	Users findByOpenId(String openid);
}
