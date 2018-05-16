package com.jinhui.service.workflow.impl;

import java.util.ArrayList;
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
import com.jinhui.enums.ImgOrFile;
import com.jinhui.mapper.workflow.WorkflowMapper;
import com.jinhui.mapper.workflowstep.WorkflowStepMapper;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Workflow;
import com.jinhui.model.WorkflowStep;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.service.workflow.WorkflowService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;

/**
 * <p>
 * Title:WorkflowServiceImpl
 * </p>
 * <p>
 * Description:机构审核流程表服务实现类
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
public class WorkflowServiceImpl implements WorkflowService {
	Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);
	@Autowired
	private WorkflowMapper workflowMapper;

	@Autowired
	private FileinfosService fileinfosService;

	@Autowired
	private WorkflowStepMapper workflowStepMapper;
	
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private  String uploadPath;
	
	public String upPath(){
		 return uploadPath;
	 }

	/**
	 * 流程节点新增
	 */
	public void saveWorkflow(Workflow workflow, MultipartFile imgcontent,HttpServletRequest request) throws Exception {
		UpLoadUtil util = new UpLoadUtil();
		Fileinfos ff = new Fileinfos();
		List<WorkflowStep> list = new ArrayList<WorkflowStep>();
		Map<String, String> map = new HashMap<String, String>();
		
		long configTime = UtilTool.dateConLon();
		workflow.setConfigTime(configTime);
		Long wf_id = workflowMapper.saveWorkflow(workflow);
		map = util.upLoadMap(imgcontent, ConstantEntity.FILE_IMG,uploadPath);
		ff.setFilePath(map.get("file_path"));
		ff.setFileName(map.get("file_name"));
		ff.setFileType(map.get("file_type"));
		ff.setUploadTime(configTime);
		ff.setEditTime(configTime);

		if (ImgOrFile.IMG_TYPE.code == workflow.getFileType()) {
			ff.setFileType("img");
		} else {
			ff.setFileType("file");
		}

		Long fileId = fileinfosService.saveFileinfosInfo(ff);

		for (WorkflowStep w : workflow.getWorkflowStepList()) {
			w.setWfId(wf_id);
			w.setFileId(fileId + "");
			w.setOnfigTime(configTime);
			list.add(w);
		}

		workflowStepMapper.addList(list);

	}

	/**
	 * 流程节点配置 查询 菜单节点
	 */
	public List<Workflow> queryList(Long gid) {
		List<Workflow> list = workflowMapper.queryList(gid);
		List<WorkflowStep> listWorkflowStep = new ArrayList<WorkflowStep>();
		for (int i = 0; i < list.size(); i++) {
			listWorkflowStep = workflowStepMapper.queryWorkflowStepList(list
					.get(i).getWfId());
			list.get(i).setWorkflowStepList(listWorkflowStep);
		}
		return list;
	}

}