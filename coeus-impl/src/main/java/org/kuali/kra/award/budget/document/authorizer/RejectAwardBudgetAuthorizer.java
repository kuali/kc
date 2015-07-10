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
package org.kuali.kra.award.budget.document.authorizer;



import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * This authorizer determines if the user has the permission
 * to reject a proposal.  You can reject if:
 * 1) The document does not have a completion requested.
 * 2) You have an approval pending on the document.
 * 3) The document state is enroute.
 * 
 * 
 */
public class RejectAwardBudgetAuthorizer extends BudgetAuthorizer {

    public boolean isAuthorized(String username, Task task) {
    	if(task instanceof AwardBudgetTask) {
	        AwardBudgetDocument doc = ((AwardBudgetTask) task).getAwardBudgetDocument();
	        WorkflowDocument workDoc = doc.getDocumentHeader().getWorkflowDocument();
	        return !workDoc.isCompletionRequested() 
	            && workDoc.isApprovalRequested() 
	            && workDoc.isEnroute();
    	}
    	else { 
    		return super.isAuthorized(username, task);
    	}
    }
}
