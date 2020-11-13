package com.lzy.model.pojo;

public class Admin {
	private Integer id;
	private String loginacct;
	private String adminpasswd;
	private String nickname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginacct() {
		return loginacct;
	}
	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}
	public String getAdminpasswd() {
		return adminpasswd;
	}
	public void setAdminpasswd(String adminpasswd) {
		this.adminpasswd = adminpasswd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Admin(Integer id, String loginacct, String adminpasswd, String nickname) {
		super();
		this.id = id;
		this.loginacct = loginacct;
		this.adminpasswd = adminpasswd;
		this.nickname = nickname;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", loginacct=" + loginacct + ", adminpasswd=" + adminpasswd + ", nickname="
				+ nickname + "]";
	}
	
	

}
