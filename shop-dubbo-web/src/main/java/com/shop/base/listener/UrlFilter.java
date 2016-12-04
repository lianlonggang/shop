package com.shop.base.listener;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.base.util.SpringApplicationContext;


public class UrlFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		UrlFilterUtil.doFilter((HttpServletRequest)arg0, (HttpServletResponse)arg1, arg2);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		SpringApplicationContext.setApplication(arg0.getServletContext());
	}

}
