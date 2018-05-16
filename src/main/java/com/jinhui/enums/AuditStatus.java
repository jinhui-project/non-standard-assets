package com.jinhui.enums;

/**
 * 审核状态
 * @author jinhui
 *
 */
public enum AuditStatus {
	
	NOT_ACTIVATION("未激活", 0), 
    ACTIVATION("激活", 1), 
    CANCELLATION("注销",2);




	public String status;

    public int code;

	private AuditStatus(String status, int code) {
		this.status = status;
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
