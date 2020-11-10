package com.lzy.model.dao.crm.base;


import java.util.List;

import com.lzy.annotation.MyTransaction;


public interface BaseDao<T> {
	@MyTransaction
	int add(String sql,Object... args);
	@MyTransaction
	int update(String sql,Object... args);
	@MyTransaction
	int delete(String sql,Object... args);
	T queryForBean(String sql,Object... args);
	List<T> queryForList(String sql,Object... args);
	
}
