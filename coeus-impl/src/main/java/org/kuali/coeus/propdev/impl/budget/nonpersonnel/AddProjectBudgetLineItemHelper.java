/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
