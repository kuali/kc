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

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.util.AbstractKualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="COST_ELEMENT")
public class CostElement extends KraPersistableBusinessObjectBase implements Comparable {
	@Id
	@Column(name="COST_ELEMENT")
	private String costElement;
	@Column(name="BUDGET_CATEGORY_CODE")
	private String budgetCategoryCode;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="ON_OFF_CAMPUS_FLAG")
	private Boolean onOffCampusFlag;
	private String budgetCategoryTypeCode;
	@OneToMany(
           targetEntity=org.kuali.kra.budget.bo.ValidCeRateType.class, mappedBy="costElementBo")
	private List<ValidCeRateType> validCeRateTypes;
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="BUDGET_CATEGORY_CODE", insertable=false, updatable=false)
	private BudgetCategory budgetCategory;
	
	public CostElement(){
	    validCeRateTypes = new ArrayList<ValidCeRateType>();
	}
	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public String getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(String budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("description", getDescription());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		return hashMap;
	}

    public List<ValidCeRateType> getValidCeRateTypes() {
        return validCeRateTypes;
    }

    public void setValidCeRateTypes(List<ValidCeRateType> validCeRateTypes) {
        this.validCeRateTypes = validCeRateTypes;
    }

    /**
     * This is for totals page to sort it by costelement
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return compareTo((CostElement) o);
    }
    
    public int compareTo(CostElement costElement) {
        return this.costElement.compareTo(costElement.costElement);
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }

    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }

}

