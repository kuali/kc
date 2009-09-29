/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.struts.action.ActionForm;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * This is a filter designed to cleanup resources before and after a request.  Some possible things to cleanup
 * may be statics, thread local variables, session artifacts, servlet context artifacts, etc.
 */
public class RequestCleanupFilter implements Filter {

    private FilterConfig filterConfig;
    
    /**
     * Method called before the filter chain executes.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected void cleanupBefore(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.cleanupMessageMap(request);
    }
    
    /**
     * Method called after the filter chain executes.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected void cleanupAfter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.cleanupMessageMap(request);
    }
    
    /**
     * This method resets the message map in the GlobalVariables and on a KualiDocumentFormBase.  This is a workaround
     * to prevent error messages from persisting between tab clicks.
     * @param request the request.
     */
    private void cleanupMessageMap(HttpServletRequest request) {
        final MessageMap messages = new MessageMap();
        GlobalVariables.setMessageMap(messages);
        final ActionForm form = WebUtils.getKualiForm(request);
        if (form instanceof KualiDocumentFormBase) {
            ((KualiDocumentFormBase) form).setMessageMapFromPreviousRequest(messages);
        }
    }
    
    /**
     * Nulls out the filter config.
     * {@inheritDoc}
     */
    public void destroy() {
        this.filterConfig = null;
    }

    /** {@inheritDoc} */
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    /**
     * 
     * This method calls {@link #cleanupBefore(HttpServletRequest, HttpServletResponse, FilterChain)}, executes the filter chain, and
     * finally calls {@link #cleanupAfter(HttpServletRequest, HttpServletResponse, FilterChain)}.
     * @param request the request
     * @param response the response
     * @param chain the filter chain
     * @throws IOException if failure occurs
     * @throws ServletException if failure occurs
     */
    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            this.cleanupBefore(request, response, chain);
        } finally {
            try {
                chain.doFilter(request, response);
            } finally {
                this.cleanupAfter(request, response, chain);
            }
        }
    }

    /**
     * Saves the filter config for later access.
     * {@inheritDoc}
     */
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
    
    /**
     * Gets the filter config.
     * @return the filter config
     */
    protected final FilterConfig getFilterConfig() {
        return this.filterConfig;
    }
}
