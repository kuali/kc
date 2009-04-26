/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.service;

import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;

/**
 * This class...
 */
public interface BudgetPersonnelBudgetService {
    public void addBudgetPersonnelDetails(BudgetDocument budgetDocument,int selectedBudgetLineItem, int selectedBudgetLineItemIndex, BudgetPersonnelDetails newBudegtPersonnelDetails);
    public void calculateBudgetPersonnelBudget(BudgetDocument budgetDocument, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails,  int lineNumber);
    public void deleteBudgetPersonnelDetails(BudgetDocument budgetDocument, int selectedBudgetPeriodIndex,
            int selectedBudgetLineItemIndex, int lineToDelete);

    /**
     * Removes all {@link BudgetPersonnelDetails} instances for a given {@link BudgetPerson}. Has to iterate through {@link BudgetPeriod} instances,
     * {@link BudgetLineItem} instances, and finally {@link BudgetPersonnelDetails} instances. Then the {@link BudgetPerson} instances are compared.
     *
     * @param document BudgetDocument to remove {@link BudgetPersonnelDetails} from
     * @param person {@link BudgetPerson} we're looking for
     */
    public void deleteBudgetPersonnelDetailsForPerson(BudgetDocument document, BudgetPerson person);
}
