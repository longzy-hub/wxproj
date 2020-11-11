package com.lzy.model.configuration;

import java.util.Map;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class EventTemplate {

	public static String getEventTemplate(Map<String, String> xmlMap) {
		// 获取客服的url
		String customerurl = TokenConfig.getCustomerUrl();
		// 获取用户url
		String url = TokenConfig.getUserUrl();
		url = url.replace("OPENID", xmlMap.get("FromUserName"));
		// 从微信服务器获取对应的权限
		String userStr = HttpUtil.get(url);
		// 解析从微信服务器发过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(userStr);
//		System.out.println(jsonObject);
		// 取出nickname
		String nickname = jsonObject.getStr("nickname");
//		String headimgurl = jsonObject.getStr("headimgurl");
//		System.out.println(headimgurl);	
		String event = xmlMap.get("Event");
		switch (event) {
		case "subscribe": 
			// 获取ticket元素
			String ticket = xmlMap.get("Ticket");
			if (ticket != null && ticket.length() > 0) {
				// 关注后获取客服消息
				TextTemplate.getCustomerRequest(nickname, xmlMap);
				
				// 扫描带参二维码回复消息
				String result3 = TextTemplate.getCustomerMesTemplate(xmlMap);
				HttpUtil.post(customerurl, result3);
				// 扫描了带参数的二维码，并点击了关注
				return TextTemplate.getEventWithParamsTemplate(xmlMap);				
			}else {	
				
				// 关注后获取客服消息
				TextTemplate.getCustomerRequest(nickname, xmlMap);
				// 直接关注，没有带参二维码				
				return TextTemplate.getEventWithoutParamsTemplate(xmlMap);
			}
		case "SCAN":
			// 已关注，
			return TextTemplate.getEventParamsTemplate(xmlMap);		
		}
		
		return null;		
	}
	

}
