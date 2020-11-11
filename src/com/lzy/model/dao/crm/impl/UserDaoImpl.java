package com.lzy.model.dao.crm.impl;


import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.crm.base.BaseDaoImpl;
import com.lzy.model.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{

	// 添加用户
	public void addUser(User user) {
		String sql = "insert into wx_user(openid,nickname,sex,headimgurl) values(?,?,?,?)";
		Object[] params = new Object[] {user.getOpenid(),user.getNickname(),user.getSex(),user.getHeadimgurl()};
		add(sql, params);
	}
	// 查询一个用户
	public User queryUser(String openid) {
		String sql = "select * from wx_user where openid=?";
		Object[] params = {openid};
		User user = queryForBean(sql, params);
		return user;		
	}

	// 测试
	@Override
	public void queryTest() {
		String sql="select * from wx_user where id=?";
		Object[] params=new Object[] {7};
		User user=queryForBean(sql, params);
		System.out.println(user);
		
	}
}
