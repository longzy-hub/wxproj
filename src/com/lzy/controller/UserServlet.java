package com.lzy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.User;


public class UserServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查询用户
		UserDao<User> userDao = (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
		List<User> lists = userDao.queryUsers();
		
		
		request.setAttribute("lists", lists);
		String path = "view/user.jsp";
		response.sendRedirect(path);
		
	}
	

}
