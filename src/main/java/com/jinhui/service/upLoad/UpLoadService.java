package com.jinhui.service.upLoad;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author jinhui
 *
 */
public interface UpLoadService {
	
	public String  upLoad(MultipartFile imgcontent,int fileType )throws Exception;

}
