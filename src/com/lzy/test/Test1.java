package com.lzy.test;


import org.junit.Test;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.User;

public class Test1 {
	
	// 查询用户
	@Test
	public void test6() {
		UserDao<User> ud = (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
		System.out.println(ud.list(1, 5));
	}
	
	// 测试统计条数
	@Test
	public void test5() {
		UserDao<User> ud = (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
		ud.getTotal();
	}
	
	
	// 测试环境搭建
	@Test
	public void test1() {
		UserDao ud =  (UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		ud.queryTest();
	}
	
	@Test
	public void test2() {
		UserDao ud = (UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		User user = new User();
		user.setOpenid("oLC2p6QmNYf2uSR-Wpd5WVxmsdvA");
		user.setNickname("拖hai哥☺");
		user.setSex(1);
		user.setHeadimgurl("http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM7OItgkfTw9rmQtiajJibfBtMoFTriaBXXiazTxia2ZEfxbb2m8iahMfDxoLhGMwNT4V961IZXbYJtUXgZo3bO5kGl8ic2vD0U5fIDoe0/132");
		ud.addUser(user);
	}
	@Test
	public void test3() {
		UserDao ud = (UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		System.out.println(ud.queryUser("oLC2p6QmNYf2uSR-Wpd5WVxmsdv2"));
	}
	

}
