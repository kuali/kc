/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class InterceptIrbRequestFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(InterceptIrbRequestFilter.class);
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         String channelUrl = request.getParameter("channelUrl");
         if (isUrlMatched(request)  || (StringUtils.isNotBlank(channelUrl) && (channelUrl.startsWith("committee") || channelUrl.startsWith("protocol")))
                 || isIrbMaint(request)) {
             HttpServletResponse httpRes   =  (HttpServletResponse)response;
                httpRes.sendRedirect("404error.jsp");
             
         } else {
            chain.doFilter(request, response);
         }

    }

    private boolean isUrlMatched(ServletRequest request) {
        String requestPath = ((HttpServletRequest)request).getServletPath();
        return requestPath.startsWith("/committee") || requestPath.startsWith("/protocol");
    }
    
    private boolean isIrbMaint(ServletRequest request) {
        String boClassName = request.getParameter("businessObjectClassName");
        return StringUtils.isNotBlank(boClassName) && (boClassName.contains("kra.irb.") 
                || boClassName.contains("kra.committee.") || boClassName.contains("kra.questionnaire.") || boClassName.contains("kra.meeting."));
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initialized");
    }


}
