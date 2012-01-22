/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.document.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;

/**
 * Base class for Narrative Authorizers.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class BudgetAuthorizer extends TaskAuthorizerImpl {
    
    private KraAuthorizationService kraAuthorizationService;
    private boolean requiresWritableDoc = false;
    
    public boolean isAuthorized(String userId, Task task) {
        BudgetTask budgetTask = (BudgetTask)task;
        if (isRequiresWritableDoc() && budgetTask.getBudgetDocument().isViewOnly()) {
            return false;
        } else {
            return isAuthorized(userId, budgetTask);
        }
           
    }
    
    public boolean isAuthorized(String userId, BudgetTask task) {
        return true;
    }

    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the Kra Authorization Service
     */
    public final void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param username the unique username of the user
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasParentPermission(String userId, BudgetParentDocument doc, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, doc, permissionName);
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }

    public boolean isRequiresWritableDoc() {
        return requiresWritableDoc;
    }

    public void setRequiresWritableDoc(boolean requiresWritableDoc) {
        this.requiresWritableDoc = requiresWritableDoc;
    }
    
    
}
