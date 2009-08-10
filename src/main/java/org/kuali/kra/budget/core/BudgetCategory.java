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
package org.kuali.kra.budget.core;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetCategory extends KraPersistableBusinessObjectBase {
	private String budgetCategoryCode;
	private String budgetCategoryTypeCode;
	private String description;
	private BudgetCategoryType budgetCategoryType;
	
	public String getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(String budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public String getBudgetCategoryTypeCode() {
		return budgetCategoryTypeCode;
	}

	public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
		this.budgetCategoryTypeCode = budgetCategoryTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("categoryType", getBudgetCategoryTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}

    /**
     * Gets the budgetCategoryType attribute. 
     * @return Returns the budgetCategoryType.
     */
    public BudgetCategoryType getBudgetCategoryType() {
        return budgetCategoryType;
    }

    /**
     * Sets the budgetCategoryType attribute value.
     * @param budgetCategoryType The budgetCategoryType to set.
     */
    public void setBudgetCategoryType(BudgetCategoryType budgetCategoryType) {
        this.budgetCategoryType = budgetCategoryType;
    }

}
