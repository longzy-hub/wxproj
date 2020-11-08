package com.lzy.model.configuration;

import java.util.HashMap;
import java.util.Map;

import com.lzy.util.StringUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TextTemplate {

	public static String getContent(int type, String nickname) {
		switch (type) {
		case 1:
			return "��л��Ļظ�";
		case 2:
			return nickname + " ��ã���ӭ���ע���ں�";
		case 3: 
			return "Hello,"+ nickname + "�����·�����������Ȧ��΢��Ⱥ\n\n��3��λ����ɨ������������ȡ";
		}
		return null;
	}

	// �ظ��ı���Ϣ
	public static String getTextTemplate(Map<String, String> xmlMap) {
		String template = "<xml>\r\n" + 
				"  <ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName>\r\n"+
				"  <FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName>\r\n" + 
				"  <CreateTime>"+ StringUtils.getCreateTime() + "</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n"+ 
				"  <Content><![CDATA[" + getContent(1, null) + "]]></Content>\r\n" + 
				"</xml>";

		return template;
	}

	// �ͷ�ģ��ѡ��-- ��ע���ںŻظ���ӭ��
	public static String getCustomerTemplate(String nickname, Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+ getContent(2, nickname)+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	// �ͷ�ģ��ѡ��-- ��ע���ںŻظ�ɨ������
	public static String getCustomerTemplate2(String nickname, Map<String, String> xmlMap) {
			String result = "{\r\n" + 
					"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
					"    \"msgtype\":\"text\",\r\n" + 
					"    \"text\":\r\n" + 
					"    {\r\n" + 
					"         \"content\":\""+ getContent(3, nickname)+"\"\r\n" + 
					"    }\r\n" + 
					"}";
			return result;
		}

	// ��������ע���ں�-- �ظ�ͼƬ��Ϣ
	public static String getEventWithParamsTemplate(Map<String, String> xmlMap) {
		String template = "<xml>\r\n" + 
				"  <ToUserName><![CDATA["+ xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+ xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[image]]></MsgType>\r\n" + 
				"  <Image>\r\n" + 
				"    <MediaId><![CDATA["+ TextTemplate.getDownloadcode(xmlMap) +"]]></MediaId>\r\n" + 
				"  </Image>\r\n" + 
				"</xml>\r\n" + 
				"";

		return template;
	}

	// �����ι�ע���ں�-- �ظ�ͼƬ��Ϣ
	public static String getEventWithoutParamsTemplate(Map<String, String> xmlMap) {
		String template = "<xml>\r\n" + 
				"  <ToUserName><![CDATA["+ xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+ xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[image]]></MsgType>\r\n" + 
				"  <Image>\r\n" + 
				"    <MediaId><![CDATA["+ TextTemplate.getDownloadcode(xmlMap) +"]]></MediaId>\r\n" + 
				"  </Image>\r\n" + 
				"</xml>\r\n" + 
				"";

		return template;
	}

	// ֱ��ȡ������
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {
		String template = "<xml>\r\n" + 
				"  <ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName>\r\n"+
				"  <FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName>\r\n"+ 
				"  <CreateTime>"+ StringUtils.getCreateTime() + "</CreateTime>\r\n"+ 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n"+ 
				"  <Content><![CDATA[" + xmlMap.get("TICKET") + "]]></Content>\r\n"+
				"</xml>";

		return template;
	}

	// ��ע���ںŻظ���ӭ�ʵ�http����
	public static void getCustomerRequest(String nickname, Map<String, String> xmlMap) {
		// ��ȡ�ͷ���url
		String customerurl = TokenConfig.getCustomerUrl();
		String result = TextTemplate.getCustomerTemplate(nickname, xmlMap);
		HttpUtil.post(customerurl, result);
		
		String result2 = TextTemplate.getCustomerTemplate2(nickname, xmlMap);
		HttpUtil.post(customerurl, result2);
	}

	// ��ȡ���ںŵĶ�ά�벢����
	public static String getDownloadcode(Map<String, String> xmlMap) {
		String codeurl = TokenConfig.getQrcodeUrl();
		// ������
		String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"\r\n"
				+ ""+ xmlMap.get("FromUserName")+"\"}}}";
		String result = HttpUtil.post(codeurl, data);
		// ����΢�ŷ�����������json�ֶΣ�ȡ��ticket
		JSONObject jsonObject = JSONUtil.parseObj(result);
//		System.out.println(jsonObject);
		String ticket = jsonObject.getStr("ticket");
//		System.out.println(ticket);
		// ��ȡ��ά��ͼƬ
		String fileUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		// ���ض�ά��
		// ���ļ����غ󱣴�����Ŀ��
		HttpUtil.downloadFile(fileUrl, FileUtil.file("ticket.jpg"));
		// ������ʱ�ز�
		String shortTimeUrl = TokenConfig.getMatterUrl();
//		System.out.println(shortTimeUrl);
		// ��ͼƬ����map��
		HashMap<String, Object> paramMap = new HashMap<>();
		// �ļ��ϴ�ֻ�轫�����еļ�ָ����Ĭ��file����ֵ��Ϊ�ļ����󼴿ɣ�����ʹ������˵���ļ��ϴ�����ͨ���ύ��������
		paramMap.put("file", FileUtil.file("ticket.jpg"));
		String result2 = HttpUtil.post(shortTimeUrl, paramMap);
//		System.out.println(result2);
		// ����json
		JSONObject jsonObject2 = JSONUtil.parseObj(result2);
		String mediaId = jsonObject2.getStr("media_id");
//		System.out.println(mediaId);
		
		return mediaId;
	}
}
