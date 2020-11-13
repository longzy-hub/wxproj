package com.lzy.model.dao.crm.impl;

import java.util.List;

import com.lzy.base.BaseDaoImpl;
import com.lzy.model.dao.crm.AdminDao;
import com.lzy.model.pojo.Admin;

public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao<Admin>{

	@Override
	public void addAdmin(Admin admin) {

		String sql = "insert into admin(id,loginacct,adminpasswd,nickname) values(?,?,?,?)";
		
		
		Object[] params=new Object[] {admin.getId(),admin.getLoginacct(),admin.getAdminpasswd(),admin.getNickname()};
		add(sql, params);

		
	}

	@Override
	public void updateAdmin(int id, String oldParam, String newParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin queryTest(String loginacct) {
		String sql="select * from wx_admin where loginacct=?";
		Object[] params = new Object[] {loginacct};
		Admin admin = queryForBean(sql,params);
		return admin;
	}

	@Override
	public List<Admin> queryAll() {
		String sql="select * from wx_admin";
		Object[] params =null;
		List<Admin> lists=queryForList(sql,params);
		return lists;
		
	}

	
	

}
