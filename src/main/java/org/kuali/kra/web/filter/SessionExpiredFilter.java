/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.KeyConstants;

public class SessionExpiredFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionExpiredFilter.class);
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest) request;
        
        if (hrequest.getRequestedSessionId() != null
                && hrequest.isRequestedSessionIdValid() == false) {
            hrequest.getSession().setAttribute(KeyConstants.SESSION_EXPIRED_IND, new Boolean(true)); 
        } 
          
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initialized");
    }

}
