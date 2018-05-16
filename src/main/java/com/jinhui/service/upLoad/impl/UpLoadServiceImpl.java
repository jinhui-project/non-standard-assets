package com.jinhui.service.upLoad.impl;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.enums.ImgOrFile;
import com.jinhui.service.upLoad.UpLoadService;


/**
 * 文件上传
 * @author jinhui
 *
 */
public class UpLoadServiceImpl implements UpLoadService {
	Logger logger = LoggerFactory.getLogger(UpLoadServiceImpl.class);
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private  String uploadPath;
	
	/**
	 * 文件路径
	 */
	@Value("${upload_picture_path}")
	private  String upload_picture_path;
	
	
	public String  upLoad(MultipartFile imgcontent,int fileType ){
		 //上传地址 
//		 String path =request.getSession().getServletContext() .getRealPath("\\upload\\");
		String strUrl = "";
		 String path ="F:\\"+upload_picture_path;
//		 System.out.println("【path】"+path);
		 if( fileType == ImgOrFile.IMG_TYPE.code ){
			 path = path + ImgOrFile.IMG_TYPE.status;
		 }else{
			 path = path + ImgOrFile.FILE_TYPE.status;
		 }
		 System.out.println("【path加了枚举类型】"+path);
		 //文件名 
		 String fileName1 = imgcontent.getOriginalFilename();
//		 System.out.println("【fileName1】"+fileName1);
		// 文件绝对地址
		 String nameFile = new Date().getTime()/1000 + fileName1;
		File targetFile1 = new File(path,nameFile );
		System.out.println("【targetFile1】"+targetFile1);
		if (!targetFile1.exists()) {
			targetFile1.mkdirs();
		}
		try {
			imgcontent.transferTo(targetFile1);
			strUrl = upload_picture_path + nameFile;
		} catch (Exception e) {
			logger.error("【UpLoadServiceImpl.upLoad】异常"+e);
		}
//		System.out.println("【strUrl】"+strUrl);
		return strUrl;

	}

}
