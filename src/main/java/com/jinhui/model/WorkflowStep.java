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
	private  Long stepId;
	/**
	 * 文件类型：0img,1file
	 */
	private int fileType;
	//流程名称
	private String wfName;
	
	
	private String token;

	
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