/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.negotiations.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * 
 * This class...
 */
public class NegotiationAction extends KraTransactionalDocumentActionBase {
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NegotiationAction.class);
    
    private NegotiationService negotiationService;
    private SequenceAccessorService sequenceAccessorService;

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.docHandler(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        getNegotiationService().checkForPropLogPromotion(negotiation);
        return forward;
    }
        
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response); 
        NegotiationForm negotiationForm = (NegotiationForm)form;
        negotiationForm.populateAuthorizationFields();
        return actionForward;
    }
    
    /**
     * Upon creating negotiation document default the negotiation status to in progress.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#createDocument(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);
        NegotiationDocument negotiationDocument = (NegotiationDocument) kualiDocumentFormBase.getDocument();
        negotiationDocument.getNegotiation().setNegotiationId(getSequenceAccessorService().getNextAvailableSequenceNumber(Constants.NEGOTIATION_SEQUENCE_NAME));
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {  
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationDocument negotiationDocument = negotiationForm.getNegotiationDocument();
        
        //call authorizer here!
        
        actionForward = super.save(mapping, form, request, response);
        return actionForward;
    }
    
    protected NegotiationService getNegotiationService() {
        if (negotiationService == null) {
            negotiationService = KraServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }
    
    public SequenceAccessorService getSequenceAccessorService() {
        if (sequenceAccessorService == null) {
            sequenceAccessorService = KraServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
}
