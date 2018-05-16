package com.jinhui.mapper.workflowstep;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.WorkflowStep;

public interface WorkflowStepMapper {

	
	
	Long saveWorkflowStepInfo(@Param("record")WorkflowStep workflowStep);
	
	void addList(@Param("list")List<WorkflowStep> list);
	
	
	List<WorkflowStep>queryWorkflowStepList(@Param("wfId") Long wfId);
	
	WorkflowStep queryWorkflowStep(@Param("stepId") Long stepId);
	
	void modVoNotNullWorkflowStep(@Param("record")WorkflowStep workflowStep);
	
	
	/**
	 * 根据资产id查询备案流程
	 * @param assetId 资产id
	 * @return
	 */
    List<WorkflowStep> queryWorkflowSteps(@Param("assetId") String assetId);
}
