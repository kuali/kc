/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.document.Document;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;

import edu.iu.uis.eden.clientapp.IDocHandler;

public class AwardAction extends KraTransactionalDocumentActionBase {
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        AwardForm awardForm = (AwardForm) form;
        String command = awardForm.getCommand();
        
        if (IDocHandler.ACTIONLIST_INLINE_COMMAND.equals(command)) {
             String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
             Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
             awardForm.setDocument(retrievedDocument);
             request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
             forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
             forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else {
             forward = super.docHandler(mapping, form, request, response);
        }

        if (IDocHandler.INITIATE_COMMAND.equals(awardForm.getCommand())) {
            awardForm.getAwardDocument().initialize();
        }else{
            awardForm.initialize();
        }
        
        return forward;
    }
    
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return super.headerTab(mapping, form, request, response);
    }
    
    public ActionForward contacts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("contacts");
    }
    
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("timeAndMoney");
    }
    
    public ActionForward paymentReportsAndTerms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("paymentReportsAndTerms");
    }
    
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("specialReview");
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("customData");
    }
    
    public ActionForward question(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("question");
    }
    
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("permissions");
    }
    
    public ActionForward notesAndAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("notesAndAttachments");
    }
    
    public ActionForward awardActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward("awardActions");
    }
}
