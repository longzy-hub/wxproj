package com.lzy.model.configuration;


import com.lzy.model.bean.AccessToken;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TokenConfig {

	// 获取Access Token 接口调用地址
	private static String accessTokenUrl = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String APPID = "wx05c3e4075b1d606e";
	private static String APPSECRET = "1e33fc38ee29fa8fdfd2cf0f29fc2886";
	
	// 获取客服的url
	private static String customerurl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	// 获取用户的基本信息的url
	private static String userurl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// 获取用户二维码的url
	private static String qrcodeurl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	// 新增临时素材
	private static String matterurl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE ";
	//获取自定义菜单url
	private static String clickurl = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private static AccessToken at;

	// 初始化我的token
	private static void initToken() {
		// 替换appid 和 appsecret
		String url = accessTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		// 从微信服务器获取对应权限(接口调用请求)
		String tokenStr = HttpUtil.get(url);
		// 解析微信服务器发过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(tokenStr);
		// 取出token和expire
		String token = jsonObject.getStr("access_token");
		String expireIn = jsonObject.getStr("expires_in");
		// 封装进token值
		at = new AccessToken(token, expireIn);
	}

	// 获取token
	public static String getAccessToken() {
		if (at == null || at.expiredStatus()) {
			initToken();
		}
		return at.getAccesstoken();
	}

	// 获取客服的调用接口
	public static String getCustomerUrl() {
		customerurl = customerurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		return customerurl;
	}
	
	// 获取用户基本信息调用接口
	public static String getUserUrl() {
		// 调用用户的基本信息接口
		userurl = userurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		return userurl;
	}
	
	// 获取公众号二维码的调用接口
	public static String getQrcodeUrl() {
		qrcodeurl = qrcodeurl.replace("TOKEN", TokenConfig.getAccessToken());
		return qrcodeurl;
	}
	
	// 新增临时素材
	public static String getMatterUrl() {
		matterurl = matterurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken()).replace("TYPE", "image");
		return matterurl;
	}
	
	//获取自定义菜单的调用接口
	public static String getClickUrl() {
		clickurl = clickurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
//		System.out.println("罗"+TokenConfig.getAccessToken());
//		System.out.println(clickurl);
		return clickurl;
	}
	
	
	
	
}













