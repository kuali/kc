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

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Class representation of the BudgetCategory Type Business Object
 *
 * BudgetCategoryType.java
 */
@Entity
@Table(name="BUDGET_CATEGORY_TYPE")
public class BudgetCategoryType extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="BUDGET_CATEGORY_TYPE_CODE")
	private String budgetCategoryTypeCode;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="SORT_ID")
	private String sortId;
	
    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
	public String getDescription() {
		return description;
	}
    
    /**
     * Assigns the description attribute
     *
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("budgetCategoryTypeCode", this.getBudgetCategoryTypeCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}

    /**
     * Gets the budgetCategoryTypeCode attribute. 
     * @return Returns the budgetCategoryTypeCode.
     */
    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }

    /**
     * Sets the budgetCategoryTypeCode attribute value.
     * @param budgetCategoryTypeCode The budgetCategoryTypeCode to set.
     */
    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }

}

