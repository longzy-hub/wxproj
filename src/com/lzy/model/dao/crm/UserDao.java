package com.lzy.model.dao.crm;

import com.lzy.annotation.MyTransaction;

public interface UserDao<User> {
	// 添加用户
	@MyTransaction
	public void addUser(User user);
	
}