package com.lzy.model.configuration;

import java.util.Map;

public class EventTemplate {

	public static String getEventTemplate(Map<String, String> xmlMap) {
		String event = xmlMap.get("Event");
		switch (event) {
		case "subscribe": 
			// 获取ticket元素
			String ticket = xmlMap.get("Ticket");
			if (ticket != null && ticket.length() > 0) {
				// 扫描了带参数的二维码，并点击了关注
				return TextTemplate.getEventWithParamsTemplate(xmlMap);				
			}else {
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
