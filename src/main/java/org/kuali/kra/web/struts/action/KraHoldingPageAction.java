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

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

/**
 * Checks to see whether the document specified in the session has completed its asynchronous processing and is ready to be reloaded.
 */
public class KraHoldingPageAction extends KualiAction {
    
    private DocumentService documentService;
    private PersonService<Person> personService;
    private ResearchDocumentService researchDocumentService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = super.execute(mapping, form, request, response);
        
        Object documentId = request.getSession().getAttribute(KNSConstants.DOCUMENT_HTTP_SESSION_KEY);
        Document document = getDocumentService().getByDocumentHeaderId(documentId.toString());
        if (isDocumentPostprocessingComplete(document)) {            
            Long routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            forward = new ActionForward(buildForwardUrl(routeHeaderId), true);
        }
        
        return forward;
    }
    
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
    private boolean isDocumentPostprocessingComplete(Document document) {
        boolean isPessimisticallyLocked = false;
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName("kr");
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(pessimisticLockHolder)) {
                isPessimisticallyLocked = true;
                break;
            }
        }
        
        return document.getDocumentHeader().hasWorkflowDocument() && !isPessimisticallyLocked;
    }
    
    /**
     * Builds the forward URL for the given routeHeaderId.
     * 
     * @param routeHeaderId the document id to forward to
     * @return the forward URL for the given routeHeaderId
     */
    private String buildForwardUrl(Long routeHeaderId) {
        StringBuilder builder = new StringBuilder();
        builder.append(getResearchDocumentService().getDocHandlerUrl(routeHeaderId));
        
        Properties parameters = new Properties();
        parameters.put(KEWConstants.ROUTEHEADER_ID_PARAMETER, routeHeaderId);
        parameters.put(KEWConstants.COMMAND_PARAMETER, NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW);
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            parameters.put(KEWConstants.BACKDOOR_ID_PARAMETER, GlobalVariables.getUserSession().getPrincipalName());
        }
        
        for (Map.Entry<Object, Object> parameter : parameters.entrySet()) {
            builder.append("&");
            builder.append(parameter.getKey());
            builder.append("=");
            builder.append(parameter.getValue());
        }
        
        return builder.toString();
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
    
    public ResearchDocumentService getResearchDocumentService() {
        if (researchDocumentService == null) {
            researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        }
        return researchDocumentService;
    }
    
    public void setResearchDocumentService(ResearchDocumentService researchDocumentService) {
        this.researchDocumentService = researchDocumentService;
    }
    
}