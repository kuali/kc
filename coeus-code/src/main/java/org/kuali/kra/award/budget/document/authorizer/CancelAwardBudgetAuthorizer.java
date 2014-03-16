/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.budget.document.authorizer;



import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.budget.document.authorizer.BudgetAuthorizer;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * This authorizer determines if the user has the permission
 * to cancel an award budget. This is only for after the 
 * budget has been submitted into workflow and is to
 * authorize the routing user to pull back the budget
 * using a super user action
 * 
 */
public class CancelAwardBudgetAuthorizer extends BudgetAuthorizer {
    
    private KcDocumentRejectionService kraDocumentRejectionService;

    public boolean isAuthorized(String username, BudgetTask task) {
        BudgetDocument doc = task.getBudgetDocument();
        WorkflowDocument workDoc = doc.getDocumentHeader().getWorkflowDocument();
        return !workDoc.isCompletionRequested() 
            && !getKraDocumentRejectionService().isDocumentOnInitialNode(doc) 
            && StringUtils.equals(username, workDoc.getRoutedByPrincipalId())
            && workDoc.isEnroute();
    }
    
    protected KcDocumentRejectionService getKraDocumentRejectionService() {
        return kraDocumentRejectionService;
    }
    public void setKraDocumentRejectionService(KcDocumentRejectionService kraDocumentRejectionService) {
        this.kraDocumentRejectionService = kraDocumentRejectionService;
    }
    
    
}
