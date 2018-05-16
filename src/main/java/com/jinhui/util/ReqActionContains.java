package com.jinhui.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 需要记录操作日志的请求
 * 
 * @time 2017-4-5 上午10:10:44
 * @author wsc
 * @desc
 * 
 */
public class ReqActionContains {
	private static Map<String, String> ACTION_MAP = new HashMap<String, String>();
	private static Map<String, String> LOGIN_REGIST_MAP = new HashMap<String, String>();

	public static Map actionMaps() {
		ACTION_MAP.put("addPeAbsAsset", "创建资产_私募ABS");
		ACTION_MAP.put("addStateInvestmentAsset", "创建资产_城投");
		ACTION_MAP.put("addIpoAsset", "创建资产_上市公司");
		ACTION_MAP.put("addSupplyChainAsset", "创建资产_供应链");
		ACTION_MAP.put("addUsufructTransferAsset", "创建资产_收益权转让");
		ACTION_MAP.put("addIndustryCommunityAsset", "创建资产_其他工商类");
		ACTION_MAP.put("putOnRecord", "更新备案进度");
		ACTION_MAP.put("addOrgPreferCollect", "提交偏好信息");
		ACTION_MAP.put("collect", "收藏");
		ACTION_MAP.put("cancelCollect", "取消收藏");
		ACTION_MAP.put("addSaleFeedbacks", "意向反馈");
		
		return ACTION_MAP;
	}
	
	public static Map loginAndRegistMap(){
		LOGIN_REGIST_MAP.put("registeredInfo", "注册");
		LOGIN_REGIST_MAP.put("login", "登录_前台");
		return LOGIN_REGIST_MAP;
	}

}
