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
package org.kuali.coeus.sys.framework.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.routing.web.ClientAppDocHandlerRedirectAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This class is for copy document.
 * currently, PD &amp; protocol provide 'copy' function.
 */
        
public class CustomDocHandlerRedirectAction extends ClientAppDocHandlerRedirectAction {

    public static final String DOCUMENT_TYPE_NAME = "documentTypeName";
    public static final String PROTOCOL_DOCUMENT = "ProtocolDocument";
    public static final String IACUC_PROTOCOL_DOCUMENT = "IacucProtocolDocument";
    public static final String AWARD_DOCUMENT = "AwardDocument";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return start(mapping, form, request, response);
    }
    
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward returnForward = super.execute(mapping, form, request, response);
        
        String docHandler = returnForward.getPath();
        if (ProposalDevelopmentConstants.KewConstants.PROPOSAL_DEVELOPMENT_DOCUMENT.equals(request.getParameter(DOCUMENT_TYPE_NAME))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, Constants.PROPOSAL_ACTIONS_PAGE);
        } else if (PROTOCOL_DOCUMENT.equals(request.getParameter(DOCUMENT_TYPE_NAME)) ||
                IACUC_PROTOCOL_DOCUMENT.equals(request.getParameter(DOCUMENT_TYPE_NAME))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, Constants.MAPPING_PROTOCOL_ACTIONS);
        } else if (AWARD_DOCUMENT.equals(request.getParameter(DOCUMENT_TYPE_NAME))) {
            docHandler = docHandler.replace(KRADConstants.DOC_HANDLER_METHOD, Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }
          
        returnForward = new ActionForward(docHandler, returnForward.getRedirect());
        return returnForward;
    }
}
