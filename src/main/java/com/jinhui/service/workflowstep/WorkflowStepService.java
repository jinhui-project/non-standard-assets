package com.jinhui.service.workflowstep;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.model.Fileinfos;
import com.jinhui.model.WorkflowStep;

/**
 * <p>Title:WorkflowStepService</p>
 * <p>Description:机构备案流程步骤表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
public interface WorkflowStepService  {
	
	
	void addList(List<WorkflowStep> list)throws Exception;
	
	List<Fileinfos> queryFileinfosList(WorkflowStep workflowStep);
	
	
	void addFileinfos(WorkflowStep workflowStep,MultipartFile imgcontent,HttpServletRequest request) throws Exception ;
	
	boolean deleteInfo(WorkflowStep workflowStep,HttpServletRequest request) throws Exception ;
	
	/**
	 * 根据资产id查询备案流程
	 * @param assetId 资产id
	 * @return
	 */
    List<WorkflowStep> queryWorkflowSteps(String assetId);
   	
}