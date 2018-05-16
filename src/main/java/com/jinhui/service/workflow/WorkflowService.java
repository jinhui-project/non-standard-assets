package com.jinhui.service.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jinhui.model.Workflow;

/**
 * <p>Title:WorkflowService</p>
 * <p>Description:机构审核流程表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
public interface WorkflowService  {
	
	
	void saveWorkflow(Workflow workflow,MultipartFile imgcontent,HttpServletRequest request)throws Exception;
//	void saveWorkflow(Workflow workflow)throws Exception;
	
	List<Workflow> queryList(Long gid);
	
	 String upPath();
	
}