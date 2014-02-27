/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi.maintenance;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoiDisclosureEventTypeMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("showCoiDisclosureEventTypeSynchrnonizationMessage", "false");
        return super.execute(mapping, form, request, response);
    }

    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.route(mapping, form, request, response);
        // set up the attribute  that indicates to the JSP that it should show the synch JS alert.
        request.setAttribute("showCoiDisclosureEventTypeSynchrnonizationMessage", "true");
        return forward;
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.blanketApprove(mapping, form, request, response);
        // since a successful blanket approve sends users to portal we cannot use a JS alert here, so instead forward to 
        // an intermediate JSP that notifies the user that their changes will be synchronized with coi coeus sub module data.
        return mapping.findForward("synchMessagePage");
    }
    
    
    /**
     * Returns the user directly to the Portal.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnToPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }
}