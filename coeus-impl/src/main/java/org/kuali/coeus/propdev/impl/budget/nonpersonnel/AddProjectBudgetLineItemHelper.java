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
package org.kuali.coeus.propdev.impl.budget.nonpersonnel;

import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.core.AddLineHelper;


public class AddProjectBudgetLineItemHelper extends AddLineHelper {

    private String editLineIndex;
    private BudgetLineItem budgetLineItem;
    private BudgetPeriod currentTabBudgetPeriod;
    private String budgetCategoryTypeCode;
    
    public AddProjectBudgetLineItemHelper() {
       super();
       initBudgetLineItemDetails();
    }
    
    public void reset() {
        super.reset();
        initBudgetLineItemDetails();
    }
    
    private void initBudgetLineItemDetails() {
        editLineIndex = null;
        budgetLineItem = new BudgetLineItem();
        currentTabBudgetPeriod = new BudgetPeriod();
        budgetCategoryTypeCode = "";
    }

	public String getEditLineIndex() {
		return editLineIndex;
	}

	public void setEditLineIndex(String editLineIndex) {
		this.editLineIndex = editLineIndex;
	}

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}

	public BudgetPeriod getCurrentTabBudgetPeriod() {
		return currentTabBudgetPeriod;
	}

	public void setCurrentTabBudgetPeriod(BudgetPeriod currentTabBudgetPeriod) {
		this.currentTabBudgetPeriod = currentTabBudgetPeriod;
	}

	public String getBudgetCategoryTypeCode() {
		return budgetCategoryTypeCode;
	}

	public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
		this.budgetCategoryTypeCode = budgetCategoryTypeCode;
	}

}
