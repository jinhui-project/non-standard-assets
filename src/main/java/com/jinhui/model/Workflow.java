package com.jinhui.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:Workflow</p>
 * <p>Description:机构审核流程表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
public class Workflow implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1973367569664000L;

	private Long id;	/** 机构id */	private  Long gid;	/** 流程id */	private  Long wfId;	/** 流程名称 */	private  String wfName;	/** 配置人id */	private  Long configId;	/** 配置时间 */	private  Long configTime;
	/**
	 * 上传类型：0图片，1文件
	 */
	private int fileType;
	
	private String token;
	
	private List<WorkflowStep> workflowStepList;
		public  Long getId() {	    return this.id;	}	public void setId( Long id) {	    this.id=id;	}	public  Long getGid() {	    return this.gid;	}	public void setGid( Long gid) {	    this.gid=gid;	}	public  Long getWfId() {	    return this.wfId;	}	public void setWfId( Long wfId) {	    this.wfId=wfId;	}	public  String getWfName() {	    return this.wfName;	}	public void setWfName( String wfName) {	    this.wfName=wfName;	}	public  Long getConfigId() {	    return this.configId;	}	public void setConfigId( Long configId) {	    this.configId=configId;	}	public  Long getConfigTime() {	    return this.configTime;	}	public void setConfigTime( Long configTime) {	    this.configTime=configTime;	}
	public List<WorkflowStep> getWorkflowStepList() {
		return workflowStepList;
	}
	public void setWorkflowStepList(List<WorkflowStep> workflowStepList) {
		this.workflowStepList = workflowStepList;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}