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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

@IdClass(org.kuali.kra.budget.bo.id.BudgetCategoryMappingId.class)
@Entity
@Table(name = "BUDGET_CATEGORY_MAPPING")
public class BudgetCategoryMapping extends KraPersistableBusinessObjectBase {
    @Id
    @Column(name = "COEUS_CATEGORY_CODE")
    private String budgetCategoryCode;
    
    @Id
    @Column(name = "MAPPING_NAME")
    private String mappingName;
    
    @Id
    @Column(name = "TARGET_CATEGORY_CODE")
    private String targetCategoryCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "COEUS_CATEGORY_CODE", insertable = false, updatable = false)
    private BudgetCategory budgetCategory;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name = "MAPPING_NAME", insertable = false, updatable = false), 
                  @JoinColumn(name="TARGET_CATEGORY_CODE", insertable = false, updatable = false)})
    private BudgetCategoryMap budgetCategoryMap;

    public String getBudgetCategoryCode() {
        return budgetCategoryCode;
    }

    public void setBudgetCategoryCode(String budgetCategoryCode) {
        this.budgetCategoryCode = budgetCategoryCode;
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

    public BudgetCategoryMap getBudgetCategoryMap() {
        return budgetCategoryMap;
    }

    public void setBudgetCategoryMap(BudgetCategoryMap budgetCategoryMap) {
        this.budgetCategoryMap = budgetCategoryMap;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
        hashMap.put("mappingName", getMappingName());
        hashMap.put("targetCategoryCode", getTargetCategoryCode());
        return hashMap;
    }

    /**
     * Gets the budgetCategory attribute.
     * 
     * @return Returns the budgetCategory.
     */
    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    /**
     * Sets the budgetCategory attribute value.
     * 
     * @param budgetCategory The budgetCategory to set.
     */
    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
}
