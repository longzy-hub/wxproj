package com.lzy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.lzy.model.dao.crm.UserDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.User;

public class UserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao<User> userDao = (UserDao<User>) DaoFactory.getInstance().getDaoByName("userDao");
		
		int start = 0;
		int count = 5; // 显示的行数

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}

		int next = start + count;
		int pre = start - count;

		int total = userDao.getTotal();

		int last;
		if (0 == total % count)
			last = total - count;
		else
			last = total - total % count;

		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;

		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		request.setAttribute("last", last);
		
		List<User> users = userDao.list(start, count);
		// 在请求中存属性
		request.setAttribute("users", users);
		
		// 转发
		request.getRequestDispatcher("view/user.jsp").forward(request, response);
		// 重定向
//		response.sendRedirect("view/user.jsp");
	}

}
