package org.kuali.kra.test.infrastructure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AutoLoginFilter implements Filter {
	private static final String USER_PARAM_NAME = "autouser";
    
	private FilterConfig filterConfig;
	
	public void init(FilterConfig config) throws ServletException {
	    this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (filterConfig.getInitParameter(USER_PARAM_NAME) == null) {
		    throw new IllegalStateException("the autouser param is not set");
		}
	    
	    chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public String getRemoteUser() {
                return AutoLoginFilter.this.filterConfig.getInitParameter(USER_PARAM_NAME);
            }
        }, response);
	}

	public void destroy() {
	    this.filterConfig = null;
	}
}