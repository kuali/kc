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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.Collection;
import java.util.Map;

public interface BudgetService<T extends BudgetParent>  {
    

    public Budget addBudgetVersion(BudgetParentDocument<T> budgetParent, String versionName, Map<String, Object> options);
    
    /**
     * check if this line item CE has inflation rate
     * @param budgetLineItem
     * @return
     */
    public boolean validInflationCeRate(BudgetLineItemBase budgetLineItem);
    
    public String getActivityTypeForBudget(Budget budget);

    public Collection<BudgetRate> getSavedProposalRates(Budget budgetToOpen);

    public boolean isBudgetVersionNameValid(BudgetParent parent, String versionName);

    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     */    
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod);
    
    public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod);

}
