package com.lzy.model.dao.crm;

import java.util.List;

import com.lzy.annotation.MyTransaction;
import com.lzy.base.BaseDao;

public interface AdminDao<Admin> extends BaseDao<Admin> {

	@MyTransaction
	public void addAdmin(Admin admin );
	
	public void updateAdmin(int id ,String oldParam,String newParam );
	
	public Admin queryTest(String loginacct) ;
	
	public List<Admin> queryAll();

}
