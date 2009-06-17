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
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.kuali.kra.util.SensitiveFieldFilterUtil;

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
 * <br/>
 * Everything is logged at DEBUG except the following uses the INFO Log Level:
 * <ul>
 *   <li>Currently logged in user</li>
 *   <li>Date/Time</li>
 *   <li>Intended Action</li>
 * </ul>
 *
 */
public class RequestLoggingFilter implements Filter {
    
    private static final Logger LOG = Logger.getLogger(RequestLoggingFilter.class);
    
    //(Partial parameter names can also be used, Note: NO wildcard characters)
    private static final String [] paramsToLogInfoByDefault = {"methodToCall"}; 
    
    private static final Boolean SENSITIVE_FILEDS_FILTER = true;
    
    private FilterConfig config;
    
    /**
     * @see Filter#destroy()
     */
    public void destroy() {}
    
    /**
     * @see Filter#init(FilterConfig)
     */
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
        
        for (Enumeration<String> parameterNames = request.getParameterNames(); parameterNames.hasMoreElements();) {
            
            String parameterName = parameterNames.nextElement();
            
            //Avoid logging Sensitive Fields if SENSITIVE_FILEDS_FILTER is set to true
            if(sensitivefieldsfilter && SensitiveFieldFilterUtil.isFieldSensitive(parameterName)) {
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
     * Determine if the logging is allowed by using {@link Logger#isInfoEnabled()}. Currently the <code>INFO</code> level
     * is required for logging here. It is possible that by extending this class and overriding {@link #isLoggingAllowed()} the 
     * required log level can be adjusted.
     *
     * @return the value of {@link Logger#isInfoEnabled()} directly.
     */
    protected boolean isInfoNotAllowed() {
        return !LOG.isInfoEnabled();
    }
}
