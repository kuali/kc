/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Class representation of the BudgetCategory Type Business Object
 * 
 * BudgetCategoryType.java
 */
public class BudgetCategoryType extends KcPersistableBusinessObjectBase implements Comparable<BudgetCategoryType> {

    private String budgetCategoryTypeCode;

    private String description;

    private Integer sortId;

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
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

    /**
     * This is for totals page 
     */
    @Override
    public int compareTo(BudgetCategoryType o) {
        return this.sortId.compareTo(o.sortId);
    }
}
