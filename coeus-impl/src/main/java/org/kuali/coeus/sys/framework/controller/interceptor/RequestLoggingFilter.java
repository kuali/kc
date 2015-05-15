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
import org.apache.log4j.MDC;
import org.kuali.coeus.sys.framework.sensitive.SensitiveFieldMatcher;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

//CHECKSTYLE_OFF:IllegalImport need to use log4j for MDC - commons logging does have this feature
//CHECKSTYLE_ON:IllegalImport

/**
 * Part of KRA's {@link FilterChain} that handles {@link HttpServletRequest} and {@link HttpSession} state. State information
 * that is recorded in logs is:
 * <ul>
 *   <li>Currently logged in user</li>
 *   <li>Date/Time</li>
 *   <li>Intended Action</li>
 *   <li>Request Headers</li>
 *   <li>Target URI</li>
 *   <li>Client IP Address</li>
 *   <li>Request Parameters</li>
 * </ul>
 *
 * Everything is logged at DEBUG except the following uses the INFO Log Level:
 * <ul>
 *   <li>Currently logged in user</li>
 *   <li>Date/Time</li>
 *   <li>Intended Action</li>
 * </ul>
 *
 */
public class RequestLoggingFilter implements Filter {
    
    private static final Log LOG = LogFactory.getLog(RequestLoggingFilter.class);
    
    //(Partial parameter names can also be used, Note: NO wildcard characters)
    private static final String [] paramsToLogInfoByDefault = {"methodToCall"}; 
    
    private static final Boolean SENSITIVE_FILEDS_FILTER = true;
    
    private FilterConfig config;
    
    @Override
    public void destroy() {}
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    /**
     * <p>Does the actual logging. The log4j.properties file already covers user and date/time logging for us, so the
     * only thing that gets logged in <b>INFO</b> here is the intended action.</p>
     * 
     * <p><b>Inefficiency:</b>Currently, an action map is being used
     * to determine the intended action of the user.</p>
     *
     *
     * @see javax.servlet.Filter#doFilter(ServletRequest,ServletResponse,FilterChain)
     */
    public void doFilter(ServletRequest request, 
                         ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response);
          
        chain.doFilter(request, response);
    }

    /**
     * <p>Does the actual logging. The log4j.properties file already covers user and date/time logging for us, so the
     * only thing that gets logged in <b>INFO</b> here is the intended action.</p>
     * 
     * <p><b>Inefficiency:</b>Currently, an action map is being used
     * to determine the intended action of the user.</p>
     * 
     * <p>This is a convenience method so avoid redeclaration of variables.</p>
     *
     * @param request cast from ServletRequest
     * @param response cast from ServletResponse
     * @see #doFilter(ServletRequest,ServletResponse,FilterChain)
     */
    private void doFilter(HttpServletRequest request, 
                          HttpServletResponse response) throws IOException, ServletException {
        if (isInfoNotAllowed()) {
            return;
        }        
        
        MDC.put("clientIp", request.getRemoteAddr());
                
        LOG.info("Current User :" + request.getRemoteUser());
        
        //RequestURI & methodToCall (request parameter) are logged to trace user actions
        LOG.info("RequestURI : " + request.getRequestURI());
        LOG.info(getRequestParametersMessage(request, SENSITIVE_FILEDS_FILTER, paramsToLogInfoByDefault));
        
        if (LOG.isDebugEnabled()) {

            LOG.debug("\n***************************** HEADERS **********************************\n"
                      + getRequestHeadersMessage(request));
            LOG.debug("\n***************************** PARAMETERS *******************************\n"
                      + getRequestParametersMessage(request, SENSITIVE_FILEDS_FILTER));
            LOG.debug("\n***************************** ATTRIBUTES *******************************\n"
                      + getRequestAttributesMessage(request));
        }
    }

    /**
     * Constructs a log message that displays HTTP header information belonging to the given
     * {@link HttpServletRequest} instance. This method uses two nested loops to iterate headers
     * and then iterate through header values because a header may have one or more values.
     *
     * @param request
     * @return Log message
     */
    private String getRequestHeadersMessage(HttpServletRequest request) {
        StringBuilder retval = new StringBuilder();
        for (Enumeration<String> headerNames = request.getHeaderNames();
             headerNames.hasMoreElements();) {
            String headerName = headerNames.nextElement();
            retval.append(headerName).append(": {").toString();

            for (Enumeration<String> headerValues = request.getHeaders(headerName); 
                 headerValues.hasMoreElements();) {
                String headerValue = headerValues.nextElement();

                retval.append(headerValue);
                if (headerValues.hasMoreElements()) {
                    retval.append(",");
                }
            }

            retval.append("}\n");
        }
        
        return retval.toString();
    }

    /**
     * Constructs a log message that displays parameter information belonging to the given
     * {@link HttpServletRequest} instance. It also provides {@link Boolean} flag
     * to filter out sensitive field info from logging. Method is also enhanced to provide
     * info on selected parameters, which can be passed along {@link String} array.
     * 
     * @param request
     * @param sensitivefieldsfilter 
     * @param params (Partial parameter names can also be used, Note: NO wildcard characters)
     * @return Log message
     */
    private String getRequestParametersMessage(HttpServletRequest request, Boolean sensitivefieldsfilter, String... params) {
        
        StringBuilder retval = new StringBuilder();

        final SensitiveFieldMatcher matcher = KcServiceLocator.getService("sensitiveFieldPatternMatcher");

        for (Enumeration<String> parameterNames = request.getParameterNames(); parameterNames.hasMoreElements();) {
            
            String parameterName = parameterNames.nextElement();
            
            //Avoid logging Sensitive Fields if SENSITIVE_FILEDS_FILTER is set to true
            if(sensitivefieldsfilter && matcher.match(parameterName)) {
                continue;
            }
            
            if(params.length == 0){
                retval.append(getRequestParameterMessage(request, parameterName));
                retval.append("\n");
            } 
            else {
                for(String p: params) {
                    if(parameterName.contains(p)) {
                        retval.append(getRequestParameterMessage(request, parameterName));
                        break; //breaks inner for
                    }
                }//end of for
            }//end of else 
            
        }//end of outer for
        
        return retval.toString();
    }
    
    /**
     * This method constructs and returns message info for passed parameter. Method also
     * iterates over parameterValues returned from request because a parameter may have 
     * one or more values.
     * @param request
     * @param parameterName
     * @return Parameter Info
     */
    private String getRequestParameterMessage(HttpServletRequest request, String parameterName) {
        StringBuilder retval = new StringBuilder();
        retval.append(parameterName).append(": {").toString();

        for (String parameterValue : request.getParameterValues(parameterName)) {
            retval.append(parameterValue);
            retval.append(",");
        }

        retval.append("}");
        
        return retval.toString();
    }
    
    /**
     * Constructs a log message that displays attribute information belonging to the given
     * {@link HttpServletRequest} instance. 
     *
     * @param request
     * @return Log message
     */
    private String getRequestAttributesMessage(HttpServletRequest request) {
        StringBuilder retval = new StringBuilder();
        for (Enumeration<String> attributeNames = request.getAttributeNames();
             attributeNames.hasMoreElements();) {
            String attributeName = attributeNames.nextElement();
            retval.append(attributeName).append(": ")
                .append(request.getAttribute(attributeName)).append("\n").toString();
        }
        
        return retval.toString();
    }    

    /**
     * Determine if the logging is allowed by using {@link Log#isInfoEnabled()}. Currently the <code>INFO</code> level
     * is required for logging here. It is possible that by extending this class and overriding {@link #isInfoNotAllowed()} the
     * required log level can be adjusted.
     *
     * @return the value of {@link Log#isInfoEnabled()} directly.
     */
    protected boolean isInfoNotAllowed() {
        return !LOG.isInfoEnabled();
    }
}
