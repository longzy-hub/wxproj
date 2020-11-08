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

	// 处理xml信息
	public static Map<String, String> handleMap(HttpServletRequest request) {
		try {
			InputStream in = request.getInputStream();
			// 准备一个map
			Map<String, String> xmlMap = new HashMap<String, String>();
			// xml解析器--->dom4j
			SAXReader reader = new SAXReader();
			// 获取到整个xml内容
			Document document = reader.read(in);
			// 获取根节点
			Element root = document.getRootElement();
			// 获取所有子节点
			List<Element> elements = root.elements();
			// 将节点存入map中
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

	// 回送微信端字符串
	public static String getResponseStr(Map<String, String> xmlMap) {
		// 取出MsgType
		String msgType = xmlMap.get("MsgType");
		String resultXml = "";
		switch (msgType) {
		// 处理文本
		case "text":
			resultXml = handleTextMessage(xmlMap);
			break;
		// 处理事件
		case "event":
			resultXml = handleEventMessage(xmlMap);
			break;
		}

		return resultXml;
	}
	// 处理文本
	private static String handleTextMessage(Map<String, String> xmlMap) {
		return TextTemplate.getTextTemplate(xmlMap);
	}

	// 处理事件
	private static String handleEventMessage(Map<String, String> xmlMap) {
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
		String headimgurl = jsonObject.getStr("headimgurl");
//		System.out.println(headimgurl);	
		
		// 关注后获取客服消息-->输出关注欢迎词
		TextTemplate.getCustomerRequest(nickname, xmlMap);
		
		
		return EventTemplate.getEventTemplate(xmlMap);
	}

}
