package com.lzy.model.dao.crm.impl;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.crm.base.BaseDaoImpl;
import com.lzy.model.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{

	// 添加用户
	@Override
	public void addUser(User user) {
		String sql = "insert into user values(?,?,?,?,?)";
		Object[] params = new Object[] {};
		add(sql, params);
	}

}
