package com.jinhui.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jinhui.constant.ConstantEntity;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Users;
import com.jinhui.service.actionLog.ActionLogService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.service.user.UsersService;
import com.jinhui.service.workflow.WorkflowService;
import com.jinhui.util.RedisUtils;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;
/**
 * 注册Controller
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class RegisteredController {
	Logger logger = LoggerFactory.getLogger(RegisteredController.class);
	@Autowired
	private ActionLogService actionLogService;
	
	@Autowired
	private UsersService usersService;
	
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
	 * 查询用户是否存在
	 * @param userName
	 * @return
	 */
	@RequestMapping("/queryUserInfo")
	@ResponseBody
	public BaseObject queryUserInfo(String userName){
		Users u =new Users();
		BaseObject obj = new BaseObject();
		u = usersService.selectByName(userName);
		if( null == u ){
			obj.setMessage("用户可以注册"); 
			obj.setCode(0);
		}else{
			obj.setMessage("用户已存在"); 
			obj.setCode(1);
		}
		return obj;	
	}
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping("/registeredInfo")
	@ResponseBody
//	public BaseObject registeredInfo(Users record,List<MultipartFile> imgcontent,HttpServletRequest request){
	public BaseObject registeredInfo(HttpServletRequest request,Users record){	
		BaseObject obj = new BaseObject();
		Users u =new Users();
		u = usersService.selectByName(record.getMobile());
		if( null != u ){
			obj.setMessage("用户手机号码已注册"); 
			obj.setCode(1);
			return obj;
		}
		String phoneCode ="";
		try {
			phoneCode = (String) RedisUtils.getValue(record.getPhoneCode(),ConstantEntity.PHONE_CODE) ;
			if( null == phoneCode || "".equals(phoneCode)){
				obj.setCode(1);
				obj.setMessage("手机验证码失效!");
				return obj;	
			}
		} catch (Exception e) {
			obj.setCode(1);
			obj.setMessage("手机验证码失效!");
			return obj;
		}
		
		if(!record.getPhoneCode().equals(phoneCode)){
			obj.setCode(1);
			obj.setMessage("验证码不正确!");
			return obj;
		}
		
		String code = "";
		try {
			code = (String) RedisUtils.getValue(record.getCode().toUpperCase(),ConstantEntity.IMG_CODE);
		} catch (Exception e1) {
			logger.error("【用户注册 图形验证码 registeredInfo】RedisUtils异常："+e1);
			obj.setCode(1);
			obj.setMessage("图形验证码失效!");
			return obj;
		}
		if(!record.getCode().equalsIgnoreCase(code)){
			obj.setCode(1);
			obj.setMessage("图形验证码不正确!");
			return obj;
		}

		
		try {

//			record.setCardPath(upLoadUrl.upLoad(imgcontent, ImgOrFile.IMG_TYPE.code,uploadPath,request));//获取文件路径			
			record.setName(record.getUsername());
			record.setUsername(record.getMobile());
			usersService.registered(record);
			//记录操作日志
			actionLogService.insertActionLog(request, record);
			obj.setMessage("注册成功"); 
			obj.setCode(0);
			return obj;
		} catch (Exception e) {
			logger.error("【用户注册失败registeredInfo】异常为："+e);
			obj.setMessage("注册失败"); 
			obj.setCode(1);
			return obj;
		}
		
		
		
	}
	
	@Autowired
	private WorkflowService workflowService;
	/**
	 * 测试方法  不需要关注
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public ModelAndView test(Users record){
		logger.info("【】【】【】【】【】【】【】【】【】【测试日志】");
		ModelMap m = null;
		System.out.println("【uploadPath】Controller"+uploadPath);
		
		System.out.println("【uploadPath】service"+workflowService.upPath());

		
		System.out.println(uploadPath+uploadPath);
		return new ModelAndView("index",m);
//		return "ssss";

	}
	
	
	@Autowired
	private FileinfosService fileinfosService;
	/**
	 * 测试方法  不需要关注
	 * @return
	 */
	@RequestMapping("/uploadInfo")
	@ResponseBody
	public String uploadInfo(List<MultipartFile> imgcontent,HttpServletRequest request){
		logger.info("【】【】【】【】【】【】【】【】【】【uploadInfo】");
//		if(imgcontent.getSize()>1024){
//			return "文件过大不能上传";
//		}
		
		
		
		
		try {
			for(MultipartFile m: imgcontent){
				if(m.getSize() == 0){
					return "请选择需要上传的文件";
				}
				UpLoadUtil u = new UpLoadUtil();
				Map<String, String> map = new HashMap<String, String>();
				map = u.upLoadMap(m,  ConstantEntity.FILE_IMG, uploadPath);
				Fileinfos fileinfos = new Fileinfos();
				System.out.println("================================"+map.get("file_path"));
				System.out.println("----------------------------------"+map.get("file_name"));
				fileinfos.setFilePath(map.get("file_path"));
				fileinfos.setFileName(map.get("file_name"));
				fileinfos.setFileType(map.get("file_type"));
				fileinfos.setUploadTime(UtilTool.dateConLon());
				Long fid = null;
				try {
					fileinfosService.saveFileinfosInfo(fileinfos);
					fid = fileinfos.getId();
				} catch (Exception e) {
					logger.error("注册用户上传文件异常【saveFileinfosInfo】："+e);
					new Exception();
				}
				System.out.println("【】【】【】【】【】fid:"+fid);
				
			}
			
		} catch (Exception e) {

		}
		
		return "===========";


	}


	

	
	


    

	

}
