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
package org.kuali.kra.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * Handles any AuthorizationException by logging it first and then passing it forward to an explanation page.
 */
public class AuthorizationExceptionHandler extends ExceptionHandler {
    
    private static final String AUTHORIZATION_EXCEPTION_HANDLER = "authorizationExceptionHandler";

    private static final Log LOG = LogFactory.getLog(AuthorizationExceptionHandler.class);
    
    /**
     * Logs the AuthorizationException before forwarding the user to the explanation page.
     * 
     * @see org.apache.struts.action.ExceptionHandler#execute(
     *      java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     *      javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(Exception exception, ExceptionConfig exceptionConfig, ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) {
        
        if (LOG.isTraceEnabled()) {
            String message = String.format("ENTRY %s", exception.getMessage());
            LOG.trace(message);
        }
        
        request.setAttribute(Globals.EXCEPTION_KEY, exception);
        
        ActionForward forward = mapping.findForward(AUTHORIZATION_EXCEPTION_HANDLER);
        
        if (LOG.isTraceEnabled()) {
            LOG.trace(String.format("EXIT %s", exception.getMessage()));
        }
        
        return forward;
    }

}