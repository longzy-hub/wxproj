package com.lzy.model.configuration;

import java.util.Map;

public class EventTemplate {

	public static String getEventTemplate(Map<String, String> xmlMap) {
		String event = xmlMap.get("Event");
		switch (event) {
		case "subscribe": 
			// ��ȡticketԪ��
			String ticket = xmlMap.get("Ticket");
			if (ticket != null && ticket.length() > 0) {
				// ɨ���˴������Ķ�ά�룬������˹�ע
				return TextTemplate.getEventWithParamsTemplate(xmlMap);				
			}else {
				// ֱ�ӹ�ע��û�д��ζ�ά��
				return TextTemplate.getEventWithoutParamsTemplate(xmlMap);
			}
		case "SCAN":
			// �ѹ�ע��
			return TextTemplate.getEventParamsTemplate(xmlMap);		
		}
		
		return null;		
	}
	

}
