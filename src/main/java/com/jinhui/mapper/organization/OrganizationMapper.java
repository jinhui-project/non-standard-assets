package com.jinhui.mapper.organization;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Organization;

public interface OrganizationMapper {
	
	/**
	 * 机构信息分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Organization> queryOrganizationPage(Organization organizationint);
	
	
	/**
	 * 查询总数量
	 */
	Integer queryOrganizationCount(Organization organizationint);
	
	/**
	 * 机构信息详情
	 * @param id
	 * @return
	 */
	Organization queryOrganizationDetail(@Param("id")Integer id);
	
	/**
	 * 机构信息--添加信息
	 * @param organization
	 */
	void insert(@Param("record")Organization organization);
	
	
	/**
	 * 机构信息分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Organization> queryOrganizationAll(Organization organizationint);
	
	void updateNotNullOrganization(Organization organizationint);

}
