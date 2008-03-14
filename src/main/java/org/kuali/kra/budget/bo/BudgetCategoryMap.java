/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetCategoryMap extends KraPersistableBusinessObjectBase {
	private String mappingName;
	private String targetCategoryCode;
	private String categoryType;
	private String description;
    private List<BudgetCategoryMapping> budgetCategoryMappings;

    public BudgetCategoryMap(){
        budgetCategoryMappings  = new ArrayList<BudgetCategoryMapping>();
    }

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getTargetCategoryCode() {
		return targetCategoryCode;
	}

	public void setTargetCategoryCode(String targetCategoryCode) {
		this.targetCategoryCode = targetCategoryCode;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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
		hashMap.put("mappingName", getMappingName());
		hashMap.put("targetCategoryCode", getTargetCategoryCode());
		hashMap.put("categoryType", getCategoryType());
		hashMap.put("description", getDescription());
		return hashMap;
	}

    /**
     * Gets the budgetCategoryMappings attribute. 
     * @return Returns the budgetCategoryMappings.
     */
    public List<BudgetCategoryMapping> getBudgetCategoryMappings() {
        return budgetCategoryMappings;
    }

    /**
     * Sets the budgetCategoryMappings attribute value.
     * @param budgetCategoryMappings The budgetCategoryMappings to set.
     */
    public void setBudgetCategoryMappings(List<BudgetCategoryMapping> budgetCategoryMappings) {
        this.budgetCategoryMappings = budgetCategoryMappings;
    }
}
