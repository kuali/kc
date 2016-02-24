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
package org.kuali.kra.negotiations.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.printing.service.NegotiationPrintingService;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.SequenceAccessorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class NegotiationAction extends KcTransactionalDocumentActionBase {
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
//        negotiationForm.getCustomDataHelper().prepareCustomData();
//        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
//        getNegotiationService().checkForPropLogPromotion(negotiation);
        prepareNegotiation(negotiationForm);
        return forward;
    }
    
    protected void prepareNegotiation(NegotiationForm negotiationForm) {
        negotiationForm.getCustomDataHelper().prepareCustomData();
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        getNegotiationService().checkForPropLogPromotion(negotiation);
    }
            
    /**
     * Upon creating negotiation document default the negotiation status to in progress.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#createDocument(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);
        NegotiationDocument negotiationDocument = (NegotiationDocument) kualiDocumentFormBase.getDocument();
        negotiationDocument.getNegotiation().setNegotiationId(getSequenceAccessorService().getNextAvailableSequenceNumber(Constants.NEGOTIATION_SEQUENCE_NAME, negotiationDocument.getNegotiation().getClass()));
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

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.sendRedirect(buildForwardUrl(((NegotiationForm)form).getDocId()));
        return null;
    }

    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    protected NegotiationService getNegotiationService() {
        if (negotiationService == null) {
            negotiationService = KcServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }
    
    public SequenceAccessorService getSequenceAccessorService() {
        if (sequenceAccessorService == null) {
            sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    public NegotiationPrintingService getNegotiationPrintingService() {
        if (negotiationPrintingService == null) {
            negotiationPrintingService = KcServiceLocator.getService(NegotiationPrintingService.class);
        }
        return negotiationPrintingService;
    }

    public void setNegotiationPrintingService(NegotiationPrintingService negotiationPrintingService) {
        this.negotiationPrintingService = negotiationPrintingService;
    }
    
    protected KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        return notificationService;
    }
    
    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    protected KualiRuleService getKualiRuleService() {
        if (kualiRuleService == null) {
            kualiRuleService = KcServiceLocator.getService(KualiRuleService.class);
        }
        return kualiRuleService;
    }

}
