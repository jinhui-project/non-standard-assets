package com.jinhui.controller.orgPreferCollect;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.OrgPreferCollect;
import com.jinhui.service.orgPreferCollect.OrgPreferCollectService;
import com.jinhui.util.FormatReturn;
import com.jinhui.util.TokenUtil;

/**
 * 
 * @time 2017-3-8 上午10:37:21  
 * @author wsc
 * @desc  机构偏好信息采集
 *
 */
@Controller
@RequestMapping("/orgPreferCollect")
public class OrgPreferCollectController {
	private static Logger logger = LoggerFactory.getLogger(OrgPreferCollectController.class);

	@Autowired
	private OrgPreferCollectService orgPreferCollectService;
	@Autowired
	private  UserMapper userMapper;
	
	/**
	 * 添加机构偏好信息采集
	 * @param orgPreferCollect
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addOrgPreferCollect",method=RequestMethod.POST)
	@ResponseBody
	public String addOrgPreferCollect(HttpServletRequest request,OrgPreferCollect orgPreferCollect) throws Exception{
		logger.info("输入：  "+ "orgPreferCollect="+orgPreferCollect);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = orgPreferCollectService.insert(orgPreferCollect);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 编辑机构偏好信息采集
	 * @param orgPreferCollect
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateOrgPreferCollect",method=RequestMethod.POST)
	@ResponseBody
	public String updateOrgPreferCollect(HttpServletRequest request,OrgPreferCollect orgPreferCollect) throws Exception{
		logger.info("输入：  "+ "orgPreferCollect="+orgPreferCollect);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = orgPreferCollectService.update(orgPreferCollect);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 根据机构id查询机构偏好信息
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/queryOrgPreferCollectByOrgId",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryOrgPreferCollect(@RequestParam("orgId") Integer orgId){
		logger.info("输入：  "+ "orgId="+orgId);
		OrgPreferCollect orgPreferCollect = orgPreferCollectService.selectByOrgId(orgId);
		logger.info("输出：   "+JSON.toJSONString(orgPreferCollect));
		return JSON.toJSONString(orgPreferCollect,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 查询是否已录入机构偏好信息
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/isExistOrgPrefer",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String isExistOrgPrefer(@RequestParam("orgId") Integer orgId){
		logger.info("输入：  "+ "orgId="+orgId);
		int flag = orgPreferCollectService.selectCountByOrgId(orgId);
		logger.info("输出：   "+JSON.toJSONString(flag));
		return JSON.toJSONString(flag,SerializerFeature.WriteMapNullValue);
	}
	
}
