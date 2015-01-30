/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kew.routing.web.ClientAppDocHandlerRedirectAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This class is for copy document.
 * currently, PD & protocol provide 'copy' function.
 */
        
public class CustomDocHandlerRedirectAction extends ClientAppDocHandlerRedirectAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return start(mapping, form, request, response);
    }
    
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward returnForward = super.execute(mapping, form, request, response);
        
        String docHandler = returnForward.getPath();
        if (("ProposalDevelopmentDocument").equals(request.getParameter("documentTypeName"))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, "actions");
        } else if (("ProtocolDocument").equals(request.getParameter("documentTypeName"))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, "protocolActions");
        } else if (("AwardDocument").equals(request.getParameter("documentTypeName"))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, "awardActions");
        }
          
        returnForward = new ActionForward(docHandler, returnForward.getRedirect());
        return returnForward;
    }
}
