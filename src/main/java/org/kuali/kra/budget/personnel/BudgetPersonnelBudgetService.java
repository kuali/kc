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
package org.kuali.kra.budget.personnel;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;

/**
 * This class...
 */
public interface BudgetPersonnelBudgetService {
    public void addBudgetPersonnelDetails(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudegtPersonnelDetails);
    public void calculateBudgetPersonnelBudget(Budget budget, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails,  int lineNumber);
    public void deleteBudgetPersonnelDetails(Budget budget, int selectedBudgetPeriodIndex,
            int selectedBudgetLineItemIndex, int lineToDelete);

    /**
     * Removes all {@link BudgetPersonnelDetails} instances for a given {@link BudgetPerson}. Has to iterate through {@link BudgetPeriod} instances,
     * {@link BudgetLineItem} instances, and finally {@link BudgetPersonnelDetails} instances. Then the {@link BudgetPerson} instances are compared.
     *
     * @param document Budget to remove {@link BudgetPersonnelDetails} from
     * @param person {@link BudgetPerson} we're looking for
     */
    public void deleteBudgetPersonnelDetailsForPerson(Budget budget, BudgetPerson person);
}
