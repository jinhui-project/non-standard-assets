package com.jinhui.controller.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Workflow;
import com.jinhui.service.workflow.WorkflowService;
import com.jinhui.service.workflowstep.WorkflowStepService;
import com.jinhui.util.UpLoadUtil;

/**
 * 机构审核流程
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class WorkflowController {
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private  String uploadPath;
	Logger logger = LoggerFactory.getLogger(WorkflowController.class);
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private WorkflowStepService workflowStepService;
	
	/**
	 * 机构审核流程 保存
	 * @param workflow
	 * @param imgcontent
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveWorkflowInfo")
	@ResponseBody
	public BaseObject saveWorkflowInfo(Workflow workflow,MultipartFile imgcontent,HttpServletRequest request){
		BaseObject bo = new BaseObject();
		try {
			workflowService.saveWorkflow(workflow, imgcontent, request);
			bo.setCode(0);
			bo.setMessage("机构审核流程 保存成功");
		} catch (Exception e) {
			logger.error("【机构审核流程 保存 saveWorkflowInfo】"+e);
			bo.setCode(1);
			bo.setMessage("机构审核流程 失败成功");
		}
		return bo;
		
	}
	
	/**
	 *流程节点配置    生成节点菜单
	 * @return
	 */
	@RequestMapping("/queryTreeInfo")
	@ResponseBody
	public String queryTreeInfo(Workflow workflow){
		List<Workflow> list = workflowService.queryList(workflow.getGid());
		return JSON.toJSONString(list);
	}
	
	

	
	@RequestMapping("/testList")
	@ResponseBody
	public String testList(HttpServletRequest request){
//		List<WorkflowStep>list = new ArrayList<WorkflowStep>();
		List<Fileinfos>  list2 = new ArrayList<Fileinfos>();
		UpLoadUtil u = new UpLoadUtil();
		try {
			u.deleteUpLoad("148228956375766477.png", 0,uploadPath);
		} catch (Exception e) {
			logger.error("【】【】【】【】【】【】"+e);
		}
//		 148228956375766477.png
//		WorkflowStep ws = new WorkflowStep();
//		ws.setFileId("4,5,6");
//		list2 = workflowStepService.queryFileinfosList(ws);
		return JSON.toJSONString(list2);
		
	}
	

}
