/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
import org.kuali.kra.infrastructure.KeyConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionExpiredFilter implements Filter {
    private static final Log LOG = LogFactory.getLog(SessionExpiredFilter.class);
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest) request;
        if (hrequest.getRequestedSessionId() != null
                && hrequest.isRequestedSessionIdValid() == false) {
            hrequest.getSession().setAttribute(KeyConstants.SESSION_EXPIRED_IND, new Boolean(true)); 
        } else {
        	if (hrequest.getSession() != null) {
        		hrequest.getSession().removeAttribute(KeyConstants.SESSION_EXPIRED_IND);
        	}
        }
          
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initialized");
    }

}
