/*
 * Copyright 2005-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.kuali.kra.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.web.struts.action.KualiRequestProcessor;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * This class handles setup of user session and restoring of action form.
 * 
 * 
 */
public class KraRequestProcessor extends KualiRequestProcessor {

    @Override
    protected ActionForward processActionPerform(final HttpServletRequest request, final HttpServletResponse response,
	    final Action action, final ActionForm form, final ActionMapping mapping) throws IOException, ServletException {

        ActionForward actionForward = super.processActionPerform(request, response, action, form, mapping);
        
        Boolean sessionExpired = (Boolean) request.getSession().getAttribute(KeyConstants.SESSION_EXPIRED_IND);  
        if (sessionExpired != null && sessionExpired.booleanValue() == true) {
            request.getSession().removeAttribute(KeyConstants.SESSION_EXPIRED_IND);
            actionForward = mapping.findForward(RiceConstants.MAPPING_PORTAL); 
        }
        
        return actionForward;
    }
    
    @Override
    protected void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
        ActionMapping mapping) throws ServletException {
            
        Boolean sessionExpired = (Boolean) request.getSession().getAttribute(KeyConstants.SESSION_EXPIRED_IND);  
        if (sessionExpired != null && sessionExpired.booleanValue() == true) {
            return;
        }
        
        super.processPopulate(request, response, form, mapping);
    }

}