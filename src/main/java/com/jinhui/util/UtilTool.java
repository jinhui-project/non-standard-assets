package com.jinhui.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTool {



	/**
	 * 判断用户密码是否正确
	 * 
	 * @param newpasswd
	 *            用户输入的密码
	 * @param oldpasswd
	 *            数据库中存储的密码－－用户密码的摘要
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean checkpassword(String newpasswd, String oldpasswd){
		if (md5Tool(newpasswd).equals(oldpasswd)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 当前时间转换long类型
	 * 
	 * @return
	 */
	public static long dateConLon() {
		Date date = new Date();
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = fo.format(date);
		try {
			date = fo.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime() / 1000;
	}

	/**
	 * 最终调用的Md5
	 * 
	 * @param str
	 * @return
	 */
	public static String md5Tool(String str) {
		if(null!= str && !"".equals(str)){
			String x = md5(str) + "Non-standard*assets#www.jinhui365.com";
			return md5(x);
		}
		return null;
	}

	/**
	 * MD5加密
	 * 
	 * @param txt
	 * @return
	 */
	public static String md5(String txt) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(txt.getBytes("UTF8")); // 问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
			StringBuffer buf = new StringBuffer();
			for (byte b : md.digest()) {
				buf.append(String.format("%02x", b & 0xff));
			}
			String temp = buf.toString();
			return temp;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取当前年月日格式为2017/01/01
	 * @return
	 */
	public static String dateStr() {
		Date date = new Date();
		SimpleDateFormat fo = new SimpleDateFormat("yyyy/MM/dd");
		String s = fo.format(date);
		return s;
	}
	
	
	 /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long lt){
        String res="";
        if( null != lt && lt>0){
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date = new Date(lt*1000);
          res = simpleDateFormat.format(date);
        }
        
        return res;
    }
	

	public static void main(String[] args) {
//		System.out.println(stampToDate(1483947622));

//		System.out.println(dateStr());
		String m = "123456";
		
		System.out.println( md5Tool(m) );
//	
//		System.out.println(	checkpassword(m,"dc43cb86831056503dc005ab0be10766"));


		// System.out.println(date.getTime()/1000);
		
//		String a ="148280704857256789.png";
//		String strName=a.substring(0,a.indexOf("."));
//		String strType=a.substring(a.indexOf("."),a.length());
//		System.out.println(strName);
//		System.out.println(strType);
	}

}
