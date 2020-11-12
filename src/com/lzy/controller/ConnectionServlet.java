package com.lzy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzy.model.dao.WxDao;
import com.lzy.util.StringUtils;

public class ConnectionServlet extends HttpServlet{
	
	/**
	开发者通过检验signature对请求进行校验（下面有校验方式）。
	若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
	1）将token、timestamp、nonce三个参数进行字典序排序
	2）将三个参数字符串拼接成一个字符串进行sha1加密
	3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信 
	 */
	
	// 接入 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "lzy";
		// 字典排序
		List<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);	
		// sha1加密
		String str = StringUtils.getSha1Str(list.get(0) + list.get(1) + list.get(2));
		if (signature.equals(str)) {
			response.getWriter().print(echostr);
		}else {
			System.out.println("接入失败");
		}
	}
	
	 // 响应
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置post编码解析
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		// 获取微信端的内容
		Map<String, String> xmlMap = WxDao.handleMap(request);
		try {
			int i = 1/0;
		} catch (Exception e) {
			response.getWriter().print("success");
		}finally {
			// 针对内容给微信端回复响应
			String responseStr = WxDao.getResponseStr(xmlMap);
			// 回送微信服务器
			response.getWriter().print(responseStr);
		}
			
		
		
	}

}
