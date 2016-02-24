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
package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

import java.util.List;

public interface BudgetPersonnelBudgetService {
    public void addBudgetPersonnelDetails(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudegtPersonnelDetails);
    public void calculateBudgetPersonnelBudget(Budget budget, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails,  int lineNumber);
    public void deleteBudgetPersonnelDetails(Budget budget, int selectedBudgetPeriodIndex,
            int selectedBudgetLineItemIndex, int lineToDelete);
    public List<BudgetPersonSalaryDetails> calculatePersonSalary(Budget budget, int personIndex);
    /**
     * Removes all {@link BudgetPersonnelDetails} instances for a given {@link BudgetPerson}. Has to iterate through {@link BudgetPeriod} instances,
     * {@link BudgetLineItem} instances, and finally {@link BudgetPersonnelDetails} instances. Then the {@link BudgetPerson} instances are compared.
     *
     * @param budget Budget to remove {@link BudgetPersonnelDetails} from
     * @param person {@link BudgetPerson} we're looking for
     */
    public void deleteBudgetPersonnelDetailsForPerson(Budget budget, BudgetPerson person);
    
    /**
     * This method is to assign budget personnel to specific period.
     * Find existing line item based on group key and add personnel details to it.
     * If line item does not exist, add it to budget period line item list. 
     * @param budgetPeriod
     * @param newBudgetLineItem
     * @param newBudgetPersonnelDetail
     */
    public void addBudgetPersonnelToPeriod(BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail);
    
    public void calculateCurrentBudgetPeriod(BudgetPeriod budgetPeriod);
    
    public void calculateBudgetPersonnelLineItem(Budget budget, BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, int lineItemNumber);
    
    public void refreshBudgetPersonnelLineItemReferences(BudgetPersonnelDetails newBudgetPersonnelDetail);
    
}
