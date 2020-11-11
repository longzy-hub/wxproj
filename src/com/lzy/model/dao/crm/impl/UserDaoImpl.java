package com.lzy.model.dao.crm.impl;


import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.crm.base.BaseDaoImpl;
import com.lzy.model.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{

	@Override
	public void queryTest() {
		String sql="select * from user where id=?";
		Object[] params=new Object[] {2};
		User user=queryForBean(sql, params);
		System.out.println(user);
		
	}

}
