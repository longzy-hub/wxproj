package com.lzy.model.configuration;

import java.util.HashMap;
import java.util.Map;

import com.lzy.model.pojo.User;
import com.lzy.util.ImageUtil;
import com.lzy.util.StringUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TextTemplate {

	public static String getContent(int type, String nickname) {
		switch (type) {
		case 1:
			return "感谢你的回复";
		case 2:
			return nickname + " 你好，欢迎你关注公众号";
		case 3: 
			return "Hello,"+ nickname + "分享下方海报至朋友圈或微信群\n\n【3】位好友扫码助力即可领取\n\n";
		case 4:
			return "你已经是我们的老朋友了，无法为好友助力";
		case 5:
			return nickname + ",已为你助力";
		}
		return null;
	}
 
	// 回复文本信息
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

	// 带参数关注公众号
	public static String getEventWithParamsTemplate(Map<String, String> xmlMap) {
		return null;
		}

	// 不带参关注公众号
	public static String getEventWithoutParamsTemplate(Map<String, String> xmlMap) {
			return null;
		}

	// 直接取出参数
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {
			String template = "<xml>\r\n" + 
					"  <ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName>\r\n"+
					"  <FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName>\r\n"+ 
					"  <CreateTime>"+ StringUtils.getCreateTime() + "</CreateTime>\r\n"+ 
					"  <MsgType><![CDATA[text]]></MsgType>\r\n"+ 
					"  <Content><![CDATA["+ getContent(4, null)+"]]></Content>\r\n"+
					"</xml>";

			return template;
		}

	// 客服模板选用-- 关注公众号回复欢迎词
	public static String getCustomerTemplate(User user, Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+ getContent(2, user.getNickname())+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}

	// 客服模板选用-- 关注公众号回复扫码助力
	public static String getCustomerTemplate2(User user, Map<String, String> xmlMap) {
			String result = "{\r\n" + 
					"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
					"    \"msgtype\":\"text\",\r\n" + 
					"    \"text\":\r\n" + 
					"    {\r\n" + 
					"         \"content\":\""+ getContent(3, user.getNickname())+"\"\r\n" + 
					"    }\r\n" + 
					"}";
			return result;
		}
	
	// 客服模板选用-- 关注公众号回复dm单
	public static String getCustomerImgTemplate(User user, Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"image\",\r\n" + 
				"    \"image\":\r\n" + 
				"    {\r\n" + 
				"      \"media_id\":\""+ TextTemplate.getDownloadcode(user,xmlMap) +"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	// 扫描带参数的二维码后，回复消息
	public static String getCustomerMesTemplate(User user,Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"    \"touser\":\""+xmlMap.get("EventKey").replace("qrscene_", "").trim()+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+ getContent(5, user.getNickname()) +"\"\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"";
//		System.out.println(xmlMap.get("EventKey").replace("qrscene_", "").trim());
		return result;		
	}

	
	// 关注公众号回复欢迎词的http请求
	public static void getCustomerRequest(User user, Map<String, String> xmlMap) {
		// 获取客服的url
		String customerurl = TokenConfig.getCustomerUrl();
		String result = TextTemplate.getCustomerTemplate(user, xmlMap);
		HttpUtil.post(customerurl, result);
		// 回复扫码助力消息
		String result2 = TextTemplate.getCustomerTemplate2(user, xmlMap);
		HttpUtil.post(customerurl, result2);
		// 回复dm单
		String resultImg = TextTemplate.getCustomerImgTemplate(user,xmlMap);
		HttpUtil.post(customerurl, resultImg);
		
	}

	// 获取公众号的二维码并下载
	public static String getDownloadcode(User user, Map<String, String> xmlMap) {
		String codeurl = TokenConfig.getQrcodeUrl();
		// 带参数
		String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"\r\n"
				+ ""+ xmlMap.get("FromUserName") +"\"}}}";
		String result = HttpUtil.post(codeurl, data);
		// 解析微信服务器发过来json字段，取出ticket
		JSONObject jsonObject = JSONUtil.parseObj(result);
//		System.out.println(jsonObject);
		String ticket = jsonObject.getStr("ticket");
//		System.out.println(ticket);
		// 获取二维码图片
		String fileUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
//		System.out.println(fileUrl);
		
		
		// 调用海报
		ImageUtil.addimg(fileUrl, user);
		
		// 下载二维码
		// 将文件下载后保存在项目服务器下
//		HttpUtil.downloadFile(fileUrl, FileUtil.file("ticket.jpg"));
		// 新增临时素材
		String shortTimeUrl = TokenConfig.getMatterUrl();
//		System.out.println(shortTimeUrl);
		// 将图片存入map中
		HashMap<String, Object> paramMap = new HashMap<>();
		// 文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
		paramMap.put("file", FileUtil.file("poster.jpg"));
		String result2 = HttpUtil.post(shortTimeUrl, paramMap);
//		System.out.println(result2);
		// 解析json
		JSONObject jsonObject3 = JSONUtil.parseObj(result2);
		String mediaId = jsonObject3.getStr("media_id");
//		System.out.println(mediaId);
		
		return mediaId;
	}
	
}
