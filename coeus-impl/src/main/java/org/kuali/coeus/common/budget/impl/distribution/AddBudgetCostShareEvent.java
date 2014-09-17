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
package org.kuali.coeus.common.budget.impl.distribution;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;

public class AddBudgetCostShareEvent {

    private BudgetCostShare budgetCostShare;
    private Budget budget;
    
    public AddBudgetCostShareEvent(Budget budget, BudgetCostShare budgetCostShare) {
    	this.budget = budget;
        this.budgetCostShare = budgetCostShare;
    }

    public BudgetCostShare getBudgetCostShare() {
        return budgetCostShare;
    }

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public void setBudgetCostShare(BudgetCostShare budgetCostShare) {
		this.budgetCostShare = budgetCostShare;
	}

}
