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
package org.kuali.kra.negotiations.document.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.negotiations.auth.NegotiationTask;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * 
 * This class handles the authorization for the Negotiation Document.
 */
public class NegotiationDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * 
     * Constructs a NegotiationDocumentAuthorizer.java.
     */
    public NegotiationDocumentAuthorizer() {
        super();
    }

    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        String userId = user.getPrincipalId();
        NegotiationDocument negotiationDocument = (NegotiationDocument) document;
        if (negotiationDocument.getNegotiation().getNegotiationId() == null) {
            if (canCreateNegotiation(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }  else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } else {
            if (canExecuteNegotiationTask(userId, negotiationDocument.getNegotiation(), TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)
                    || canExecuteNegotiationTask(userId, negotiationDocument.getNegotiation(), TaskName.NEGOTIATION_MODIFY_ACTIVITIES)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else if (canExecuteNegotiationTask(userId, negotiationDocument.getNegotiation(), TaskName.NEGOTIATION_VIEW_NEGOTIATION)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }  
        }
        setPermissions(user, negotiationDocument, editModes);
        return editModes;
    }
    
    protected void setPermissions(Person user, NegotiationDocument negotiationDoc, Set<String> editModes) {
        
        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc.getNegotiation(), TaskName.NEGOTIATION_CREATE_NEGOTIATION)) {
            editModes.add("create");
        }
        
        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc.getNegotiation(), TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)) {
            editModes.add("modify");
        }

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc.getNegotiation(), TaskName.NEGOTIATION_MODIFY_ACTIVITIES)) {
            editModes.add("modify_activity");
        }

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc.getNegotiation(), TaskName.NEGOTIATION_VIEW_NEGOTIATION)) {
            editModes.add("view");
        }

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc.getNegotiation(), TaskName.NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED)) {
            editModes.add("view_unrestricted");
        }
    }
    
    private boolean canCreateNegotiation(Person user) {
        boolean retVal = canExecuteNegotiationTask(user.getPrincipalId(), new Negotiation(), TaskName.NEGOTIATION_CREATE_NEGOTIATION);
        return retVal;
    }
    
    
    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateNegotiation(user);
    }
    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        boolean retVal = 
            canExecuteNegotiationTask(user.getPrincipalId(), ((NegotiationDocument) document).getNegotiation(), TaskName.NEGOTIATION_VIEW_NEGOTIATION);
        return retVal;
    }
    
    private boolean canExecuteNegotiationTask(String userId, Negotiation negotiation, String taskName) {
        NegotiationTask modifyActivitiesTask = new NegotiationTask(taskName, negotiation);
        return this.getTaskAuthorizationService().isAuthorized(userId, modifyActivitiesTask);
    }
    
    public boolean canEdit(Document document, Person user) {
        return canExecuteNegotiationTask(user.getPrincipalId(), ((NegotiationDocument) document).getNegotiation(), TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)
            || canExecuteNegotiationTask(user.getPrincipalId(), ((NegotiationDocument) document).getNegotiation(), TaskName.NEGOTIATION_MODIFY_ACTIVITIES);
    }
    
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    public boolean canReload(Document document, Person user) {
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return canEdit(document, user) && !workflowDocument.stateIsInitiated();
    }
}
