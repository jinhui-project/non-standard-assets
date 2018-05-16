package com.jinhui.constant;

public class ConstantEntity {
	
	/**
	 * 图形验证码  常量
	 */
	public static String IMG_CODE = "code_";
	
	/**
	 * 手机验证码  常量
	 */
	public static String PHONE_CODE = "phone_code_";
	
	/**
	 * 手机  用户密码修改验证码  常量
	 */
	public static String PWD_CODE = "pwdCode_";
	
	/**
	 * 图形验证码  常量
	 */
	public static String USER_TICKET = "user_ticket_";
	
	/**
	 * 用户登录次数
	 */
	public static String LOGIN_NUM = "login_num_";
	
	/**
	 * 图片
	 */
	public static Integer FILE_IMG = 0;
	
	/**
	 * 文件
	 */
	public static Integer FILE_FILE = 1;
	
	/**
	 * 上传文件要截取的字符串
	 */
	public static String UPLOAD_STR = "fbasset_file";
	
	/**
	 * 登录redis Token有效期
	 */
	public static int REDISUTILS_EXPIRE = 30;
	
	/**
	 *图形 验证码redis 有效期
	 */
	public static int REDISUTILS_CODE_EXPIRE = 3;
	
	
	/**
	 *手机   用户密码修改验证码redis 有效期
	 */
	public static int PWD_CODE_EXPIRE = 3;

}
