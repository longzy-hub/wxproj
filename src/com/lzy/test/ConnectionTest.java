package com.lzy.test;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.lzy.util.JdbcUtil;


public class ConnectionTest {
	@Test
	public void test() throws Exception{
		Connection conn=JdbcUtil.getConnection();
		System.out.println(conn);
	}
	
}
