package com.lzy.test;

import org.junit.Test;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.User;

public class Test1 {
	// 测试环境搭建
	@Test
	public void test1() {
		UserDao ud =  (UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		ud.queryTest();
		
	}
	

}
