/*.
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
package org.kuali.kra.subaward.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
        if (forward == null) {
            return routeSubAwardToHoldingPage(mapping, (SubAwardForm) form);
        } else {
            return forward;
        }
    }
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.route(mapping, form, request, response);
        if (forward == null) {
            return routeSubAwardToHoldingPage(mapping, (SubAwardForm) form);
        } else {
            return forward;
        }
    }

    @Override
    public ActionForward approve(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
    HttpServletResponse response) throws Exception {
        ActionForward forward = super.approve(mapping, form, request, response);
        if(!GlobalVariables.getMessageMap().getErrorMessages().isEmpty()) {
            return forward;
        }else {
            return routeSubAwardToHoldingPage(mapping, (SubAwardForm) form);
        }
    }

    private ActionForward routeSubAwardToHoldingPage(ActionMapping mapping, SubAwardForm subAwardForm) {
        String routeHeaderId = subAwardForm.getSubAwardDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_SUBAWARD_ACTION_PAGE, "SubAwardDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }
    
}
