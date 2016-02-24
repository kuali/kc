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
package org.kuali.kra.negotiations.document.authorization;

import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.negotiations.auth.NegotiationTask;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * This class handles the authorization for the Negotiation Document.
 */
public class NegotiationDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    

    public NegotiationDocumentAuthorizer() {
        super();
    }

    
    @Override
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
            if (canExecuteNegotiationTask(userId, negotiationDocument, TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)
                    || canExecuteNegotiationTask(userId, negotiationDocument, TaskName.NEGOTIATION_MODIFY_ACTIVITIES)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else if (canExecuteNegotiationTask(userId, negotiationDocument, TaskName.NEGOTIATION_VIEW_NEGOTIATION)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }  
        }
        setPermissions(user, negotiationDocument, editModes);
        return editModes;
    }
    
    protected void setPermissions(Person user, NegotiationDocument negotiationDoc, Set<String> editModes) {
        
        if (canCreateNegotiation(user)) {
            editModes.add("create");
        }
        
        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc, TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)) {
            editModes.add("modify");
        }
        
        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc, TaskName.NEGOTIATION_CREATE_ACTIVITIES)) {
            editModes.add("create_activity");
        }        

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc, TaskName.NEGOTIATION_MODIFY_ACTIVITIES)) {
            editModes.add("modify_activity");
        }

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc, TaskName.NEGOTIATION_VIEW_NEGOTIATION)) {
            editModes.add("view");
        }

        if (canExecuteNegotiationTask(user.getPrincipalId(), negotiationDoc, TaskName.NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED)) {
            editModes.add("view_unrestricted");
        }
    }
    
    private boolean canCreateNegotiation(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.NEGOTIATION_CREATE_NEGOTIATION);
        boolean retVal = this.getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), task);
        return retVal;
    }
    
    
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateNegotiation(user);
    }
    
    @Override
    public boolean canOpen(Document document, Person user) {
        boolean retVal = 
            canExecuteNegotiationTask(user.getPrincipalId(), (NegotiationDocument) document, TaskName.NEGOTIATION_VIEW_NEGOTIATION);
        return retVal;
    }
    
    private boolean canExecuteNegotiationTask(String userId, NegotiationDocument negotiation, String taskName) {
        NegotiationTask modifyActivitiesTask = new NegotiationTask(taskName, negotiation);
        return this.getTaskAuthorizationService().isAuthorized(userId, modifyActivitiesTask);
    }
    
    public boolean canEdit(Document document, Person user) {
        return canExecuteNegotiationTask(user.getPrincipalId(), (NegotiationDocument) document, TaskName.NEGOTIATION_MODIFIY_NEGOTIATION)
            || canExecuteNegotiationTask(user.getPrincipalId(), (NegotiationDocument) document, TaskName.NEGOTIATION_MODIFY_ACTIVITIES)
            || canExecuteNegotiationTask(user.getPrincipalId(), (NegotiationDocument) document, TaskName.NEGOTIATION_CREATE_ACTIVITIES);
    }
    
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    public boolean canReload(Document document, Person user) {
        WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return canEdit(document, user) && !workflowDocument.isInitiated();
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }

    
}
