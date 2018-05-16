package com.jinhui.mapper.workflow;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Workflow;

/**
 * 
 * @author jinhui
 *
 */
public interface WorkflowMapper {
	
	Long saveWorkflow(@Param("record")Workflow workflow);
	
	
	List<Workflow> queryList(@Param("gid")Long gid);

}
