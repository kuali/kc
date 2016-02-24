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
package org.kuali.kra.iacuc.species.exception;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IacucProtocolExceptionAction extends IacucProtocolAction{
    private static final String CONFIRM_DELETE_PROTOCOL_EXCEPTION_KEY = "confirmDeleteProtocolException";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
        protocolForm.getIacucProtocolExceptionHelper().prepareView();
        
        return forward;
    }

    public ActionForward addProtocolException(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocolException iacucProtocolException = protocolForm.getIacucProtocolExceptionHelper().getNewIacucProtocolException();
        
        if (applyRules(new AddProtocolExceptionEvent(Constants.EMPTY_STRING, document, iacucProtocolException))) {
            getIacucProtocolExceptionService().addProtocolException(document.getIacucProtocol(), iacucProtocolException);
            protocolForm.getIacucProtocolExceptionHelper().setNewIacucProtocolException(new IacucProtocolException());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    public ActionForward deleteProtocolException(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROTOCOL_EXCEPTION_KEY,
                KeyConstants.QUESTION_PROTOCOL_EXCEPTION_DELETE_CONFIRMATION), CONFIRM_DELETE_PROTOCOL_EXCEPTION_KEY, "");
    }


    @SuppressWarnings("deprecation")
    public ActionForward confirmDeleteProtocolException(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_EXCEPTION_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
            
            document.getIacucProtocol().getIacucProtocolExceptions().remove(getLineToDelete(request));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected IacucProtocolExceptionService getIacucProtocolExceptionService() {
        return (IacucProtocolExceptionService) KcServiceLocator.getService("iacucProtocolExceptionService");
    }
    
}
