package com.jinhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.constant.ConstantEntity;

public class UpLoadUtil {
	Logger logger = LoggerFactory.getLogger(UpLoadUtil.class);

	public String upLoad(MultipartFile imgcontent, int fileType,String uploadPath) throws Exception {
		// 上传地址
		String path = uploadPath;
		String strUrl = "";
		logger.info("【UpLoadUtil.上传地址 ====path:】" + path);
		// 文件名
		String fileName1 = imgcontent.getOriginalFilename();
		logger.info("【UpLoadUtil.文件名 ====fileName1】" + fileName1);
		// 文件绝对地址
		String nameFile = new Date().getTime() + fileName1;
		String strName = nameFile.substring(0, nameFile.indexOf("."));
		String strType = nameFile.substring(nameFile.indexOf("."),
				nameFile.length());

		File targetFile1 = new File(path + File.separator + UtilTool.dateStr()
				+ File.separator, UtilTool.md5Tool(strName) + strType);
		logger.info("【UpLoadUtil.文件绝对地址====targetFile1】" + targetFile1);
		if (!targetFile1.exists()) {
//			targetFile1.setReadable(true);
			targetFile1.mkdirs();
		}
		try {
			imgcontent.transferTo(targetFile1);
			String str = targetFile1.toString();
			String strPath = str.substring(
					str.indexOf(File.separator + ConstantEntity.UPLOAD_STR),
					str.length());
			logger.info("【UpLoadUtil.截取后的地址====str】" + strPath);
			strUrl = strPath;
		} catch (Exception e) {
			logger.error("【UpLoadUtil.upLoad文件上传】异常为：" + e);
		}
		return strUrl.replace("\\", "/");

	}

	/**
	 * 
	 * @param imgcontent
	 * @param fileType
	 * @param request
	 * @return
	 */
	public Map<String, String> upLoadMap(MultipartFile imgcontent,int fileType, String uploadPath) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 上传地址
		String path = uploadPath;
		String strUrl = "";
		logger.info("【UpLoadUtil.上传地址 ====path】" + path);

		// 文件名
		String fileName1 = imgcontent.getOriginalFilename();
		logger.info("【UpLoadUtil.文件名 ====fileName1】" + fileName1);
		// 文件绝对地址
		String nameFile = new Date().getTime() + fileName1;
		String strName = nameFile.substring(0, nameFile.indexOf("."));
		String strType = nameFile.substring(nameFile.indexOf("."),nameFile.length());
		String typeFile =imgcontent.getContentType();
		File targetFile1 = new File(path + File.separator + UtilTool.dateStr()
				+ File.separator, UtilTool.md5Tool(strName) + strType);
		logger.info("【UpLoadUtil.文件绝对地址====targetFile1】" + targetFile1);
		if (!targetFile1.exists()) {
//			targetFile1.setReadable(true);
			targetFile1.mkdirs();
		}
		try {
			imgcontent.transferTo(targetFile1);
			String str = targetFile1.toString();
			String strPath = str.substring(str.indexOf(File.separator + ConstantEntity.UPLOAD_STR),str.length());
			logger.info("【UpLoadUtil.截取后的地址====str】" + strPath);
			strUrl = strPath;
			map.put("file_path", strUrl.replace("\\", "/"));
			map.put("file_name", fileName1);
			map.put("file_type", typeFile);
			map.put("upcode", "0");
		} catch (Exception e) {
			logger.error("【UpLoadUtil.upLoad文件上传】异常为：" + e);
			map.put("upcode", "1");
		}
		return map;
	}

	public void downloadFile(String filePath, String fileName,
			String uploadDelPath, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

		File file = new File(uploadDelPath + filePath);
		String userAgent = request.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
		fileName = new String(bytes, "ISO-8859-1");
		// 设置输出的格式
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);

		InputStream inStream = null;
		try {
			inStream = new FileInputStream(file);
			IOUtils.copy(inStream, response.getOutputStream());// 使用commons-io组件进行文件流的处理
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream);
		}

	}


	public boolean deleteUpLoad(String fileName, int fileType, String uploadPath)
			throws Exception {
		boolean flag = false;
		// 上传地址
		String path = uploadPath;
		logger.info("【UpLoadUtil.删除上传地址 ====path】" + path);
		// 文件名 148228956375766477.png
		String fileName1 = fileName;
		logger.info("【UpLoadUtil.删除文件名 ====fileName1】" + fileName1);
		File targetFile1 = new File(path, fileName1);
		logger.info("【UpLoadUtil.删除文件绝对地址====targetFile1】" + targetFile1);
		if (targetFile1.exists()) {
			if (targetFile1.delete()) {
				flag = true;
			}
		}
		return flag;
	}

}
