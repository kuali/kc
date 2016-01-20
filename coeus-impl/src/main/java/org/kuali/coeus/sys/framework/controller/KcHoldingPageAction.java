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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Checks to see whether the document specified in the session has completed its asynchronous processing and is ready to be
 * reloaded.
 */
public class KcHoldingPageAction extends KualiAction {

    private static final String RETURN_TO_PORTAL = "returnToPortal";

    private DocumentService documentService;
    private PersonService personService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward forward = super.execute(mapping, form, request, response);

        final String documentIdParameter = request.getParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID);
        final Object documentId;
        if (StringUtils.isNotBlank(documentIdParameter)) {
            documentId = documentIdParameter;
        } else {
            // get the id from the top level http session
            documentId = request.getSession().getAttribute(KRADConstants.DOCUMENT_HTTP_SESSION_KEY);
        }
        Document document = getByDocumentHeaderId(documentId.toString());
        // check if the user clicked the 'Return to Portal' button
        if (RETURN_TO_PORTAL.equals(findMethodToCall(form, request))) {
            // clean up the session and also remove the messages meant for the return page
            cleanupUserSession();
            GlobalVariables.getUserSession().removeObject(KcHoldingPageConstants.HOLDING_PAGE_MESSAGES);
        } else if(GlobalVariables.getUserSession().retrieveObject(Constants.FORCE_HOLDING_PAGE_FOR_ACTION_LIST) != null) {
            // this is a temporary solution
            // introduced to unload the block ui in embedded mode
            GlobalVariables.getUserSession().removeObject(Constants.FORCE_HOLDING_PAGE_FOR_ACTION_LIST); 
            forward = getReturnPath(request);
        }
        else if (isDocumentPostprocessingComplete(document)) {
            forward = getReturnPath(request);
        }
        
        return forward;
    }

    /**
     * This method is to get the return location and clean up session.
     */
    private ActionForward getReturnPath(HttpServletRequest request) {

        final String documentIdParameter = request.getParameter(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION);
        final String backLocation;
        if (StringUtils.isNotBlank(documentIdParameter)) {
            backLocation = documentIdParameter;
        } else {
            backLocation = (String) GlobalVariables.getUserSession().retrieveObject(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION);
        }

        cleanupUserSession();
        return new ActionForward(backLocation, true);
    }

    private void cleanupUserSession() {
        GlobalVariables.getUserSession().removeObject(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION);
    }

    /**
     * Returns the user directly to the Portal instead of having to wait for the document to reload.
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
