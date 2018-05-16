package com.jinhui.service.organization;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jinhui.model.Organization;

public interface OrganizationService {
	
	
	/**
	 * 
	 * 机构信息分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Organization> queryOrganizationPage(Organization organizationint,int pageIndex, int pageSize);
	
	/**
	 * 机构信息总数
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	Integer queryOrganizationCount(Organization organization);
	
	/**
	 * 机构信息详情
	 * @param gid
	 * @return
	 */
	Organization queryOrganizationDetail(Integer gid);
	
	/**
	 * 机构信息---添加
	 * @param organization
	 * @throws Exception
	 */
	void insert(Organization organization)throws Exception;

//	void insert(Organization organization,List<MultipartFile> busiLicence,List<MultipartFile> certificate,HttpServletRequest request)throws Exception;
	

	List<Organization> queryOrganizationAll(Organization organizationint);
	void updateNotNullOrganization(Organization organizationint)throws Exception;
//	void updateNotNullOrganization(Organization organizationint,List<MultipartFile> busiLicence,List<MultipartFile> certificate)throws Exception;
}
