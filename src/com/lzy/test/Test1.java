package com.lzy.test;

import org.junit.Test;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.crm.impl.UserDaoImpl;
import com.lzy.model.dao.factory.DaoFactory;

public class Test1 {
	// 测试环境搭建
	@Test
	public void test1() {
//		UserDao ud =  (UserDao) DaoFactory.getInstance().getDaoByName("userDao");
//		ud.queryUser();
		UserDao ud = new UserDaoImpl();
		ud.queryUser();
		
	}
	

}
