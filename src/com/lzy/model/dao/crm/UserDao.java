package com.lzy.model.dao.crm;

import java.util.List;

import com.lzy.base.BaseDao;

public interface UserDao<User> extends BaseDao<User> {
	
	// 统计
	public int getTotal();
	
	// 添加用户
	public void addUser(User user);
	
	// 查询一个用户
	public User queryUser(String openid);
	
	// 查询所有
	public List<User> queryUsers();
	
	// 查询用户
	public List<User> list(int start, int count);
	 
	public void queryTest() ;
	
}