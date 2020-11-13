package com.lzy.model.dao.crm.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.lzy.base.BaseDaoImpl;
import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.pojo.User;
import com.lzy.util.JdbcUtil;

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
	
	// 查询用户
	@Override
	public List<User> list(int start, int count) {
		String sql = "select * from wx_user order by id desc limit ?,?";
		Object[] params = {start, count};
		List<User> lists = queryForList(sql, params);		
		return lists;
	}
	// 统计数据条数
	@Override
	public int getTotal() {
		int total = 0;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select count(*) from wx_user";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
//			System.out.println(total);
			JdbcUtil.close(conn, st, rs);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	// 查询所有用户
	@Override
	public List<User> queryUsers() {
		String sql = "select * from wx_user";
		Object[] params = null;
		List<User> lists = queryForList(sql, params);
		return lists;
	}
	
	// 测试
	@Override
	public void queryTest() {
		String sql="select * from wx_user where id=?";
		Object[] params=new Object[] {2};
		User user=queryForBean(sql, params);
		System.out.println(user);
		
	}
}
