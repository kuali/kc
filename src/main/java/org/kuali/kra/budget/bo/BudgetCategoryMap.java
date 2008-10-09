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

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.budget.bo.id.BudgetCategoryMapId.class)
@Entity
@Table(name="BUDGET_CATEGORY_MAPS")
public class BudgetCategoryMap extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="MAPPING_NAME")
	private String mappingName;
	
	@Id
	@Column(name="TARGET_CATEGORY_CODE")
	private String targetCategoryCode;
	
	@Column(name="CATEGORY_TYPE")
	private String categoryType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetCategoryMapping.class, mappedBy="budgetCategoryMap")
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

