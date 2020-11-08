package com.lzy.model.bean;

public class AccessToken {

	private String accesstoken;
	private long expireTime;
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public AccessToken(String accesstoken, String expireIn) {
		super();
		this.accesstoken = accesstoken;
		this.expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn)*1000;
	}
	
	// 过期时间
	public boolean expiredStatus() {
		// 如果返true,身份过期
		return System.currentTimeMillis() > expireTime;
	}
	
}
