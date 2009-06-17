/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.rule;

import org.kuali.kra.budget.rule.event.AddBudgetCostShareEvent;

public interface AddBudgetCostShareRule {

    /**
     * Process business rules for add of BudgetCostShare
     * @param addBudgetCostShareEvent
     * @return
     */
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent budgetCostShareEvent);
}
