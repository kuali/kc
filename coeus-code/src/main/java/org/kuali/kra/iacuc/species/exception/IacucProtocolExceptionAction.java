/*
 * Copyright 2005-2013 The Kuali Foundation
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
