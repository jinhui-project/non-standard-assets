package com.jinhui.util;

/**
 * 增 删 改操作返回信息美化
 * @author jinhui
 *
 */
public class FormatReturn {

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public static FormatReturn format(Integer flag){
		FormatReturn fr = new FormatReturn();
		if(flag > 0){
			fr.setErrorCode("0");  //操作成功
		}else if(flag == 0){
			fr.setErrorCode("2");  //不存在该条记录
		}else{
			fr.setErrorCode("1");  //操作失败
		}
		return fr;
	}
}
