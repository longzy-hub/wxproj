package com.lzy.model.configuration;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import com.lzy.util.ImageUtil;
import com.lzy.util.StringUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import sun.awt.image.PixelConverter.Bgrx;

public class TextTemplate {

	public static String getContent(int type, String nickname) {
		switch (type) {
		case 1:
			return "感谢你的回复";
		case 2:
			return nickname + " 你好，欢迎你关注公众号";
		case 3: 
			return "Hello,"+ nickname + "分享下方海报至朋友圈或微信群\n\n【3】位好友扫码助力即可领取\n\n";
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

	// 客服模板选用-- 关注公众号回复欢迎词
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
	
	// 客服模板选用-- 关注公众号回复欢迎词
	public static String getCustomerImgTemplate( Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"    \"touser\":\""+ xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"image\",\r\n" + 
				"    \"image\":\r\n" + 
				"    {\r\n" + 
				"      \"media_id\":\""+ TextTemplate.getDownloadcode(xmlMap) +"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	
	// 客服模板选用-- 关注公众号回复扫码助力
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

	// 带参数关注公众号-- 回复图片消息
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

	// 不带参关注公众号-- 回复图片消息
	public static String getEventWithoutParamsTemplate(Map<String, String> xmlMap) {
		return "";
	}

	// 直接取出参数
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

	// 关注公众号回复欢迎词的http请求
	public static void getCustomerRequest(String nickname, Map<String, String> xmlMap) {
		// 获取客服的url
		String customerurl = TokenConfig.getCustomerUrl();
		String result = TextTemplate.getCustomerTemplate(nickname, xmlMap);
		HttpUtil.post(customerurl, result);
		// 回复扫码助力消息
		String result2 = TextTemplate.getCustomerTemplate2(nickname, xmlMap);
		HttpUtil.post(customerurl, result2);
		// 回复dm单
		String resultImg = TextTemplate.getCustomerImgTemplate(xmlMap);
		HttpUtil.post(customerurl, resultImg);
		
	}

	// 获取公众号的二维码并下载
	public static String getDownloadcode(Map<String, String> xmlMap) {
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
		
		String url = TokenConfig.getUserUrl();
		url = url.replace("OPENID", xmlMap.get("FromUserName"));
		// 从微信服务器获取对应的权限
		String userStr = HttpUtil.get(url);
		// 解析从微信服务器发过来的json请求
		JSONObject jsonObject2 = JSONUtil.parseObj(userStr);
//		System.out.println(jsonObject);
		// 取出nickname
		String headimgurl = jsonObject2.getStr("headimgurl");
		
		// 调用海报
		ImageUtil.addimg(fileUrl, headimgurl);
		
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
