/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This is the struts action class for handling the exception for Kuali
 * applications.
 * 
 */
public class AuthorizationExceptionAction extends Action {
    
    private static final String MESSAGE_FIELD = "message";
    
    private static final Log LOG = LogFactory.getLog(AuthorizationExceptionAction.class);

    /**
     * Dispatches action to be taken during an AuthorizationException.
     * 
     * @see org.apache.struts.action.Action#execute(
     *      org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, 
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("ENTRY %s%n%s", form.getClass().getSimpleName(), request.getRequestURI()));
        }
        
        ActionForward forward = null;

        Exception exception = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);

        if (exception == null) {
            forward = mapping.findForward(KNSConstants.MAPPING_CLOSE);
        } else {
            forward = processException(mapping, form, request, exception);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("EXIT %s", (forward == null) ? "null" : forward.getPath()));
        }

        return forward;
    }
    
    private ActionForward processException(ActionMapping mapping, ActionForm form, HttpServletRequest request, Exception exception) throws Exception {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(MESSAGE_FIELD, exception.getMessage());
        
        request.setAttribute(AuthorizationExceptionAction.class.getName(), properties);
        
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

}