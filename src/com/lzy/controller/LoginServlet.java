package com.lzy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzy.model.dao.crm.AdminDao;
import com.lzy.model.dao.factory.DaoFactory;
import com.lzy.model.pojo.Admin;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginacct=request.getParameter("loginacct");
		String userpasswd =request.getParameter("userpasswd");
		//response.getWriter().println(loginacct+","+userpasswd);
		//response.sendRedirect("view/main.jsp");
		//request.getRequestDispatcher("view/main.jsp").forward(request, response);
		AdminDao adminDao=(AdminDao) DaoFactory.getInstance().getDaoByName("adminDao");
		Admin ad =new Admin();
		 ad=(Admin)adminDao.queryTest(loginacct);
		 
		if(null!=ad) {
			//response.sendRedirect("view/main.jsp");
			
			if(userpasswd.equals(ad.getAdminpasswd())) {
				response.sendRedirect("view/main.jsp");
				}else {
					response.sendRedirect("view/login.jsp");
				}
			
				
			
		}else {
			response.sendRedirect("view/login.jsp");
 		}
		

		
		
	}

}
