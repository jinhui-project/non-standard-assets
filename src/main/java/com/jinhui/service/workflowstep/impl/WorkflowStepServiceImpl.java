package com.jinhui.service.workflowstep.impl;

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
import com.jinhui.mapper.upload.FileinfosMapper;
import com.jinhui.mapper.workflowstep.WorkflowStepMapper;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.WorkflowStep;
import com.jinhui.service.workflowstep.WorkflowStepService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;

/**
 * <p>
 * Title:WorkflowStepServiceImpl
 * </p>
 * <p>
 * Description:机构备案流程步骤表服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 金汇金融有限公司
 * </p>
 * 
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
@Service
public class WorkflowStepServiceImpl implements WorkflowStepService {

	Logger logger = LoggerFactory.getLogger(WorkflowStepServiceImpl.class);
	@Autowired
	private WorkflowStepMapper workflowStepMapper;

	@Autowired
	private FileinfosMapper fileinfosMapper;
	
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private String uploadPath;
	

	/**
	 * 批量添加
	 */
	public void addList(List<WorkflowStep> list) throws Exception {
		try {
			workflowStepMapper.addList(list);
		} catch (Exception e) {
			logger.error("【WorkflowStepServiceImpl】" + e);
		}
	}

	/**
	 * 查询文件信息
	 */
	public List<Fileinfos> queryFileinfosList(WorkflowStep workflowStep) {
		WorkflowStep ws = workflowStepMapper.queryWorkflowStep(workflowStep
				.getStepId());
		List<Fileinfos> fileinfosList = new ArrayList<Fileinfos>();
		String str = ws.getFileId();
		if (null != str && !"".equals(str)) {
			String[] idStr = str.split(",");
			if (idStr.length > 0) {
				List<Long> idList = new ArrayList<Long>();
				for (String id : idStr) {
					idList.add(Long.parseLong(id));
				}
				fileinfosList = fileinfosMapper.queryList(idList);
			}
		}
		return fileinfosList;

	}

	/**
	 * 添加步骤 模版
	 */
	public void addFileinfos(WorkflowStep workflowStep,
			MultipartFile imgcontent, HttpServletRequest request)
			throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		UpLoadUtil upload = new UpLoadUtil();
		Fileinfos ff = new Fileinfos();
		WorkflowStep ws = new WorkflowStep();
		long configTime = UtilTool.dateConLon();
		map = upload.upLoadMap(imgcontent, ConstantEntity.FILE_IMG,uploadPath);
		ff.setFilePath(map.get("file_path"));
		ff.setFileName(map.get("file_name"));
		ff.setFileType(map.get("file_type"));
		ff.setUploadTime(configTime);
		ff.setEditTime(configTime);
		Long fileId = fileinfosMapper.saveFileinfosInfo(ff);

		ws = workflowStepMapper.queryWorkflowStep(workflowStep.getStepId());
		if (null != ws.getFileId() && !"".equals(ws.getFileId())) {
			workflowStep.setFileId(ws.getFileId() + "," + fileId + "");
		} else {
			workflowStep.setFileId(fileId + "");
		}

		workflowStep.setOnfigTime(configTime);
		workflowStepMapper.modVoNotNullWorkflowStep(ws);

	}

	/**
	 * 删除 流程步骤文件
	 */
	public boolean deleteInfo(WorkflowStep workflowStep,HttpServletRequest request) throws Exception {
		UpLoadUtil util = new UpLoadUtil();
		boolean flag = false;

		Fileinfos ff = fileinfosMapper.queryEntity(Long.valueOf(workflowStep.getFileId()));
		WorkflowStep ws = workflowStepMapper.queryWorkflowStep(workflowStep.getStepId());
		String[] str = ws.getFileId().split(",");
		List<String> list = Arrays.asList(str);
		try {
			flag = util.deleteUpLoad(ff.getFileName(),ConstantEntity.FILE_IMG,uploadPath);
		} catch (Exception e) {
			logger.error("【删除 流程步骤文件  util.deleteUpLoad】" + e);
		}

		if(flag) {
			StringBuffer sbf = new StringBuffer();
			List<String> arrayList=new ArrayList<String>(list);//转换为ArrayLsit调用相关的remove方法
			if(list.contains(ff.getId() + "")) {	
			     arrayList.remove(ff.getId()+"");
			}
			for(String temp : arrayList ){
				sbf.append(temp).append(",");
			}
			fileinfosMapper.delId(ff.getId());
			ws.setFileId(sbf.substring(0,sbf.length()-1));
			workflowStepMapper.modVoNotNullWorkflowStep(ws);
		}
		return flag;
	}

	/**
	 * 根据资产id查询备案流程
	 * @param assetId 资产id
	 * @return
	 */
	public List<WorkflowStep> queryWorkflowSteps(String assetId) {
		return workflowStepMapper.queryWorkflowSteps(assetId);
	}
}