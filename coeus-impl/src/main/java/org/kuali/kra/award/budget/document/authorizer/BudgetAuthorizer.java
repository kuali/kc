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
package org.kuali.kra.award.budget.document.authorizer;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizerBase;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;

/**
 * Base class for Narrative Authorizers.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class BudgetAuthorizer extends TaskAuthorizerBase {
    
    private KcAuthorizationService kraAuthorizationService;
    private boolean requiresWritableDoc = false;
    
    public boolean isAuthorized(String userId, Task task) {
        BudgetTask budgetTask = (BudgetTask)task;
        if (isRequiresWritableDoc() && budgetTask.getBudget().isViewOnly()) {
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
    public final void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
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
    
    protected boolean hasParentPermission(String userId, BudgetParentDocument doc, String permissionNamespace, String permissionName) {
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
