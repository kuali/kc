/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
