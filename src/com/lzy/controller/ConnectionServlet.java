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
	������ͨ������signature���������У�飨������У�鷽ʽ����
	��ȷ�ϴ˴�GET��������΢�ŷ���������ԭ������echostr�������ݣ��������Ч����Ϊ�����߳ɹ����������ʧ�ܡ�����/У���������£�
	1����token��timestamp��nonce�������������ֵ�������
	2�������������ַ���ƴ�ӳ�һ���ַ�������sha1����
	3�������߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢�� 
	 */
	
	// ���� 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "lzy";
		// �ֵ�����
		List<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);	
		// sha1����
		String str = StringUtils.getSha1Str(list.get(0) + list.get(1) + list.get(2));
		if (signature.equals(str)) {
			response.getWriter().print(echostr);
		}else {
			System.out.println("����ʧ��");
		}
	}
	
	 // ��Ӧ
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����post�������
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		// ��ȡ΢�Ŷ˵�����
		Map<String, String> xmlMap = WxDao.handleMap(request);
		// ������ݸ�΢�Ŷ˻ظ���Ӧ
		String responseStr = WxDao.getResponseStr(xmlMap);
		// ����΢�ŷ�����
		response.getWriter().print(responseStr);
		
		
	}

}
