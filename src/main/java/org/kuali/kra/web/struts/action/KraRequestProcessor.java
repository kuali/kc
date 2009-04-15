/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.kuali.kra.web.struts.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.web.struts.action.KualiRequestProcessor;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * <p>
 * This class handles setup of user session and restoring of action form.
 * </p>
 * 
 * <p>
 * This class also sorts the Audit Map In the HttpServletRequest
 * </p>
 */
public class KraRequestProcessor extends KualiRequestProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    protected ActionForward processActionPerform(final HttpServletRequest request,
        final HttpServletResponse response, final Action action, final ActionForm form,
        final ActionMapping mapping) throws IOException, ServletException {
        
        final ActionForward actionForward;
        
        if (this.isSessionExpired(request)) {
            final HttpSession session = request.getSession();
            session.removeAttribute(KeyConstants.SESSION_EXPIRED_IND);
            actionForward = mapping.findForward(RiceConstants.MAPPING_PORTAL); 
        } else {
            actionForward = super.processActionPerform(request, response, action, form, mapping);
            //must come after call to super because the super class sets audit map in request
            this.sortAuditMapInRequest(request);
        }
        
        return actionForward;
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
        ActionMapping mapping) throws ServletException {

        if (!this.isSessionExpired(request)) {
            super.processPopulate(request, response, form, mapping);
        }
    }

    /**
     * This method checks if session is expired
     * (according to the {@link KeyConstants.SESSION_EXPIRED_IND KeyConstants.SESSION_EXPIRED_IND}).
     * @param request the HttpServletRequest
     * @return true if expired
     */
    private boolean isSessionExpired(final HttpServletRequest request) {
        assert request != null : "the request is null";
        
        final HttpSession session = request.getSession();
        final Boolean sessionExpired = (Boolean) session.getAttribute(KeyConstants.SESSION_EXPIRED_IND);
        //null means it is not expired...
        return Boolean.TRUE.equals(sessionExpired);
    }
    
    /**
     * This method retrieves the audit map from the http request.
     * 
     * @param request the HttpServletRequest
     * @return the audit map.  If not present this method will return null.
     */
    private Map<String, AuditCluster> getAuditMap(final HttpServletRequest request) {
        assert request != null : "the request is null";
        
        @SuppressWarnings("unchecked")
        final Map<String, AuditCluster> auditErrorsMap
            = (Map<String, AuditCluster>) request.getAttribute(RiceConstants.AUDIT_ERRORS);
        
        return auditErrorsMap;
    }
    
    /**
     * Sorts the audit map in the request.
     * @param request the HttpServletRequest
     */
    private void sortAuditMapInRequest(final HttpServletRequest request) {
        assert request != null : "the request is null";
        
        final Map<String, AuditCluster> auditMap = getAuditMap(request);
        if (auditMap != null) {
            AuditMapSorter sorter = new AuditMapSorter(auditMap);
            sorter.sort(AuditMapSorter.DEFAULT_PATTERN_COMPARATOR_MAP);
        }
    }
}
