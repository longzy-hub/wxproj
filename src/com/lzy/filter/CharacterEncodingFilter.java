package com.lzy.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		request.setCharacterEncoding("UTF-8"); // 只解�? post，不能解�? get
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		chain.doFilter(new MyRequest(request), response); // request.getparameter("password");
		//.....
	}

	/*
	 * 1.写一个类，实现与被增强对象相同的接口 2.定义�?个变量，记住被增强对�? 3.定义�?个构造方法，接收被增强对�? 4.覆盖想增强的方法
	 * 5.对于不想增强的方法，直接调用被增强对象（目标对象）的方法
	 */

	class MyRequest extends HttpServletRequestWrapper {

		private HttpServletRequest request;

		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		public String getParameter(String name) {
			String value = this.request.getParameter(name);//这里还有可能是乱�?
			if (!request.getMethod().equalsIgnoreCase("get")) {
				return value;
			}

			if (value == null) {
				return null;
			}

			try {
				return value = new String(value.getBytes("iso8859-1"),
						request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}