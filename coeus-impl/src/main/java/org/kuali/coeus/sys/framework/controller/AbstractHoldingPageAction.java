/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractHoldingPageAction extends KualiAction {


    private DocumentService documentService;
    private PersonService personService;
    
    /**
     * Returns the user directly to the Portal instead of having to wait for the document to reload.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnToPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
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
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName(KewApiConstants.SYSTEM_USER);
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
        
        if (document instanceof KcTransactionalDocumentBase) {
            isProcessComplete = ((KcTransactionalDocumentBase) document).isProcessComplete();
        }
        
        return isProcessComplete;
    }


    protected Document getByDocumentHeaderId(String documentId) throws WorkflowException {
        return getDocumentService().getByDocumentHeaderId(documentId);
    }

    public DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KcServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public PersonService getPersonService() {
        if (personService == null) {
            personService = KcServiceLocator.getService(PersonService.class);
        }
        return personService;
    }
    
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
