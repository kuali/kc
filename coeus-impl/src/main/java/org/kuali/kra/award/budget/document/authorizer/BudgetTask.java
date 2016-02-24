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

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.budget.framework.core.Budget;

/**
 * A Budget Task is a task that performs an action against a
 * Budget Document.  To assist authorization, the
 * Budget Document is available.
 */
public final class BudgetTask extends Task {
    
    private Budget budget;
    
    /**
     * Constructs a BudgetTask.
     * @param taskName the name of the task
     * @param taskGroupName the name of the task group
     * @param budgetDocument the Budget Document
     */
    public BudgetTask(String taskGroupName, String taskName, Budget budget) {
        super(taskGroupName,taskName);
        this.budget = budget;
    }    
    /**
     * Get the Budget Document.
     * @return the Budget Document
     */
    public Budget getBudget() {
        return budget;
    }
}
