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
package org.kuali.kra.budget.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="BUDGET_CATEGORY")
public class BudgetCategory extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="BUDGET_CATEGORY_CODE")
	private String budgetCategoryCode;
	@Column(name="CATEGORY_TYPE")
	private String budgetCategoryTypeCode;
	@Column(name="DESCRIPTION")
	private String description;
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="CATEGORY_TYPE", insertable=false, updatable=false)
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

