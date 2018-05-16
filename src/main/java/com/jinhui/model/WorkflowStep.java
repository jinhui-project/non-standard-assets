package com.jinhui.model;

import java.io.Serializable;

/**
 * <p>Title:WorkflowStep</p>
 * <p>Description:机构备案流程步骤表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
public class WorkflowStep  implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1973596654962688L;

	/** 主键 */
	private  Long stepId;	/** workflow表id */	private  Long wfId;	/** 步骤名 */	private  String stepName;	/** 步骤顺序 */	private  Integer stepOrders;	/** 步骤附件文件id */	private  String fileId;	/** 配置人id */	private  Integer configId;	/** 配置时间 */	private  Long onfigTime;
	/**
	 * 文件类型：0img,1file
	 */
	private int fileType;
	//流程名称
	private String wfName;
	
	
	private String token;

		public  Long getWfId() {	    return this.wfId;	}	public void setWfId( Long wfId) {	    this.wfId=wfId;	}	public  Long getStepId() {	    return this.stepId;	}	public void setStepId( Long stepId) {	    this.stepId=stepId;	}	public  String getStepName() {	    return this.stepName;	}	public void setStepName( String stepName) {	    this.stepName=stepName;	}	public  Integer getStepOrders() {	    return this.stepOrders;	}	public void setStepOrders( Integer stepOrders) {	    this.stepOrders=stepOrders;	}	public  String getFileId() {	    return this.fileId;	}	public void setFileId( String fileId) {	    this.fileId=fileId;	}	public  Integer getConfigId() {	    return this.configId;	}	public void setConfigId( Integer configId) {	    this.configId=configId;	}	public  Long getOnfigTime() {	    return this.onfigTime;	}	public void setOnfigTime( Long onfigTime) {	    this.onfigTime=onfigTime;	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getWfName() {
		return wfName;
	}
	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}