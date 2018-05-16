package com.jinhui.enums;

/**
 * 文件上传枚举
 * @author jinhui
 *
 */
public enum ImgOrFile {
	
    IMG_TYPE("imgType", 0), 
    FILE_TYPE("fileType",1);




	public String status;

    public int code;
    
    private ImgOrFile(String status, int code) {
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
