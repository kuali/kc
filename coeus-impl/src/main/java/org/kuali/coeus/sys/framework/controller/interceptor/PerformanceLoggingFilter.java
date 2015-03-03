/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.controller.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Part of KRA's {@link FilterChain} that handles timing a {@link HttpServletRequest} and reporting on the state of 
 * resource consumption. The following information gets logged from this {@link Filter} at the <b>INFO</b> log level.
 * <ul>
 *   <li>Request execution time</li>
 *   <li>Initial resource consumption</li>
 *   <li>Initial available resources</li>
 *   <li>Final resource consumption</li>
 *   <li>Final available resources</li>
 * </ul>
 */
public class PerformanceLoggingFilter implements Filter {
    private static final Log LOG = LogFactory.getLog(PerformanceLoggingFilter.class);
    
    private static final String IGNORED_URLS_PARAM = "ignored.urls";
    
    private List<Pattern> ignoredUrls = new ArrayList<>();
    
    @Override
    public void destroy() {}
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    	if (config.getInitParameter(IGNORED_URLS_PARAM) != null) {
    		for (String urlPattern : config.getInitParameter(IGNORED_URLS_PARAM).split(",")) {
    			ignoredUrls.add(Pattern.compile(urlPattern));
    		}
    	}
    }

    /**
     * <p>Does the actual logging. The log4j.properties file already covers user and date/time logging for us, so the
     * only thing that gets logged in <b>INFO</b> here is the intended action.</p>
     * 
     * <p>Memory used for a request is calculated with {@link Runtime#freeMemory()} and 
     * {@link Runtime#totalMemory()}
     *
     * @see javax.servlet.Filter#doFilter(ServletRequest,ServletResponse,FilterChain)
     * @see Runtime#totalMemory()
     * @see Runtime#freeMemory()
     */
    public void doFilter(ServletRequest request, 
                         ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
    	if (isIgnoredRequest(((HttpServletRequest) request).getRequestURL().toString())) {
    		chain.doFilter(request, response);
    	} else {
	        long start = System.currentTimeMillis();
	        chain.doFilter(request, response);
	
	        if (LOG.isInfoEnabled() ) {
	            long elapsed = System.currentTimeMillis() - start;
	            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	            LOG.info(((HttpServletRequest) request).getRequestURI() + " : " + elapsed + " ms");
	            LOG.info(((HttpServletRequest) request).getRequestURI() + " : " + usedMemory + " memory used");
	        }
    	}
    }
    
    public boolean isIgnoredRequest(String requestUrl) {
    	for (Pattern pattern : ignoredUrls) {
    		if (pattern.matcher(requestUrl).matches()) {
    			return true;
    		}
    	}
    	return false;
    }
}
