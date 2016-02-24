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
package org.kuali.kra.subaward.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**.
 *
 * This class represents the Struts Action for
 *  SubAward Actions page(SubAwardActions.jsp)
 */
public class SubAwardActionsAction  extends
SubAwardAction implements AuditModeAction {
    @Override
    public ActionForward execute(ActionMapping mapping,
    ActionForm form, ServletRequest request, ServletResponse response)
       throws Exception {
        ActionForward actionForward = super.
        execute(mapping, form, request, response);
        return actionForward;
    }
    @Override
    public ActionForward activate(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response)
    throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).
        setAuditMode(mapping, (SubAwardForm) form, true);
    }
    @Override
    public ActionForward deactivate(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (SubAwardForm) form, false);
    }
    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ActionForward forward = super.blanketApprove(mapping, form, request, response);
        return routeSubAwardToHoldingPage(mapping, forward, (SubAwardForm) form);
    }
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.route(mapping, form, request, response);
        return routeSubAwardToHoldingPage(mapping, forward, (SubAwardForm) form);
    }

    @Override
    public ActionForward approve(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response) throws Exception {
        ActionForward forward = super.approve(mapping, form, request, response);
        if(!GlobalVariables.getMessageMap().getErrorMessages().isEmpty()) {
            return forward;
        }else {
            return routeSubAwardToHoldingPage(mapping, forward, (SubAwardForm) form);
        }
    }

    private ActionForward routeSubAwardToHoldingPage(ActionMapping mapping, ActionForward returnForward,SubAwardForm subAwardForm) {
        String routeHeaderId = subAwardForm.getSubAwardDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_SUBAWARD_ACTION_PAGE, "SubAwardDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, returnForward, holdingPageForward, returnLocation, routeHeaderId);
    }
    
}
