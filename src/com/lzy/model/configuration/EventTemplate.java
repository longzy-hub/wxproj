package com.lzy.model.configuration;

import java.util.Map;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.User;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class EventTemplate {

	public static String getEventTemplate(Map<String, String> xmlMap) {
		// 获取创建菜单的URL
		String clickUrl = TokenConfig.getClickUrl();
		// 获取菜单模板，通过post方式请求
		HttpUtil.post(clickUrl, TextTemplate.getClickTemplate(xmlMap));
		
		String event = xmlMap.get("Event");
		switch (event) {
		case "subscribe":
			User user = createUser(xmlMap);
			UserDao<User> ud = (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
			// 如果数据库中已经存在用户，就不添加到数据库
//			if (ud.queryUser(user.getOpenid()).equals(null)) {
//				ud.addUser(user);					
//			}
		
			// 获取ticket元素
			String ticket = xmlMap.get("Ticket");
			if (ticket != null && ticket.length() > 0) {

				// 关注后获取客服消息
				TextTemplate.getCustomerRequest(user, xmlMap);
				// 获取客服的url
				String customerurl = TokenConfig.getCustomerUrl();
				// 扫描带参二维码回复消息
				String result3 = TextTemplate.getCustomerMesTemplate(user, xmlMap);
				HttpUtil.post(customerurl, result3);
				// 扫描了带参数的二维码，并点击了关注
				return TextTemplate.getEventWithParamsTemplate(xmlMap);
			} else {

				// 关注后获取客服消息
				TextTemplate.getCustomerRequest(user, xmlMap);
				// 直接关注，没有带参二维码
				return TextTemplate.getEventWithoutParamsTemplate(xmlMap);
			}
		case "SCAN":
			// 已关注，
			return TextTemplate.getEventParamsTemplate(xmlMap);
		}

		return null;
	}

	// 获取用户的信息，存入User中
	private static User createUser(Map<String, String> xmlMap) {
		// 获取用户url
		String url = TokenConfig.getUserUrl();
		url = url.replace("OPENID", xmlMap.get("FromUserName"));
		// 从微信服务器获取对应的权限
		String userStr = HttpUtil.get(url);
		// 解析从微信服务器发过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(userStr);
//		System.out.println(jsonObject);
		String openid = jsonObject.getStr("openid");
		// 取出nickname
		String nickname = jsonObject.getStr("nickname");
		Integer sex = jsonObject.getInt("sex");
		String headimgurl = jsonObject.getStr("headimgurl");
		// 将数据存入User中
		User user = new User();
		user.setOpenid(openid);
		user.setNickname(nickname);
		user.setSex(sex);
		user.setHeadimgurl(headimgurl);

		return user;
	}

}
