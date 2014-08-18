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
package org.kuali.coeus.common.budget.framework.auth.task;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;

/**
 * A Budget Task is a task that performs an action against a
 * Budget Document.  To assist authorization, the
 * Budget Document is available.
 */
public final class BudgetTask extends Task {
    
    private BudgetDocument budgetDocument;
    
    /**
     * Constructs a BudgetTask.
     * @param taskName the name of the task
     * @param taskGroupName the name of the task group
     * @param budgetDocument the Budget Document
     */
    public BudgetTask(String taskGroupName, String taskName, BudgetDocument budgetDocument) {
        super(taskGroupName,taskName);
        this.budgetDocument = budgetDocument;
    }    
    /**
     * Get the Budget Document.
     * @return the Budget Document
     */
    public BudgetDocument getBudgetDocument() {
        return budgetDocument;
    }
}
