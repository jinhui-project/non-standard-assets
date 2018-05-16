package com.jinhui.service.orgPreferCollect.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.constant.ConstantEntity;
import com.jinhui.mapper.orgPreferCollect.OrgPreferCollectMapper;
import com.jinhui.mapper.organization.OrganizationMapper;
import com.jinhui.mapper.upload.FileinfosMapper;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.OrgPreferCollect;
import com.jinhui.model.Organization;
import com.jinhui.service.orgPreferCollect.OrgPreferCollectService;
import com.jinhui.service.organization.OrganizationService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;

/**
 * @time 2017-3-8 上午10:30:10  
 * @author wsc
 * @desc 机构偏好信息采集
 *
 */
@Service
public class OrgPreferCollectServiceImpl implements OrgPreferCollectService {
	@Autowired
	private OrgPreferCollectMapper orgPreferCollectMapper;

	Logger logger = LoggerFactory.getLogger(OrgPreferCollectServiceImpl.class);

	@Override
	public int insert(OrgPreferCollect orgPreferCollect) {
		OrgPreferCollect opc = orgPreferCollectMapper.selectByOrgId(orgPreferCollect.getOrganizationId());
		int flag = 0;
		if(opc == null){
			flag = orgPreferCollectMapper.insertSelective(orgPreferCollect);
		}
		return flag;
	}

	@Override
	public OrgPreferCollect selectByOrgId(Integer orgId) {
		return orgPreferCollectMapper.selectByOrgId(orgId);
	}

	@Override
	public int selectCountByOrgId(Integer orgId) {
		OrgPreferCollect opc = orgPreferCollectMapper.selectByOrgId(orgId);
		int flag = 0;
	    if(opc != null){  //已存在机构偏好信息
	    	flag = 1;
	    }
		return flag;
	}

	@Override
	public int update(OrgPreferCollect orgPreferCollect) {
		return orgPreferCollectMapper.updateByPrimaryKeySelective(orgPreferCollect);
	}

	
}
