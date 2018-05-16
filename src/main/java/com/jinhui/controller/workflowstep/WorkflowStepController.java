package com.jinhui.controller.workflowstep;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Products;
import com.jinhui.model.WorkflowStep;
import com.jinhui.service.workflowstep.WorkflowStepService;

/**
 * 
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class WorkflowStepController {
	
	Logger logger = LoggerFactory.getLogger(WorkflowStepController.class);
	
	@Autowired
	private WorkflowStepService workflowStepService;
	
	
	/**
	 *本案流程配置  根据文件 id 查询文件列表
	 * @param workflowStep
	 * @return
	 */
	@RequestMapping("/queryFileinfosList")
	@ResponseBody
	public String queryFileinfosList(WorkflowStep workflowStep){
		List<Fileinfos>  list2 = new ArrayList<Fileinfos>();
		list2 = workflowStepService.queryFileinfosList(workflowStep);
		return JSON.toJSONString(list2);
		
	}
	
	
	/**
	 *备案流程配置  添加
	 * @param workflowStep
	 * @return
	 */
	@RequestMapping("/addFileinfos")
	@ResponseBody
	public BaseObject addFileinfos(WorkflowStep workflowStep,MultipartFile imgcontent,HttpServletRequest request){
		BaseObject obj = new BaseObject();
		try {
			workflowStepService.addFileinfos(workflowStep, imgcontent, request);
			obj.setCode(0);
			obj.setMessage("备案流程配置  添加成功");
		} catch (Exception e) {
			logger.error("【备案流程配置  添加 addFileinfos】"+e);
			obj.setCode(1);
			obj.setMessage("备案流程配置  添加失败");
		}
		return obj;
		
	}
	
	/**
	 *备案流程配置  删除
	 * @param workflowStep
	 * @param stepId 主键Id
	 * @param fileId 文件Id
	 * @param fileType 文件类型：0img,1file
	 * @return obj
	 */
	@RequestMapping("/deleteFileinfos")
	@ResponseBody
	public BaseObject deleteFileinfos(WorkflowStep workflowStep,HttpServletRequest request){
		BaseObject obj = new BaseObject();
		try {
			boolean flag  = workflowStepService.deleteInfo(workflowStep, request);
			if(flag){
				obj.setCode(0);
				obj.setMessage("备案流程配置 ，删除成功");
			}else{
				obj.setCode(1);
				obj.setMessage("备案流程配置 ，删除失败");
			}
		} catch (Exception e) {
			logger.error("【备案流程配置  删除 deleteFileinfos】"+e);
			obj.setCode(1);
			obj.setMessage("备案流程配置 ，删除异常");
		}
		return obj;		
	}
	
	
	/**
	 * 根据资产id查询备案流程
	 * @param asssetId
	 * @return
	 */
	@RequestMapping(value="/queryWorkflowStepByAssetId",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryWorkflowSteps(@RequestParam("assetId") String assetId){
		logger.info("输入：  "+ "assetId="+assetId);
		List<WorkflowStep> stepsList = workflowStepService.queryWorkflowSteps(assetId);
		logger.info("输出：   "+JSON.toJSONString(stepsList));
		return JSON.toJSONString(stepsList,SerializerFeature.WriteMapNullValue);
	}

}
