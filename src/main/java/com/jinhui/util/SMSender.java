package com.jinhui.util;

import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTPack.MsgType;
import com.esms.common.entity.MTPack.SendType;
import com.jinhui.controller.products.ProductsController;

public class SMSender {
	private static Logger logger = LoggerFactory.getLogger(SMSender.class);
	private Account account = null; 
	private PostMsg pm = null;

	public SMSender(String accountName, String password, String gateWayIP,int geteWayPort) {
		account = new Account(accountName, password);
		pm = new PostMsg();
		pm.getCmHost().setHost(gateWayIP, geteWayPort);//设置网关的IP和port，用于发送信息
	}
	
	/**
	 * 发送单条短信
	 * @param mobileNo  手机号码
	 * @param content   短信内容
	 * @throws Exception
	 */
	public GsmsResponse doSendSms(String mobileNo,String content) throws Exception{
		MTPack pack = new MTPack();
		pack.setMsgType(MsgType.SMS); //SMS短信发送，MMS彩信发送
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
		msgs.add(new MessageData(mobileNo, content));
		pack.setMsgs(msgs);
		pack.setSendType(SendType.GROUP);
		GsmsResponse resp = pm.post(account,pack);
		logger.info(resp.toString());
		return resp;
	}
	
	public static void main(String[] args) {
		Account account = new Account("jfb@jinhui", "nLpBhs8F");
		PostMsg pm = new PostMsg();
		pm.getCmHost().setHost("211.147.239.62", 9080);//设置网关的IP和port，用于发送信息
		
		MTPack pack = new MTPack();
		pack.setMsgType(MsgType.SMS); //SMS短信发送，MMS彩信发送
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
		msgs.add(new MessageData("15820780934", "您正在修改登录密码"));
		pack.setMsgs(msgs);
		pack.setSendType(SendType.GROUP);
		GsmsResponse resp = null;
		try {
			resp = pm.post(account,pack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(resp.toString());
	}
}
