package com.lzy.model.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lzy.model.configuration.EventTemplate;
import com.lzy.model.configuration.TextTemplate;
import com.lzy.model.configuration.TokenConfig;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class WxDao {

	// ����xml��Ϣ
	public static Map<String, String> handleMap(HttpServletRequest request) {
		try {
			InputStream in = request.getInputStream();
			// ׼��һ��map
			Map<String, String> xmlMap = new HashMap<String, String>();
			// xml������--->dom4j
			SAXReader reader = new SAXReader();
			// ��ȡ������xml����
			Document document = reader.read(in);
			// ��ȡ���ڵ�
			Element root = document.getRootElement();
			// ��ȡ�����ӽڵ�
			List<Element> elements = root.elements();
			// ���ڵ����map��
			for (Element e : elements) {
				String key = e.getName();
				String value = e.getStringValue();
				xmlMap.put(key, value);
//				System.out.println(key + "..." + value);					
			}
			return xmlMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ����΢�Ŷ��ַ���
	public static String getResponseStr(Map<String, String> xmlMap) {
		// ȡ��MsgType
		String msgType = xmlMap.get("MsgType");
		String resultXml = "";
		switch (msgType) {
		// �����ı�
		case "text":
			resultXml = handleTextMessage(xmlMap);
			break;
		// �����¼�
		case "event":
			resultXml = handleEventMessage(xmlMap);
			break;
		}

		return resultXml;
	}
	// �����ı�
	private static String handleTextMessage(Map<String, String> xmlMap) {
		return TextTemplate.getTextTemplate(xmlMap);
	}

	// �����¼�
	private static String handleEventMessage(Map<String, String> xmlMap) {
		// ��ȡ�û�url
		String url = TokenConfig.getUserUrl();
		url = url.replace("OPENID", xmlMap.get("FromUserName"));
		// ��΢�ŷ�������ȡ��Ӧ��Ȩ��
		String userStr = HttpUtil.get(url);
		// ������΢�ŷ�������������json����
		JSONObject jsonObject = JSONUtil.parseObj(userStr);
//		System.out.println(jsonObject);
		// ȡ��nickname
		String nickname = jsonObject.getStr("nickname");
		String headimgurl = jsonObject.getStr("headimgurl");
//		System.out.println(headimgurl);	
		
		// ��ע���ȡ�ͷ���Ϣ-->�����ע��ӭ��
		TextTemplate.getCustomerRequest(nickname, xmlMap);
		
		
		return EventTemplate.getEventTemplate(xmlMap);
	}

}
