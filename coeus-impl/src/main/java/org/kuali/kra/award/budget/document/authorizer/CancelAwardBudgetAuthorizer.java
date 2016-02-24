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



import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
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

    public boolean isAuthorized(String username, AwardBudgetTask task) {
        AwardBudgetDocument doc = task.getAwardBudgetDocument();
        WorkflowDocument workDoc = doc.getDocumentHeader().getWorkflowDocument();
        return !workDoc.isCompletionRequested() 
            && !getKraDocumentRejectionService().isDocumentOnInitialNode(doc.getDocumentHeader().getWorkflowDocument())
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
