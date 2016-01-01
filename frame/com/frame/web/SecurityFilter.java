package com.frame.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;


public class SecurityFilter implements Filter {

	private List<String> excludedUrlList = new ArrayList<String>();

	@Override
	public void destroy() {
		excludedUrlList.clear();

		excludedUrlList = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		String requestUri = ((HttpServletRequest) request).getRequestURI();
		
		
		long startTime = System.currentTimeMillis();
		request.setAttribute("_startTime", startTime);

		for (String url : excludedUrlList) {
			if (requestUri.endsWith(url)) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("appModel") == null) {
			
			String contextPath = ((HttpServletRequest) request).getContextPath();
			
			((HttpServletResponse) response).sendRedirect(contextPath + "/login");
			
		} else {

			if (request.getParameter("selectedModuleId") != null) {
				((ApplicationModel) session.getAttribute("appModel"))
						.setSelectedModuleId(Long.parseLong(request
								.getParameter("selectedModuleId")));

			}

			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String excludedUrls = arg0.getInitParameter("excludedUrls");

		String[] array = StringUtils.split(excludedUrls, ",");

		excludedUrlList.clear();

		excludedUrlList.addAll(Arrays.asList(array));
	}

}
