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
