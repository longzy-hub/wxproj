package com.lzy.model.dao.crm;

import java.util.List;

import com.lzy.annotation.MyTransaction;

public interface UserDao<User> {
	// 添加用户
	@MyTransaction
	public void addUser(User user);
	
	// 查询用户
	public List<User> queryUser();
	
}