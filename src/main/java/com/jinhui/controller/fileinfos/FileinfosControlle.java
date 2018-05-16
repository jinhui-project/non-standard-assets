package com.jinhui.controller.fileinfos;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.AgreementPowerTemplate;
import com.jinhui.model.AgreementTemplate;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.FileinfosResponse;
import com.jinhui.service.agreementpowertemplate.AgreementPowerTemplateService;
import com.jinhui.service.agreementtemplate.AgreementTemplateService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;


/**
 *文件信息Controller
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class FileinfosControlle {
	@Autowired
	private FileinfosService fileinfosService;
	@Autowired
	private AgreementPowerTemplateService agreementPowerTemplateService;
	
	@Autowired
	private AgreementTemplateService agreementTemplateService;
	
	Logger logger = LoggerFactory.getLogger(FileinfosControlle.class);
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private  String uploadPath;
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadDelPath}")
	private  String uploadDelPath;
	
	
	
	/**
	 * 文件信息----分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryFileInfoPage",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryFileInfoPage(Fileinfos fileinfos,int pageIndex, int pageSize){
		FileinfosResponse fr = new FileinfosResponse();
		List<Fileinfos> list= fileinfosService.queryPageList(fileinfos, pageIndex, pageSize);
		
		fr.setList(list);
		fr.setTotalCount(fileinfosService.queryPageCount(fileinfos));
		return JSON.toJSONString(fr);
	}
	
	
	/**
	 * 模版展示----分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryTemplateFilePage",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryTemplateFilePage(Fileinfos fileinfos,int pageIndex, int pageSize){
		FileinfosResponse fr = new FileinfosResponse();
		List<Fileinfos> notPathList = new ArrayList<Fileinfos>();
		List<Fileinfos> list= fileinfosService.queryTemplateFilePage(fileinfos, pageIndex, pageSize);
		for(Fileinfos f : list){
			f.setFilePath("");
			notPathList.add(f);
		}
		fr.setList(notPathList);
		fr.setTotalCount(fileinfosService.queryTemplateFilePageCount(fileinfos));
		return JSON.toJSONString(fr);
	}
	
	
	
	/**
	 * 权限       模版展示----分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryPowerTemplateFilePage",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryPowerTemplateFilePage(Fileinfos fileinfos,int pageIndex, int pageSize){
		FileinfosResponse fr = new FileinfosResponse();
		AgreementPowerTemplate at = new AgreementPowerTemplate();
		at.setGid(fileinfos.getGid());
		List<Fileinfos> notPathList = new ArrayList<Fileinfos>();
		
		List<Fileinfos> list= fileinfosService.queryTemplateFilePage(fileinfos, pageIndex, pageSize);
		AgreementPowerTemplate apt = agreementPowerTemplateService.findAgreementPower(at);
		for(Fileinfos f : list){
			AgreementTemplate template = agreementTemplateService.queryTemplateByFid( f.getId() );
			if( null == apt){
				f.setFilePath("");
				notPathList.add(f);
			}else{
				List<String> sList =  new ArrayList<String>();
				List<String>recordInstitutionsType = new ArrayList<String>();
				String[] tempProductTypeArr = apt.getProductType().split(",");
				String[] recordInstitutionsTypeArr = apt.getRecordInstitutionsType().split(",");
				for(String sTemp : tempProductTypeArr){
					sList.add(sTemp);
				}
				for(String rTemp : recordInstitutionsTypeArr){
					recordInstitutionsType.add(rTemp);
				}
				logger.info( "【角色权限判断】"+( (apt.getServiceType()>0 && apt.getServiceType() == template.getServiceType())||(apt.getMoneySideType()>0 &&  apt.getMoneySideType()==template.getMoneySideType())||(apt.getAssetType()>0 &&  apt.getAssetType()==template.getAssetType())||(apt.getOrganizationType()> 0 &&  apt.getOrganizationType()== template.getOrganizationType())||(apt.getOtherType()> 0 && apt.getOtherType() ==template.getOtherType())||(apt.getTrusteeType()>0 &&  apt.getTrusteeType() == template.getTrusteeType())));
				if( sList.contains(template.getProductType()+"") && 
					( recordInstitutionsType.contains(template.getRecordInstitutionsType()+"") || null == template.getRecordInstitutionsType()) &&
			      (
			       (apt.getServiceType()>0 && apt.getServiceType() == template.getServiceType())||
				   (apt.getMoneySideType()>0 &&  apt.getMoneySideType()==template.getMoneySideType())||
				   (apt.getAssetType()>0 &&  apt.getAssetType()==template.getAssetType())||
				   (apt.getOrganizationType()> 0 &&  apt.getOrganizationType()== template.getOrganizationType())||
				   (apt.getOtherType()> 0 && apt.getOtherType() ==template.getOtherType())||
				   (apt.getTrusteeType()>0 &&  apt.getTrusteeType() == template.getTrusteeType())
				 
				)){
					logger.info("展示路径的："+f.getId());
					notPathList.add(f);
				}else{
					f.setFilePath("");
					logger.info("不展示路径的【】【】【】【】【】【】【】："+f.getId());
					notPathList.add(f);
				}
			}
			
		}
		fr.setList(notPathList);
		fr.setTotalCount(fileinfosService.queryTemplateFilePageCount(fileinfos));
		return JSON.toJSONString(fr);
	}
	

	 /**
	  * 文件下载
	  * @param id
	  * @param request
	  * @param response
	  */
	 @RequestMapping("/download")  
	 public void download(long id ,HttpServletRequest request,HttpServletResponse response){
		 UpLoadUtil u = new UpLoadUtil();
		 Fileinfos  f= fileinfosService.queryFileinfos(id);
		 try {
			u.downloadFile(f.getFilePath(),f.getFileName(), uploadDelPath,request, response);
		} catch (UnsupportedEncodingException e) {
			logger.error("【文件下载】download"+e);
		}
	 }
	
	/**
	 * 文件删除
	 * @param fid
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public BaseObject deleteFile(long fid){
		BaseObject bo =  new BaseObject();
		 Fileinfos  f = fileinfosService.queryFileinfos(fid);
		 if( null == f){
			bo.setCode(1);
			bo.setMessage("文件不存在!");
			return bo;
		 }
		 UpLoadUtil u = new UpLoadUtil();
		 try {
			boolean flag = u.deleteUpLoad(f.getFilePath(), 0, uploadDelPath);
			if(flag){
				fileinfosService.delFileinfosId(fid);
				bo.setCode(0);
				bo.setMessage("文件删除成功!");
			}else{
				bo.setCode(1);
				bo.setMessage("文件删除失败!");
			}
			
		} catch (Exception e) {
			logger.error("【文件删除】deleteFile"+e);
			bo.setCode(1);
			bo.setMessage("文件删除失败!");
		}		
		return bo;	
	}
	
	
	/**
	 * 前台    用户或者模版文件上传文件  通用
	 * type 0：用户，1模版文件
	 * @return
	 */
	@RequestMapping("/publicUploadInfo")
	@ResponseBody
	public Map<String, String> publicUploadInfo(MultipartFile imgcontent,Integer type ){
		logger.info("前台    用户或者模版文件上传文件  通用【uploadInfo】 start");
		Map<String, String> map = new HashMap<String, String>();		
		try {
				if(imgcontent.getSize() == 0){
					map.put("code","1");
					map.put("message","请选择需要上传的文件");
					return map;
				}
				logger.info("前台    用户或者模版文件上传文件  格式验证为："+ckFileType(imgcontent));
				if( !ckFileType(imgcontent)){
					map.put("code","1");
					map.put("message","请选择正确格式,doc,docx,ppt,pptx,xls,xlsx,pdf,bmp,jpg,jpeg,png,gif");
					return map;
				}
				
				UpLoadUtil u = new UpLoadUtil();			
				map = u.upLoadMap(imgcontent,  ConstantEntity.FILE_IMG, uploadPath);
				logger.info("上传文件【upcode】："+map.get("upcode"));
				if("0".equals(map.get("upcode"))){
					Fileinfos fileinfos = new Fileinfos();
					System.out.println("================================"+map.get("file_path"));
					System.out.println("----------------------------------"+map.get("file_name"));
					fileinfos.setFilePath(map.get("file_path"));
					fileinfos.setFileName(map.get("file_name"));
					fileinfos.setFileType(map.get("file_type"));
					fileinfos.setUploadTime(UtilTool.dateConLon());
					fileinfos.setType(type);
					Long fid = null;
					try {
						fileinfosService.saveFileinfosInfo(fileinfos);
						fid = fileinfos.getId();
						map.put("file_id", fid+"");
					} catch (Exception e) {
						logger.error("注册用户上传文件异常【saveFileinfosInfo】："+e);
						map.put("code","1");
						map.put("message","请选择需要上传的文件");
						return map;
					}
					System.out.println("【】【】【】【】【】fid:"+fid);
				}else{
					map.put("code","1");
					map.put("message","上传文件失败");
					return map;
				}
				
		} catch (Exception e) {
			map.put("code","1");
			map.put("message","请选择需要上传的文件");
			return map;
		}
		map.put("code","0");
		map.put("message","上传成功");
		logger.info("前台    用户或者模版文件上传文件  通用【uploadInfo】end");
		return map;
	}

	
	
	public Map<String, String> updateFileInfo(MultipartFile imgcontent,String fid){
		
		logger.info("【修改】【updateFileInfo】");
		Map<String, String> map = new HashMap<String, String>();		
		 Fileinfos  f= fileinfosService.queryFileinfos(Long.valueOf(fid));
		 if( null == f ){
			 map.put("code","1");
			 map.put("message","文件id不存在不能修改!");
			 return map;
		 }
		try {
				Integer type = 0;
				if(imgcontent.getSize() == 0){
					map.put("code","1");
					map.put("message","请选择需要上传的文件");
					return map;
				}
				UpLoadUtil u = new UpLoadUtil();
				String fileName1 = imgcontent.getOriginalFilename();
				String strFileType = fileName1.substring(fileName1.indexOf(".")+1,fileName1.length());
				if(strFileType.toLowerCase().equals("png") || 
						strFileType.toLowerCase().equals(  "jpg") ||
						strFileType.toLowerCase().equals( "jpeg" )|| 
						strFileType.toLowerCase().equals( "gif") || 
						strFileType.toLowerCase().equals("bmp"))
				{
					type = 0;
				}else{
					type = 1;
				}
				
				map = u.upLoadMap(imgcontent,  ConstantEntity.FILE_IMG, uploadPath);
				u.deleteUpLoad(f.getFilePath(),0,uploadDelPath);
				Fileinfos fileinfos = new Fileinfos();
				logger.info("================================"+map.get("file_path"));
				logger.info("----------------------------------"+map.get("file_name"));
				fileinfos.setFilePath(map.get("file_path"));
				fileinfos.setFileName(map.get("file_name"));
				fileinfos.setFileType(map.get("file_type"));
				fileinfos.setUploadTime(UtilTool.dateConLon());
				fileinfos.setEditTime(UtilTool.dateConLon());
				fileinfos.setType(type);
				fileinfosService.updateFileinfos(fileinfos);

				
			
			
		} catch (Exception e) {
			map.put("code","1");
			map.put("message","请选择需要修改的文件");
			return map;
		}
		map.put("code","0");
		map.put("message","修改成功");
		return map;
		
	}
	

	
	/**
	 * 获取文件信息
	 * @param fileIds
	 * @return
	 */
	@RequestMapping(value="/queryFileinfosInfo",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryFileinfosInfo(String fileIds){
		logger.info("输入：  "+ "fileIds="+fileIds);
		List<Fileinfos>  fileinfosLists = new ArrayList<Fileinfos>();
		String[] ids = fileIds.split(",");
		for (int i = 0; i < ids.length; i++){
			Fileinfos fileinfo = fileinfosService.queryFileinfos(Long.parseLong(ids[i]));
			fileinfosLists.add(fileinfo);
		}		
		logger.info("输出：  "+ JSON.toJSONString(fileinfosLists));
		return JSON.toJSONString(fileinfosLists);
	}
	
	
	private boolean ckFileType(MultipartFile imgcontent){	
		String fileType = imgcontent.getOriginalFilename();
		String nameType = fileType.substring(fileType.indexOf(".")+1,fileType.length());
		logger.info("ckFileType 上传后缀为：  "+ nameType);
		if( !"gif".equals( nameType.toLowerCase() ) && !"png".equals( nameType.toLowerCase() ) && 
			!"jpeg".equals( nameType.toLowerCase() ) && !"jpg".equals( nameType.toLowerCase() ) && 
			!"bmp".equals( nameType.toLowerCase() ) && !"pdf".equals( nameType.toLowerCase() ) && 
			!"xlsx".equals( nameType.toLowerCase() ) && !"xls".equals( nameType.toLowerCase() ) && 
			!"pptx".equals( nameType.toLowerCase() ) && !"ppt".equals( nameType.toLowerCase() ) && 
			!"docx".equals( nameType.toLowerCase() ) && !"".equals( nameType.toLowerCase() ) && 
			!"".equals( nameType.toLowerCase() ) && !"doc".equals( nameType.toLowerCase() ) ){
			return false;
		}
		return true;		
	}

}
