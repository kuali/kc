/*
 * Copyright 2006-2009 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

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
    private static final Logger LOG = Logger.getLogger(PerformanceLoggingFilter.class);
    
    /**
     * @see Filter#destroy()
     */
    public void destroy() {}
    
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig config) throws ServletException {
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
        long start = System.currentTimeMillis();
        long startMem = Runtime.getRuntime().freeMemory();
        chain.doFilter(request, response);

        if (LOG.isInfoEnabled() ) {
            long elapsed = System.currentTimeMillis() - start;
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            LOG.info(((HttpServletRequest) request).getRequestURI() + " : " + elapsed + " ms");
            LOG.info(((HttpServletRequest) request).getRequestURI() + " : " + usedMemory + " memory used");
        }
    }
}
