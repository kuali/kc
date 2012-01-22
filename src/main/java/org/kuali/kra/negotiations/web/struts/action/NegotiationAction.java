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

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.printing.service.NegotiationPrintingService;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.SequenceAccessorService;

/**
 * 
 * This class...
 */
public class NegotiationAction extends KraTransactionalDocumentActionBase {
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NegotiationAction.class);
    
    private NegotiationService negotiationService;
    private SequenceAccessorService sequenceAccessorService;
    private NegotiationPrintingService negotiationPrintingService;
    private KcNotificationService notificationService;
    private KualiRuleService kualiRuleService;

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.docHandler(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getCustomDataHelper().negotiationCustomData(mapping, negotiationForm, request, response);
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        getNegotiationService().checkForPropLogPromotion(negotiation);
        return forward;
    }
            
    /**
     * Upon creating negotiation document default the negotiation status to in progress.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#createDocument(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);
        NegotiationDocument negotiationDocument = (NegotiationDocument) kualiDocumentFormBase.getDocument();
        negotiationDocument.getNegotiation().setNegotiationId(getSequenceAccessorService().getNextAvailableSequenceNumber(Constants.NEGOTIATION_SEQUENCE_NAME));
        negotiationDocument.getNegotiation().setNegotiationStatus(
                getNegotiationService().getNegotiationStatus(getNegotiationService().getInProgressStatusCodes().get(0)));
        negotiationDocument.getNegotiation().setNegotiationStatusId(negotiationDocument.getNegotiation().getNegotiationStatus().getId());
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {  
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationDocument negotiationDocument = negotiationForm.getNegotiationDocument();
        if (negotiationDocument.getDocumentHeader().getWorkflowDocument().isInitiated() 
                || negotiationDocument.getDocumentHeader().getWorkflowDocument().isSaved()) {
            getDocumentService().routeDocument(negotiationDocument, "Route To Final", new ArrayList());
        }
        
        actionForward = super.save(mapping, form, request, response);
        return actionForward;
    }
    
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
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
    
    public NegotiationPrintingService getNegotiationPrintingService() {
        if (negotiationPrintingService == null) {
            negotiationPrintingService = KraServiceLocator.getService(NegotiationPrintingService.class);
        }
        return negotiationPrintingService;
    }

    public void setNegotiationPrintingService(NegotiationPrintingService negotiationPrintingService) {
        this.negotiationPrintingService = negotiationPrintingService;
    }
    
    protected KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KraServiceLocator.getService(KcNotificationService.class);
        }
        return notificationService;
    }
    
    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    protected KualiRuleService getKualiRuleService() {
        if (kualiRuleService == null) {
            kualiRuleService = KraServiceLocator.getService(KualiRuleService.class);
        }
        return kualiRuleService;
    }
    
}
