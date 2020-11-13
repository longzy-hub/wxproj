package com.lzy.model.dao.crm;



import java.util.List;

import com.lzy.base.BaseDao;

public interface HierarchyDao<Hierarchy> extends BaseDao<Hierarchy> {
	
	public Hierarchy queryTest(Integer subordinateId) ;
	
	public List<Hierarchy> queryDown(Integer superiorId);
	
	public List<Hierarchy> queryAll();

}
