package com.jinhui.controller.organization;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Organization;
import com.jinhui.model.OrganizationResponse;
import com.jinhui.service.organization.OrganizationService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.util.UpLoadUtil;

/**
 * 机构信息Controller
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class OrganizationController {
	
	Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private FileinfosService fileinfosService;
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadDelPath}")
	private  String uploadDelPath;
	
	/**
	 * 机构信息----分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryOrganizationPage",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryOrganizationPage(Organization organizationint,int pageIndex, int pageSize){
		OrganizationResponse or = new OrganizationResponse();

		List<Organization> list = organizationService.queryOrganizationPage(organizationint,pageIndex, pageSize);		
		or.setList(list);
		or.setTotalCount(organizationService.queryOrganizationCount(organizationint));
		return JSON.toJSONString(or);
	}
	
	
	/**
	 * 机构信息获取全部  按照功能调用
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryOrganizationAll",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryOrganizationAll(Organization organizationint){
//		OrganizationResponse or = new OrganizationResponse();

		List<Organization> list = organizationService.queryOrganizationAll(organizationint);		
//		or.setList(list);
		return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue);
	}
	
	
	/**
	 * 机构信息----详情
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryOrganizationDetail",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryOrganizationDetail(Integer gid){

		Organization organization = new Organization();
		organization = organizationService.queryOrganizationDetail(gid);
		return JSON.toJSONString(organization);
	}
	/**
	 * 机构信息--添加
	 * @param organization
	 * @param imgcontentBusiLicence 营业执照路径
	 * @param imgcontentCertificate 资质附件路径
	 * @return
	 */
	@RequestMapping("/saveOrganization")
	@ResponseBody
//	public BaseObject saveOrganization(Organization organization,List<MultipartFile> imgcontentBusiLicence,
//			List<MultipartFile> imgcontentCertificate,HttpServletRequest httpsRequest){
	public BaseObject saveOrganization(Organization organization){
		BaseObject bo = new BaseObject();
//		for(MultipartFile m: imgcontentBusiLicence){
//			if(m.getSize() == 0){
//				bo.setMessage("请选择营业执照文件"); 
//				bo.setCode(1);
//				return bo;
//			}
//		}
//		for(MultipartFile m: imgcontentCertificate){
//			if(m.getSize() == 0){
//				bo.setMessage("请选择资质附件文件"); 
//				bo.setCode(1);
//				return bo;
//			}
//		}
		try {
//			organizationService.insert(organization, imgcontentBusiLicence, imgcontentCertificate, httpsRequest);
			organizationService.insert(organization);
			bo.setCode(0);
			bo.setMessage("机构信息保存成功!");
		} catch (Exception e) {
			logger.error("【机构信息--添加saveOrganization】异常："+e);
			bo.setCode(1);
			bo.setMessage("机构信息保存失败!");
		}
		return bo;
		
	}
	
	
	
	/**
	 * 机构信息--修改
	 * @param organization
	 * @param imgcontentBusiLicence 营业执照路径
	 * @param imgcontentCertificate 资质附件路径
	 * @return
	 */
	@RequestMapping("/updateOrganization")
	@ResponseBody
//	public BaseObject updateOrganization(Organization organization,List<MultipartFile> imgcontentBusiLicence,
//			List<MultipartFile> imgcontentCertificate){
	public BaseObject updateOrganization(Organization organization){	
		BaseObject bo = new BaseObject();
//		for(MultipartFile m: imgcontentBusiLicence){
//			if(m.getSize() == 0){
//				bo.setMessage("请选择营业执照文件"); 
//				bo.setCode(1);
//				return bo;
//			}
//		}
//		for(MultipartFile m: imgcontentCertificate){
//			if(m.getSize() == 0){
//				bo.setMessage("请选择资质附件文件"); 
//				bo.setCode(1);
//				return bo;
//			}
//		}
		try {
//			organizationService.updateNotNullOrganization(organization, imgcontentBusiLicence, imgcontentCertificate);
			organizationService.updateNotNullOrganization(organization);
			if( null != organization.getDelFid() && !"".equals( organization.getDelFid() )){
				delFId( organization.getDelFid());
			}

			bo.setCode(0);
			bo.setMessage("机构信息修改成功!");
		} catch (Exception e) {
			logger.error("【机构信息--添加saveOrganization】异常："+e);
			bo.setCode(1);
			bo.setMessage("机构信息修改失败!");
		}
		return bo;
		
	}
	
	public void delFId(String fid){
		List<Long> listId = new ArrayList<Long>();
		String [] tempId = fid.split(",");
		if(tempId.length > 0){
			for(String id : tempId){
				listId.add(Long.valueOf(id));
			}
			
			List<Fileinfos> fList = fileinfosService.queryList(listId);
			if( null !=fList && fList.size() > 0 ){
				try {
					for(Fileinfos f: fList){
						UpLoadUtil u = new UpLoadUtil();
						u.deleteUpLoad(f.getFilePath(), 0, uploadDelPath);
						fileinfosService.delFileinfosId(f.getId());
					}
				} catch (Exception e) {
					logger.error("文件删除【delFId】异常"+e);
				}
				
			}
		}	
	}

}


