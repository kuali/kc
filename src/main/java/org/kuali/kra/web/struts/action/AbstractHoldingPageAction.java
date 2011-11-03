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
package org.kuali.kra.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public abstract class AbstractHoldingPageAction extends KualiAction {

    
    private DocumentService documentService;
    private PersonService<Person> personService;
    
    /**
     * Returns the user directly to the Portal instead of having to wait for the document to reload.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnToPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(KNSConstants.MAPPING_PORTAL);
    }
    
    /**
     * Checks whether the postprocessing on the given document is complete.
     * 
     * @param document the document to check
     * @return true if the postprocessing is complete, false otherwise
     */
    protected boolean isDocumentPostprocessingComplete(Document document) {
        return document.getDocumentHeader().hasWorkflowDocument() && !isPessimisticallyLocked(document) && isProcessComplete(document);
    }
    
    /**
     * 
     * This method checks for pessimistic locks 
     * @param document - the workflow document
     * @return true if the document is still locked.
     */
    protected boolean isPessimisticallyLocked(Document document) {
        boolean isPessimisticallyLocked = false;
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName(KEWConstants.SYSTEM_USER);
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(pessimisticLockHolder)) {
                isPessimisticallyLocked = true;
                break;
            }
        }
        
        return isPessimisticallyLocked;
    }
    
    
    // TODO : have NOT found a consistent indicator of whether a document route is processed or not.
    // so a couple of hacks for now.
    protected boolean isProcessComplete(Document document) {
        boolean isProcessComplete = false;
        
        if (document instanceof AwardDocument) {
            isProcessComplete = ((AwardDocument) document).isProcessComplete();
        } else if (document instanceof CommitteeDocument) {
            isProcessComplete = ((CommitteeDocument) document).isProcessComplete();
        } else if (document instanceof InstitutionalProposalDocument) {
            isProcessComplete = ((InstitutionalProposalDocument) document).isProcessComplete();
        } else if (document instanceof ProposalDevelopmentDocument) {
            isProcessComplete = ((ProposalDevelopmentDocument) document).isProcessComplete();
        } else if (document instanceof ProtocolDocument) {
            isProcessComplete = ((ProtocolDocument) document).isProcessComplete();
        }else if (document instanceof TimeAndMoneyDocument) {
            isProcessComplete = ((TimeAndMoneyDocument) document).isProcessComplete();
        } else if (document instanceof ProtocolOnlineReviewDocument) {
            isProcessComplete = ((ProtocolOnlineReviewDocument) document).isProcessComplete();
        }
        
        return isProcessComplete;
    }
    
    public DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KraServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    @SuppressWarnings("unchecked")
    public PersonService<Person> getPersonService() {
        if (personService == null) {
            personService = KraServiceLocator.getService(PersonService.class);
        }
        return personService;
    }
    
    public void setPersonService(PersonService<Person> personService) {
        this.personService = personService;
    }
}
