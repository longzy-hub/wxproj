package com.lzy.model.dao.crm;

import com.lzy.base.BaseDao;

public interface UserDao<User> extends BaseDao<User> {
	
	// 添加用户
	public void addUser(User user);
	
	// 查询一个用户
	public User queryUser(String openid);
	
	
	
	public void queryTest() ;
	
}