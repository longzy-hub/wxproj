package com.lzy.model.dao.crm.impl;


import java.util.List;

import com.lzy.base.BaseDaoImpl;
import com.lzy.model.dao.crm.HierarchyDao;
import com.lzy.model.pojo.Hierarchy;


public class HierarchyDaoImpl extends BaseDaoImpl<Hierarchy> implements HierarchyDao<Hierarchy>{

	@Override
	public Hierarchy queryTest(Integer subordinateId) {

		String sql="select * from wx_hierarchy where subordinate_id=?";
		Object[] params = new Object[] {subordinateId};
		Hierarchy hierarchy = queryForBean(sql,params);
		return hierarchy;
		
	}

	@Override
	public List<Hierarchy> queryDown(Integer superiorId) {
		String sql="select * from wx_hierarchy where superior_id=?";
		Object[] params =new Object[] {superiorId};
		List<Hierarchy> hList=queryForList(sql,params);
		return hList;
	}

	
	
	@Override
	public List<Hierarchy> queryAll() {
		
		String sql="select * from wx_hierarchy";
		Object[] params =null;
		List<Hierarchy> lists=queryForList(sql,params);
		return lists;
	}

}
