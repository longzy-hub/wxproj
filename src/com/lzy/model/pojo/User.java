package com.lzy.model.pojo;

public class User {
	private Integer id;
	private String openid;	// 用户的标识，对当前公众号唯一
	private String nickname; // 用户的昵称
	private Integer sex; // 性别
	private String headimgurl; // 用户头像
	
	public User() {
		super();
	}

	public User(Integer id, String openid, String nickname, Integer sex, String headimgurl) {
		super();
		this.id = id;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.headimgurl = headimgurl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", headimgurl="
				+ headimgurl +"]";
	}
	
	
	
}
