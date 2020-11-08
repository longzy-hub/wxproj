package com.lzy.model.configuration;


import com.lzy.model.bean.AccessToken;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TokenConfig {

	// ��ȡAccess Token �ӿڵ��õ�ַ
	private static String accessTokenUrl = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String APPID = "wxdfc9f9997018cd3b";
	private static String APPSECRET = "78bb7c7a9afab8c456d1dff72d8a15e1";
	
	// ��ȡ�ͷ���url
	private static String customerurl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	// ��ȡ�û��Ļ�����Ϣ��url
	private static String userurl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// ��ȡ�û���ά���url
	private static String qrcodeurl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	// ������ʱ�ز�
	private static String matterurl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE ";
	
	private static AccessToken at;

	// ��ʼ���ҵ�token
	private static void initToken() {
		// �滻appid �� appsecret
		String url = accessTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		// ��΢�ŷ�������ȡ��ӦȨ��(�ӿڵ�������)
		String tokenStr = HttpUtil.get(url);
		// ����΢�ŷ�������������json����
		JSONObject jsonObject = JSONUtil.parseObj(tokenStr);
		// ȡ��token��expire
		String token = jsonObject.getStr("access_token");
		String expireIn = jsonObject.getStr("expires_in");
		// ��װ��tokenֵ
		at = new AccessToken(token, expireIn);
	}

	// ��ȡtoken
	public static String getAccessToken() {
		if (at == null || at.expiredStatus()) {
			initToken();
		}
		return at.getAccesstoken();
	}

	// ��ȡ�ͷ��ĵ��ýӿ�
	public static String getCustomerUrl() {
		customerurl = customerurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		return customerurl;
	}
	
	// ��ȡ�û�������Ϣ���ýӿ�
	public static String getUserUrl() {
		// �����û��Ļ�����Ϣ�ӿ�
		userurl = userurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		return userurl;
	}
	
	// ��ȡ���ںŶ�ά��ĵ��ýӿ�
	public static String getQrcodeUrl() {
		qrcodeurl = qrcodeurl.replace("TOKEN", TokenConfig.getAccessToken());
		return qrcodeurl;
	}
	
	// ������ʱ�ز�
	public static String getMatterUrl() {
		matterurl = matterurl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken()).replace("TYPE", "image");
		return matterurl;
	}
	
	
	
	
	
	
}













