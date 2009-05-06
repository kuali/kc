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
package org.kuali.kra.irb.specialreview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.service.SpecialReviewService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * This class represents the Struts Action for Special Review page(ProtocolSpecialReview.jsp)
 */
public class ProtocolSpecialReviewAction extends ProtocolAction {
    
    private static final String PRIMARY_KEY_FIELD_NAME = "protocolSpecialReviewId";
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((ProtocolForm)form).getSpecialReviewHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * This method is for adding ProtocolSpecialReview
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm)form;
        Protocol protocol = protocolForm.getDocument().getProtocol();
        if (getSpecialReviewService().addSpecialReview(protocol,protocolForm)) {
            protocolForm.getSpecialReviewHelper().setNewSpecialReview(new ProtocolSpecialReview());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method deletes the SpecialReview from the list
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm)form;
        Protocol protocol = protocolForm.getDocument().getProtocol();
        getSpecialReviewService().deleteSpecialReview(protocol,getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#preDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void preDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.preDocumentSave(form);
        ProtocolForm protocolForm = (ProtocolForm)form;
        Protocol protocol = protocolForm.getDocument().getProtocol();
        getSpecialReviewService().processBeforeSaveSpecialReview(protocol);
    }
    
    @Override
    protected void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        ProtocolForm protocolForm = (ProtocolForm)form;
        Protocol protocol = protocolForm.getDocument().getProtocol();
        getSpecialReviewService().deleteExemptions(protocol, PRIMARY_KEY_FIELD_NAME, ProtocolSpecialReviewExemption.class);
    }
    
    private SpecialReviewService<ProtocolSpecialReview,ProtocolSpecialReviewExemption> getSpecialReviewService() {
        return KraServiceLocator.getService(SpecialReviewService.class);
    }
}
