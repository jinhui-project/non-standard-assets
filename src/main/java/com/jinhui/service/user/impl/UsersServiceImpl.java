package com.jinhui.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jinhui.wechat.WechatAuthorizeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.enums.AuditStatus;
import com.jinhui.mapper.upload.FileinfosMapper;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Organization;
import com.jinhui.model.Users;
import com.jinhui.service.organization.OrganizationService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.service.user.UsersService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.UtilTool;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
public class UsersServiceImpl implements UsersService {
	Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private FileinfosService fileinfosService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private FileinfosMapper fileinfosMapper;
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private  String uploadPath;
	/**
	 * 注册时查询用户名称是否已经存在
	 */
	public Users selectByName(String usersName) {
		Users user = new Users();
		user.setMobile(usersName);
		Users u = userMapper.selectByName(user);
		return u;
	}

	/**
	 * 注册用户信息
	 */
	public int registered(Users record) throws Exception {
		record.setPower("0000");
		record.setAuditStatus(AuditStatus.NOT_ACTIVATION.code);//初始化状态：未激活
		record.setPassword(UtilTool.md5Tool(record.getPassword()));
		record.setCreateTime(UtilTool.dateConLon());
		return	userMapper.insert(record);
	}

	/**
	 * 登录查询
	 */
	public BaseObject login(Users record, HttpServletRequest request) throws Exception {
		BaseObject bo = new BaseObject();
		String code = record.getCode();
		if(!WechatAuthorizeManager.isWechatRequest(request)) {
			String redisNum = null;
			redisNum = (String) RedisUtils.getValue(record.getMobile(), ConstantEntity.LOGIN_NUM);
			if (null != redisNum && !"".equals(redisNum)) {
				if (Integer.valueOf(redisNum) >= 3) {
					if (null == code || "".equals(code)) {
						bo.setCode(1);
						bo.setMessage("帐号或密码输入错误!");
						return bo;
					}
					String sessionCode = "";
					try {
						sessionCode = (String) RedisUtils.getValue(code.toUpperCase(), ConstantEntity.IMG_CODE);
					} catch (Exception e1) {
						logger.error("【登录login】RedisUtils异常：" + e1);
						bo.setCode(1);
						bo.setMessage("验证码不存在!");
						return bo;
					}
					if (null != sessionCode || !"".equals(sessionCode)) {
						if (!code.equalsIgnoreCase(sessionCode)) {
							bo.setCode(1);
							bo.setMessage("验证码不正确!");
							return bo;
						}
					}
				}
			}
		}
		bo = loginCk(record,request);
		return bo;
	}
	
	/**
	 * 根据id查询
	 */
	public Users selectById(Integer id) {
		Users user = userMapper.selectById(id);
		List<Fileinfos> list = new ArrayList<Fileinfos>();
		user.setCreateTimeStr(UtilTool.stampToDate(user.getCreateTime()));
		if( null!= user.getCardPath() && !"".equals(user.getCardPath())){
			String [] arrFid = user.getCardPath().split(",");
			if(arrFid.length > 0){
				for(String fid : arrFid){
					Fileinfos f = fileinfosService.queryFileinfos(Long.valueOf(fid));
					f.setUploadTimeStr(UtilTool.stampToDate(f.getUploadTime()));
					f.setEditTimeStr(UtilTool.stampToDate(f.getEditTime()));
					list.add(f);
				}
			}	
		}
		user.setFileinfos(list);
		return user;
	}
	
	/**
	 * 修改不等于nill的实体
	 */
	public void updateNotNullInfo(Users record)throws Exception {
		record.setEditorTime(UtilTool.dateConLon());
		userMapper.updateNotNullInfo(record);
	}
	
	/**
	 * 分页查询
	 */
	public List<Users> queryUsersPage(Users users,int pageIndex, int pageSize) {
		 List<Users> list = new  ArrayList<Users>();
		int begin = pageIndex * pageSize;
		users.setOffset(begin);
		users.setLimit(pageSize);	
		 List<Users> listUser  = userMapper.queryUsersPage(users);
		if( null != listUser && listUser.size() >0 ){
			for( Users u:listUser ){
				u.setCreateTimeStr(UtilTool.stampToDate(u.getCreateTime()));
				u.setEditorTimeStr(UtilTool.stampToDate(u.getEditorTime()));
				list.add(u);
			}
		}
		return list;
	}
	/**
	 * 查询总数量
	 */
	public Integer queryUsersCount(Users users) {
		return userMapper.queryUsersCount(users);
	}

	
	
	

	@Override
	public Users selectByuserName(Users users) {	
		return userMapper.selectByuserName(users);
	}

	public Users identifyByOpenId(String openId) {
		if(StringUtils.isEmpty(openId))
			throw new IllegalArgumentException("openid为空");
		return userMapper.findByOpenId(openId);
	}

	public void authorizeWechat(String openid, String username) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("openid", openid);
		map.put("username", username);
		userMapper.authorizeWechat(map);
	}


	/**
	 * 根据用户信息生成ticket
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	private String generateTicket(HttpServletRequest request,Users user ) 
	{
		long current  = System.currentTimeMillis();	
		StringBuilder bf = new StringBuilder();
		 bf.append(user.getId());
		 bf.append(user.getGid());
		 bf.append(user.getUsername());
		 bf.append(request.getHeader("x-forwarded-for")); //ticket包含客户端ip信息，同一个帐号在不同机器同时登录的情况应该生成不同的ticket
		 bf.append(current);
		 String msg="";
		 System.out.println("【bf.toString()】："+bf.toString());
		 msg =UtilTool.md5Tool(bf.toString());	
		return msg;
	}

	/**
	 * 登录是次数验证   
	 * @param record
	 * @param request
	 * @return
	 * @throws Exception
	 */
    private BaseObject loginCk(Users record,HttpServletRequest request) throws Exception{
    	BaseObject bo = new BaseObject();
  		Users users = userMapper.queryUsersInfo(record);
  		if( null != users){
  			if(users.getAuditStatus() == 2){
  				bo.setCode(1);
  				bo.setMessage("用户机是禁用状态,无法登录!");
  				return bo;
  			}
  	
  			String ticket = generateTicket( request, users );	
  			RedisUtils.setValue( ticket ,ConstantEntity.USER_TICKET, JSON.toJSONString(users),ConstantEntity.REDISUTILS_EXPIRE);
  			Organization ot = new Organization();
  			if(null != users.getGid()){
  				try {
  					ot = organizationService.queryOrganizationDetail(users.getGid());
  				} catch (Exception e) {
  					bo.setCode(1);
  					bo.setMessage("用户机构信息查询不到,无法登录!");
  					return bo;
  				}
  			}
  			
  			users.setOrganization(ot);
  			bo.setCode(0);
  			bo.setMessage(ticket);
  			bo.setJson(users);
  			bo.setExpire(System.currentTimeMillis() / 1000);
  			bo.setRedisutils_expire(ConstantEntity.REDISUTILS_EXPIRE);
  			RedisUtils.removeValue(record.getMobile() ,ConstantEntity.LOGIN_NUM);
  		}else{
  			int num=0;
  			num++;
  			String temp = null;
  			temp = (String) RedisUtils.getValue(record.getMobile() ,ConstantEntity.LOGIN_NUM);
  			logger.info("【前台 登录帐号】"+record.getMobile()+",【获取错误次数】："+temp);
  			if(null!=temp&&!"".equals(temp)){
  				num =num+Integer.valueOf(temp);
  			}
  			logger.info("【前台 登录帐号】"+record.getMobile()+",【错误次数】："+num);
  			RedisUtils.setValue(record.getMobile() ,ConstantEntity.LOGIN_NUM, num+"",60*24);
  			bo.setCode(1);
  			bo.setMessage("帐号或密码输入错误!");
  			bo.setLoginNum(num);
  		}
  		return bo;
      }




}
