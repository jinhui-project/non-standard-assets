package com.jinhui.service.orgPreferCollect;

import com.jinhui.model.OrgPreferCollect;
import com.jinhui.model.Organization;

/**
 * @time 2017-3-8 上午10:29:31  
 * @author wsc
 * @desc 机构偏好信息采集
 *
 */
public interface OrgPreferCollectService {
	
	/**
	 * 机构偏好信息---添加
	 * @param orgPreferCollect
	 * 
	 */
	int insert(OrgPreferCollect orgPreferCollect);
	
	/**
	 * 机构偏好信息---编辑
	 * @param orgPreferCollect
	 * 
	 */
	int update(OrgPreferCollect orgPreferCollect);
	
	/**
	 * 根据机构id查机构偏好信息
	 * @param orgId
	 * @return
	 */
	OrgPreferCollect selectByOrgId(Integer orgId);
	
	int selectCountByOrgId(Integer orgId);

}
