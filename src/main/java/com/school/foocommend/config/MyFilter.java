package com.school.foocommend.config;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter Init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter");
		try {
			
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {
			request.setAttribute("errorMessage", ex);
		}
	}

	@Override
	public void destroy() {
		System.out.println("Filter Destroy");
	}
}
