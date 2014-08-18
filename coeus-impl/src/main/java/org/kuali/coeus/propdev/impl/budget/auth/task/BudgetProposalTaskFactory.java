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
package org.kuali.coeus.propdev.impl.budget.auth.task;

import org.apache.struts.action.ActionForm;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.WebTaskFactoryBase;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.TaskGroupName;

import javax.servlet.http.HttpServletRequest;

/**
 * The Budget Proposal Task Factory will create a Proposal Task with its
 * task name and the proposal development document contained within
 * the form.  Due to the relationship between budgets and proposals, we
 * sometimes need to determine if we can perform a proposal task from
 * within a budget document.
 */
public class BudgetProposalTaskFactory extends WebTaskFactoryBase {

    @Override
    public Task createTask(ActionForm form, HttpServletRequest request) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetParentDocument parentDocument = budgetForm.getBudgetDocument().getBudget().getBudgetParent().getDocument();
        return parentDocument.getParentAuthZTask(getTaskName());
    }
    
    @Override
    public String getTaskGroupName() {
        return TaskGroupName.PROPOSAL;
    }
}
